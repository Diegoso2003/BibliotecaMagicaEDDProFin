/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.arbol_avl;

import com.mycompany.bibliotecamagica.backend.estructuras.lista_doble.ListaDoble;

/**
 *
 * @author rafael-cayax
 */
public class NodoAvl<T extends Comparable> {
    private NodoAvl<T> derecha = null;
    private NodoAvl<T> izquierda = null;
    private int altura = 1;
    private ListaDoble<T> lista;

    public NodoAvl(T elemento) {
        lista = new ListaDoble<>(true);
        lista.agregar(elemento);
    }

    public NodoAvl<T> getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoAvl<T> derecha) {
        this.derecha = derecha;
    }

    public NodoAvl<T> getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoAvl<T> izquierda) {
        this.izquierda = izquierda;
    }
    
    public boolean esNodoHoja(){
        return izquierda == null && derecha == null;
    }
    
    public int getFe(){
        int alturaDerecha = derecha == null ? 0 : derecha.altura;
        int alturaIzquierda = izquierda == null ? 0 : izquierda.altura;
        return alturaDerecha - alturaIzquierda;
    }
    
    public void recalcularAltura(){
        int alturaDerecha = derecha == null ? 0 : derecha.altura;
        int alturaIzquierda = izquierda == null ? 0 : izquierda.altura;
        altura = Math.max(alturaDerecha, alturaIzquierda) + 1;
    }
    
    public T obtenerPrimero(){
        return lista.obtenerPrimero();
    }

    public ListaDoble<T> getLista() {
        return lista;
    }

    public void setLista(ListaDoble<T> lista) {
        this.lista = lista;
    }
    
}
