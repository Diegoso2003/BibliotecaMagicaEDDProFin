/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.lista_doble;

import com.mycompany.bibliotecamagica.backend.estructuras.lista_simple.NodoSimple;

/**
 *
 * @author rafael-cayax
 */
public class NodoDoble<T> extends NodoSimple<T> implements Comparable<NodoSimple<T>>{
    private NodoDoble<T> anterior;
    
    public NodoDoble(T elemento) {
        super(elemento);
    }

    public NodoDoble<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble<T> anterior) {
        this.anterior = anterior;
    }
    
    @Override
    public NodoDoble<T> getSiguiente(){
        return (NodoDoble<T>)siguiente;
    }

    @Override
    public int compareTo(NodoSimple<T> o) {
        if(o.getElemento() instanceof Comparable otro){
            Comparable actual = (Comparable)elemento;
            return actual.compareTo(otro);
        }
        return -1;
    }

}