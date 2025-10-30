/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras;

import com.mycompany.bibliotecamagica.backend.estructuras.lista_simple.NodoSimple;

/**
 *
 * @author rafael-cayax
 */
public class Pila<T> {
    private NodoSimple<T> primero;
    private int numElementos;
    
    public void apilar(T elemento){
        NodoSimple<T> nuevo = new NodoSimple<>(elemento);
        nuevo.setSiguiente(primero);
        primero = nuevo;
        numElementos++;
    }
    
    public T desapilar(){
        if(estaVacia()) return null;
        NodoSimple<T> aux = primero;
        primero = primero.getSiguiente();
        numElementos--;
        return aux.getElemento();
    }
    
    public boolean estaVacia(){
        return numElementos == 0;
    }
}
