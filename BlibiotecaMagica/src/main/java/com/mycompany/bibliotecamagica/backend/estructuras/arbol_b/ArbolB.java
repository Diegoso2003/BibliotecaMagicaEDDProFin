/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.arbol_b;

import com.mycompany.bibliotecamagica.backend.comparadores.ComparadorAño;
import com.mycompany.bibliotecamagica.backend.estructuras.lista_doble.ListaDoble;
import com.mycompany.bibliotecamagica.backend.estructuras.lista_simple.ListaSimple;
import com.mycompany.bibliotecamagica.backend.modelos.Libro;
import com.mycompany.bibliotecamagica.backend.modelos.ListaLibros;

/**
 *
 * @author rafael-cayax
 */
public class ArbolB {
    private final ComparadorAño comparador;
    private NodoArbolB<ListaLibros> raiz;
    private static final int ORDEN_ARBOL = 3;
    private static final int MAX_ELEMENTOS = 2 *ORDEN_ARBOL;
    private int numElementos = 0;

    public ArbolB() {
        this.comparador = new ComparadorAño();
    }
    
    private void dividirRaiz(){
        
    }
    
    private void agregarListaElementos(AuxiliarBusquedaB aux, NodoArbolB nodo){
        
    }
    
    private void agregarElemento(NodoArbolB<ListaLibros> nodo, ListaLibros nuevo){
        if(nodo.esNodoHoja()){
            nodo.agregarElemento(nuevo);
            return;
        }
        ListaDoble<ListaLibros> []libros = nodo.getClaves();
        NodoArbolB<ListaLibros> []hijos = nodo.getHijos();
        for(int i = 0; i <= nodo.getNumeroClaves(); i++){
            Libro[] claves;
            if(libros[i] != null && comparador.compare(nuevo.obtenerPrimero().getLibro(), 
                    libros[i].obtenerPrimero().obtenerPrimero().getLibro()) == 0){
                
            }
        }
    }
    
    public void agregarLibro(ListaLibros nuevo){
        
    }
    
    public String obtenerDotAño(){
        return "";
    }
    
    public boolean estaVacia(){
        return numElementos == 0;
    }
    
    public ListaSimple<ListaLibros> obtenerListaPorRango(String texto){
        
    }
}
