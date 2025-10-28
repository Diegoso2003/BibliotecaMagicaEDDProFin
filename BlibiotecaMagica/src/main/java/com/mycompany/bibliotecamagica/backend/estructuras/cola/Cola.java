/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.cola;

import com.mycompany.bibliotecamagica.backend.estructuras.lista_simple.NodoSimple;

/**
 *
 * @author rafael-cayax
 */
public class Cola<T> {
    
    protected NodoSimple<T> primero;
    protected NodoSimple<T> ultimo;
    protected int numElementos = 0;
        
    public void agregar(T elemento){
        NodoSimple<T> nuevo = new NodoSimple<>(elemento);
        if(estaVacia()){
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }
        numElementos++;
    }
    
    public T obtenerPrimeroCola(){
        if(estaVacia()){
            return null;
        } else {
            NodoSimple<T> nodo = primero;
            primero = primero.getSiguiente();
            numElementos--;
            return nodo.getElemento();
        }
    }
    
    public boolean estaVacia(){
        return numElementos == 0;
    }
}