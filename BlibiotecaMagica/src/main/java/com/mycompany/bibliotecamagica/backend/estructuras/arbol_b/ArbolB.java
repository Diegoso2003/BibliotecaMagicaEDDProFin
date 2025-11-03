/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.arbol_b;

import com.mycompany.bibliotecamagica.backend.comparadores.ComparadorAño;
import com.mycompany.bibliotecamagica.backend.estructuras.lista_doble.ListaDoble;
import com.mycompany.bibliotecamagica.backend.estructuras.lista_simple.ListaSimple;
import com.mycompany.bibliotecamagica.backend.modelos.LibroBiblioteca;

/**
 *
 * @author rafael-cayax
 */
public class ArbolB {
    private final ComparadorAño comparador;
    private NodoArbolB<LibroBiblioteca> raiz;
    private static final int ORDEN_ARBOL = 3;
    private static final int MAX_ELEMENTOS = 2 *ORDEN_ARBOL;
    private int numElementos = 0;

    public ArbolB() {
        this.comparador = new ComparadorAño();
        raiz = new NodoArbolB(ORDEN_ARBOL, comparador);
    }
    
    private void dividirRaiz(){
        var nuevo = new NodoArbolB(ORDEN_ARBOL, comparador);
        NodoArbolB<LibroBiblioteca>[] hijos = nuevo.getHijos();
        ListaDoble<LibroBiblioteca>[] claves = nuevo.getClaves();
        hijos[0] = raiz;
        hijos[1] = raiz.obtenerNuevoDerecho();
        claves[0] = raiz.obtenerMedio();
        raiz = nuevo;
        raiz.setEsHoja(false);
        raiz.setNumeroClaves(1);
    }
    
    private void agregarListaElementos(AuxiliarBusquedaB aux, NodoArbolB nodo) {
        if (nodo == null) {
            return;
        }
        ListaDoble<LibroBiblioteca>[] claves = nodo.getClaves();
        NodoArbolB<LibroBiblioteca>[] hijos = nodo.getHijos();
        for (int i = 0; i <= nodo.getNumeroClaves() && aux.seguirBuscando(); i++) {
            if (claves[i] != null) {
                int actual = claves[i].obtenerPrimero().getLibro().getAño();
                if (actual >= aux.fechaInicio() && actual <= aux.fechaFin()) {
                    if (actual != aux.fechaInicio()) {
                        agregarListaElementos(aux, hijos[i]);
                    }
                    aux.getLista().agregarLista(claves[i]);
                    if (actual == aux.fechaFin()) {
                        aux.pararBusqueda();
                    }
                    continue;
                }
                if (nodo.esNodoHoja() && actual > aux.fechaFin()) {
                    aux.pararBusqueda();
                }
            }
            if (claves[i] == null || aux.fechaInicio() < claves[i].obtenerPrimero().getLibro().getAño()) {
                agregarListaElementos(aux, hijos[i]);
                if (aux.esFechaUnica()) {
                    break;
                }
            }
        }
    }
    
    private void agregarElemento(NodoArbolB<LibroBiblioteca> nodo, LibroBiblioteca nuevo){
        if(nodo.esNodoHoja()){
            nodo.agregarElemento(nuevo);
            return;
        }
        ListaDoble<LibroBiblioteca> []libros = nodo.getClaves();
        NodoArbolB<LibroBiblioteca> []hijos = nodo.getHijos();
        for(int i = 0; i <= nodo.getNumeroClaves(); i++){
            if(libros[i] != null && comparador.compare(nuevo, 
                    libros[i].obtenerPrimero()) == 0){
                libros[i].agregar(nuevo);
                break;
            }
            if(libros[i] == null || comparador.compare(nuevo, libros[i].obtenerPrimero()) < 0){
                agregarElemento(hijos[i], nuevo);
                if(hijos[i].getNumeroClaves() > MAX_ELEMENTOS){
                    nodo.dividirNodoHijo(i);
                }
                break;
            }
        }
    }
    
    public void agregarLibro(LibroBiblioteca nuevo){
        agregarElemento(raiz, nuevo);
        if(raiz.getNumeroClaves() > MAX_ELEMENTOS){
            dividirRaiz();
        }
    }
    
    public String obtenerDotAño(){
        GeneradorDotB gen = new GeneradorDotB();
        return gen.generar(raiz);
    }
    
    public boolean estaVacia(){
        return numElementos == 0;
    }
    
    public ListaSimple<LibroBiblioteca> obtenerListaPorRango(String texto){
        int fechas[] = {-1, -1};
        var lista = new ListaSimple<LibroBiblioteca>();
        return lista;
    }

    public ListaSimple<LibroBiblioteca> getListaPorRango(AuxiliarBusquedaB aux) {
        ListaSimple<LibroBiblioteca> lista = new ListaSimple<>();
        aux.setLista(lista);
        agregarListaElementos(aux, raiz);
        return lista;
    }
    
}
