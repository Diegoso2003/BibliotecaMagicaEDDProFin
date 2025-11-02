/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.arbol_b_mas;

import com.mycompany.bibliotecamagica.backend.comparadores.ComparadorClaves;
import com.mycompany.bibliotecamagica.backend.comparadores.Identificable;
import com.mycompany.bibliotecamagica.backend.estructuras.lista_doble.ListaDoble;
import com.mycompany.bibliotecamagica.backend.exception.EntradaException;

/**
 *
 * @author rafael-cayax
 */
public class ArbolBMas <T extends Comparable> {
    private static final int ORDEN_ARBOL = 3;
    private static final ComparadorClaves COMPARADOR= new ComparadorClaves();
    private static final int MAX_ELEMENTOS = ORDEN_ARBOL * 2;
    private int numeroElementos = 0;
    private final Identificable identificador;
    private NodoArbolBMas<T> raiz;

    public ArbolBMas(Identificable identificador) {
        this.identificador = identificador;
        raiz = new NodoArbolBMasHoja(ORDEN_ARBOL, COMPARADOR, identificador);
    }
    
    private void dividirRaiz(){
        var nuevo = new NodoArbolBMasInterno(ORDEN_ARBOL, COMPARADOR, identificador);
        NodoArbolBMas<T>[] hijos = nuevo.getHijos();
        String[] claves = nuevo.claves;
        hijos[0] = raiz;
        claves[0] = raiz.obtenerClaveMedia();
        hijos[1] = raiz.obtenerNuevoDerecha();
        nuevo.numeroClaves = 1;
        raiz = nuevo;
    }
    
    public void agregarElemento(T elemento){
        numeroElementos++;
        raiz.agregarElemento(elemento);
        if(raiz.numeroClaves > MAX_ELEMENTOS){
            dividirRaiz();
        }
    }
    
    public void eliminarElemento(T elemento){
        raiz.eliminarElemento(elemento);
        numeroElementos--;
        if(!raiz.esNodoHoja() && raiz.getNumeroClaves() == 0){
            var nodoInterno = (NodoArbolBMasInterno) raiz;
            NodoArbolBMas[] hijos = nodoInterno.getHijos();
            raiz = hijos[0];
        }
    }
    
    public ListaDoble<T> buscar(T elemento) throws EntradaException{
        if(estaVacia()) throw new EntradaException("Biblioteca vacia ingresar datos");
        return raiz.buscarElemento(elemento);
    }
    
    public boolean estaVacia(){
        return numeroElementos == 0;
    }
    
    public String obtenerDotArbolBMas(){
        GeneradorDotBMas generador = new GeneradorDotBMas();
        return generador.obtenerDot(raiz);
    }
}
