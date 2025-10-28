/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.tabla_hash;

/**
 *
 * @author rafael-cayax
 */
public class NodoHash<K, V> {
    private final K clave;
    private V valor;
    private final int hash;
    private NodoHash<K, V> siguiente;

    public NodoHash(K clave, V valor, int hash) {
        this.clave = clave;
        this.valor = valor;
        this.hash = hash;
    }

    public NodoHash<K, V> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoHash<K, V> siguiente) {
        this.siguiente = siguiente;
    }

    public K getClave() {
        return clave;
    }

    public V getValor() {
        return valor;
    }

    public int getHash() {
        return hash;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }
    
}