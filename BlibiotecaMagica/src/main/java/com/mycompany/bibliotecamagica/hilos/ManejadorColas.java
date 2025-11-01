/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.hilos;

import com.mycompany.bibliotecamagica.backend.modelos.Biblioteca;
import com.mycompany.bibliotecamagica.backend.modelos.EntradaLibro;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *
 * @author rafael-cayax
 */
public class ManejadorColas extends Thread {

    private final Set<Biblioteca> bibliotecas = new CopyOnWriteArraySet<>();
    private final ConcurrentHashMap<Biblioteca, Boolean> nuevoIngreso = new ConcurrentHashMap<>();
    private static final int LIMITE = 50;
    private static final long TIEMPO = 100L;

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(TIEMPO);
                List<Biblioteca> nuevas = new LinkedList<>();
                for (Biblioteca biblioteca : bibliotecas) {
                    boolean nuevoTraspaso = false;
                    boolean nuevoDespacho = false;
                    if (!nuevoIngreso.get(biblioteca)) {
                        if (biblioteca.gettIngreso().desencolar(TIEMPO)) {
                            EntradaLibro datos = biblioteca.gettIngreso().obtenerPrimeroEnCola();
                            biblioteca.getBiblioColas().eliminarPrimeroEntrada();
                            if (datos.llegoASuDestino()) {
                                IngresoLibro nuevo = new IngresoLibro(biblioteca, datos);
                                nuevo.setDaemon(true);
                                nuevo.start();
                            } else {
                                nuevoTraspaso = biblioteca.gettTraspaso().estaVacia();
                                biblioteca.colocarEnTraspaso(datos);
                            }
                        }
                    } else {
                        nuevoIngreso.put(biblioteca, Boolean.FALSE);
                    }

                    if (!nuevoTraspaso && biblioteca.gettTraspaso().desencolar(TIEMPO)) {
                        EntradaLibro datos = biblioteca.gettTraspaso().obtenerPrimeroEnCola();
                        biblioteca.getBiblioColas().eliminarPrimeroTraspaso();
                        nuevoDespacho = biblioteca.gettTraspaso().estaVacia();
                        biblioteca.colocarEnDespacho(datos);
                    }

                    if (!nuevoDespacho && biblioteca.getDespacho().desencolar(TIEMPO)) {
                        EntradaLibro datos = biblioteca.getDespacho().obtenerPrimeroEnCola();
                        biblioteca.getBiblioColas().eliminarPrimeroDespacho();
                        Biblioteca siguiente = datos.obtenerSiguiente();
                        if (siguiente.gettIngreso().estaVacia() && nuevoIngreso.containsKey(siguiente)) {
                            nuevoIngreso.put(siguiente, Boolean.TRUE);
                        }
                        siguiente.colocarEnEntrada(datos);
                        nuevas.add(siguiente);
                    }
                }
                limpiarBibliotecas();
                agregarNuevas(nuevas);
            } catch (InterruptedException ex) {
                System.getLogger(ManejadorColas.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } while (!bibliotecas.isEmpty());
    }

    public boolean estaLLeno() {
        return bibliotecas.size() >= LIMITE;
    }

    public synchronized void agregarBiblioteca(Biblioteca biblioteca) {
        if (bibliotecas.add(biblioteca)) {
            nuevoIngreso.put(biblioteca, Boolean.FALSE);
        }
    }

    private void agregarNuevas(List<Biblioteca> nuevas) {
        for (Biblioteca biblioteca : nuevas) {
            agregarBiblioteca(biblioteca);
        }
    }

    private void limpiarBibliotecas() {
        Set<Biblioteca> paraRemover = new HashSet<>();
        for (Biblioteca bib : bibliotecas) {
            if (!bib.verificarEstadoCola()) {
                paraRemover.add(bib);
            }
        }
        bibliotecas.removeAll(paraRemover);
        paraRemover.forEach(bib -> nuevoIngreso.remove(bib));
    }
}
