/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.iterador;

import com.mycompany.bibliotecamagica.backend.estructuras.lista_simple.NodoSimple;

/**
 *
 * @author rafael-cayax
 */
public class IteradorLista<T> {
    private NodoSimple<T> actual;

    public IteradorLista(NodoSimple<T> actual) {
        this.actual = new NodoSimple<>(null);
        this.actual.setSiguiente(actual);
    }
    
    public boolean haySiguiente(){
        actual = actual.getSiguiente();
        return actual != null;
    }

    public NodoSimple<T> getActual() {
        return actual;
    }
    
}