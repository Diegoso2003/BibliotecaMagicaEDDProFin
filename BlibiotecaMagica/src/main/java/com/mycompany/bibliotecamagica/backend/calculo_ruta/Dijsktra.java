/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.calculo_ruta;

import com.mycompany.bibliotecamagica.backend.RedBibliotecas;
import com.mycompany.bibliotecamagica.backend.enums.PrioridadEnum;
import com.mycompany.bibliotecamagica.backend.estructuras.Pila;
import com.mycompany.bibliotecamagica.backend.estructuras.grafo.Conexion;
import com.mycompany.bibliotecamagica.backend.estructuras.grafo.NodoGrafo;
import com.mycompany.bibliotecamagica.backend.estructuras.lista_doble.ListaDoble;
import com.mycompany.bibliotecamagica.backend.estructuras.lista_simple.ListaSimple;
import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.backend.iterador.IteradorLista;
import com.mycompany.bibliotecamagica.backend.modelos.Biblioteca;
import com.mycompany.bibliotecamagica.backend.modelos.Libro;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 *
 * @author rafael-cayax
 */
public class Dijsktra {
    private PrioridadEnum prioridad;
    private final HashMap<Biblioteca, BigDecimal> costos;
    private final HashMap<Biblioteca, Long> tiempos;
    private final HashMap<Biblioteca, Biblioteca> padres;
    private final HashMap<Biblioteca, Boolean> bibliotecasVisitadas;
    private final ListaDoble<AuxiliarPrioridad> colaDePrioridad;

    public Dijsktra() {
        colaDePrioridad = new ListaDoble<>();
        padres = new HashMap<>();
        tiempos = new HashMap<>();
        costos = new HashMap<>();
        bibliotecasVisitadas = new HashMap<>();
    }

    public InfoTraslado calcularRuta(String idOrigen, String idDestino, Libro libro, PrioridadEnum prioridad) throws EntradaException{
        limpiarVariables();
        this.prioridad = prioridad;
        NodoGrafo origen = RedBibliotecas.INSTANCIA.buscar(idOrigen).orElseThrow(() -> 
        new EntradaException("Biblioteca de origen no encontrada: " + idOrigen));
        NodoGrafo destino = RedBibliotecas.INSTANCIA.buscar(idDestino).orElseThrow(() -> 
        new EntradaException("Biblioteca de destino no encontrada: " + idDestino));
        return iniciarAnalisis(origen, destino, libro);
    }

    public InfoTraslado iniciarAnalisis(NodoGrafo origen, NodoGrafo destino, Libro libro) throws EntradaException {
        validarLibro(destino, libro);
        colaDePrioridad.agregar(new AuxiliarPrioridad(prioridad, origen, Long.valueOf(0), new BigDecimal(0)));
        padres.put(origen.getBiblioteca(), null);
        while(!colaDePrioridad.estaVacia()){
            AuxiliarPrioridad aux = colaDePrioridad.eliminarPrimero();
            NodoGrafo actual = aux.getNodo();
            if(!esBibliotecaYaVisitada(actual)){
                if(actual.equals(destino)){
                    return resultado(libro, origen, destino);
                }
                ListaDoble<Conexion> conexiones = actual.getConexiones();
                IteradorLista<Conexion> iterador = conexiones.getIterador();
                while(iterador.haySiguiente()){
                    Conexion conexion = iterador.getActual();
                    if(!bibliotecasVisitadas.containsKey(conexion.getBiblioAdyascente().getBiblioteca())){
                        compararDatos(aux, conexion);
                    }
                }
            }
        }
        throw new EntradaException("No se encontro ninguna forma de llegar de la biblioteca con ID: " + origen + 
                " hasta la biblioteca con ID: " + destino);
    }

    private void limpiarVariables() {
        costos.clear();
        tiempos.clear();
        padres.clear();
        bibliotecasVisitadas.clear();
        colaDePrioridad.limpiar();
    }

    private void validarLibro(NodoGrafo destino, Libro libro) {
        //metodo para validar que la biblioteca destino sea donde esta el libro
    }

    private boolean esBibliotecaYaVisitada(NodoGrafo actual) {
        if(bibliotecasVisitadas.containsKey(actual.getBiblioteca())){
            return true;
        }
        bibliotecasVisitadas.put(actual.getBiblioteca(), Boolean.TRUE);
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

    private InfoTraslado resultado(Libro libro, NodoGrafo origen, NodoGrafo destino) {
        Pila<Biblioteca> pila = new Pila<>();
        Biblioteca d = destino.getBiblioteca();
        Long tiempo = tiempos.get(d);
        BigDecimal costo = costos.get(d);
        do{
            pila.apilar(d);
            d = padres.get(d);
        } while(d != null);
        ListaSimple<Biblioteca> bibliotecas = new ListaSimple<>();
        while(!pila.estaVacia()){
            bibliotecas.agregar(pila.desapilar());
        }
        return new InfoTraslado(libro, costo, tiempo, bibliotecas);
    }
    
}
