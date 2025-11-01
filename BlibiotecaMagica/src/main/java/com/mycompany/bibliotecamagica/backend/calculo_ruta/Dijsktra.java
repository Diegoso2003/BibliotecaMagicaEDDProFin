/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.calculo_ruta;

import com.mycompany.bibliotecamagica.backend.RedBibliotecas;
import com.mycompany.bibliotecamagica.backend.enums.PrioridadEnum;
import com.mycompany.bibliotecamagica.backend.estructuras.Pila;
import com.mycompany.bibliotecamagica.backend.estructuras.cola.Cola;
import com.mycompany.bibliotecamagica.backend.estructuras.grafo.Conexion;
import com.mycompany.bibliotecamagica.backend.estructuras.grafo.NodoGrafo;
import com.mycompany.bibliotecamagica.backend.estructuras.lista_doble.ListaDoble;
import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.backend.iterador.IteradorLista;
import com.mycompany.bibliotecamagica.backend.modelos.Biblioteca;
import com.mycompany.bibliotecamagica.backend.modelos.EntradaLibro;
import com.mycompany.bibliotecamagica.backend.modelos.InfoLibro;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author rafael-cayax
 */
public class Dijsktra {
    private PrioridadEnum prioridad;
    private final HashMap<Biblioteca, BigDecimal> costos;
    private final HashMap<Biblioteca, Long> tiempos;
    private final HashMap<Biblioteca, Biblioteca> padres;
    private final Set<Biblioteca> bibliotecasVisitadas;
    private final ListaDoble<AuxiliarPrioridad> colaDePrioridad;

    public Dijsktra() {
        colaDePrioridad = new ListaDoble<>();
        padres = new HashMap<>();
        tiempos = new HashMap<>();
        costos = new HashMap<>();
        bibliotecasVisitadas = new HashSet<>();
    }

    public EntradaLibro calcularRuta(String idOrigen, String idDestino, InfoLibro libro, PrioridadEnum prioridad) throws EntradaException{
        this.prioridad = prioridad;
        NodoGrafo origen = RedBibliotecas.INSTANCIA.buscar(idOrigen).orElseThrow(() -> 
        new EntradaException("Biblioteca de origen no encontrada: " + idOrigen));
        NodoGrafo destino = RedBibliotecas.INSTANCIA.buscar(idDestino).orElseThrow(() -> 
        new EntradaException("Biblioteca de destino no encontrada: " + idDestino));
        return iniciarAnalisis(origen, destino, libro, true);
    }

    public void setPrioridad(PrioridadEnum prioridad) {
        this.prioridad = prioridad;
    }
    
    public EntradaLibro iniciarAnalisis(NodoGrafo origen, NodoGrafo destino, InfoLibro libro, boolean nuevo) throws EntradaException {
        limpiarVariables();
        colaDePrioridad.agregar(new AuxiliarPrioridad(prioridad, origen, 0L, BigDecimal.ZERO));
        tiempos.put(origen.getBiblioteca(), 0L);
        costos.put(origen.getBiblioteca(), BigDecimal.ZERO);
        padres.put(origen.getBiblioteca(), null);
        while(!colaDePrioridad.estaVacia()){
            AuxiliarPrioridad aux = colaDePrioridad.eliminarPrimero();
            NodoGrafo actual = aux.getNodo();
            if(!esBibliotecaYaVisitada(actual)){
                if(actual.equals(destino)){
                    return resultado(libro, destino, nuevo);
                }
                ListaDoble<Conexion> conexiones = actual.getConexiones();
                IteradorLista<Conexion> iterador = conexiones.getIterador();
                while(iterador.haySiguiente()){
                    Conexion conexion = iterador.getActual();
                    if(!bibliotecasVisitadas.contains(conexion.getBiblioAdyascente().getBiblioteca())){
                        compararDatos(aux, conexion);
                    }
                }
            }
        }
        throw new EntradaException("No se encontro ninguna forma de llegar de la biblioteca: " + origen + 
                " hasta la biblioteca: " + destino);
    }

    private void limpiarVariables() {
        costos.clear();
        tiempos.clear();
        padres.clear();
        bibliotecasVisitadas.clear();
        colaDePrioridad.limpiar();
    }

    private boolean esBibliotecaYaVisitada(NodoGrafo actual) {
        if(bibliotecasVisitadas.contains(actual.getBiblioteca())){
            return true;
        }
        bibliotecasVisitadas.add(actual.getBiblioteca());
        return false;
    }

    private void compararDatos(AuxiliarPrioridad padre, Conexion biblioActual) {
        BigDecimal nuevoC = padre.getCosto().add(biblioActual.getPrecio());
        Long nuevoT = padre.getTiempo() + biblioActual.getTiempo();
        boolean cambio = false;
        switch(prioridad){
            case COSTO -> {
                BigDecimal actual = costos.get(biblioActual.getBiblioAdyascente().getBiblioteca());
                cambio = actual == null || nuevoC.compareTo(actual) < 0;
            }
            default -> {
                Long actual = tiempos.get(biblioActual.getBiblioAdyascente().getBiblioteca());
                cambio = actual == null || nuevoT < actual;
            }
        }
        if(cambio){
            costos.put(biblioActual.getBiblioAdyascente().getBiblioteca(), nuevoC);
            padres.put(biblioActual.getBiblioAdyascente().getBiblioteca(), padre.getNodo().getBiblioteca());
            tiempos.put(biblioActual.getBiblioAdyascente().getBiblioteca(), nuevoT);
            colaDePrioridad.agregar(new AuxiliarPrioridad(prioridad, biblioActual.getBiblioAdyascente(), nuevoT, nuevoC));
        }
    }

    private EntradaLibro resultado(InfoLibro libro, NodoGrafo destino, boolean nuevo) {
        Pila<Biblioteca> pila = new Pila<>();
        Biblioteca d = destino.getBiblioteca();
        Long tiempo = tiempos.get(d);
        BigDecimal costo = costos.get(d);
        do{
            pila.apilar(d);
            d = padres.get(d);
        } while(d != null);
        Cola<Biblioteca> cola = new Cola<>();
        StringBuilder ruta = new StringBuilder();
        while(!pila.estaVacia()){
            Biblioteca biblioteca = pila.desapilar();
            cola.agregar(biblioteca);
            if(!ruta.isEmpty()){
                ruta.append(" -> ");
            }
            ruta.append(biblioteca.getId());
        }
        return new EntradaLibro(libro, nuevo, cola, new InfoTraslado(libro.getLibro(), costo, tiempo, ruta.toString(), prioridad));
    }
    
}