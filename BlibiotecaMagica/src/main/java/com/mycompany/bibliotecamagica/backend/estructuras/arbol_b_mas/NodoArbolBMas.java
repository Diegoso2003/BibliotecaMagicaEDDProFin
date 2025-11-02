/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.arbol_b_mas;

import com.mycompany.bibliotecamagica.backend.comparadores.ComparadorClaves;
import com.mycompany.bibliotecamagica.backend.comparadores.Identificable;
import com.mycompany.bibliotecamagica.backend.estructuras.lista_doble.ListaDoble;

/**
 *
 * @author rafael-cayax
 */
public abstract class NodoArbolBMas <T extends Comparable>{
    protected int numeroClaves = 0;
    protected final int ordenArbol;
    protected final ComparadorClaves comparador;
    protected final Identificable<T> identificador;
    protected String[] claves;
    protected int max;

    public NodoArbolBMas(int ordenArbol, ComparadorClaves comparador, Identificable<T> identificador) {
        this.ordenArbol = ordenArbol;
        this.comparador = comparador;
        this.identificador = identificador;
        max = ordenArbol*2+1;
        claves = new String[max];
    }

    public int getNumeroClaves() {
        return numeroClaves;
    }

    public String[] getClaves() {
        return claves;
    }
    
    public abstract String obtenerClaveMedia();
    public abstract NodoArbolBMas<T> obtenerNuevoDerecha();
    public abstract boolean esNodoHoja();
    public abstract void eliminarElemento(T elemento);
    public abstract void agregarElemento(T elemento);
    public abstract String prestarDerecha(NodoArbolBMas<T> nodo, String clavePadre);
    public abstract String prestarIzquierda(NodoArbolBMas<T> nodo, String clavePadre);
    public abstract void fusionar(NodoArbolBMas nodo, String clave);
    public abstract ListaDoble<T> buscarElemento(T elemento);
}