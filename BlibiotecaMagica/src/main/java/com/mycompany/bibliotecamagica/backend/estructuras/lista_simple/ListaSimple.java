/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.lista_simple;

import com.mycompany.bibliotecamagica.backend.iterador.IteradorLista;

/**
 *
 * @author rafael-cayax
 */
public class ListaSimple<T> {
    protected NodoSimple<T> primero;
    protected NodoSimple<T> ultimo;
    protected int numElementos = 0;
    
    public boolean agregar(T elemento){
        NodoSimple<T> nuevo = new NodoSimple<>(elemento);
        if(estaVacia()){
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }
        numElementos++;
        return true;
    }
    
    public void agregarLista(ListaSimple<T> lista){
        IteradorLista<T> iterador = lista.getIterador();
        while(iterador.haySiguiente()){
            agregar(iterador.getActual());
        }
    }
    
    public boolean estaVacia(){
        return numElementos == 0;
    }
    
    public IteradorLista<T> getIterador(){
        return new IteradorLista<>(primero);
    }

    public int getNumElementos() {
        return numElementos;
    }
    
}