/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.tabla_hash;

import com.mycompany.bibliotecamagica.backend.modelos.LibroBiblioteca;

/**
 *
 * @author rafael-cayax
 */
public class NodoHash {
    private final LibroBiblioteca libro;
    private NodoHash siguiente;

    public NodoHash(LibroBiblioteca libro) {
        this.libro = libro;
    }

    public void setSiguiente(NodoHash siguiente) {
        this.siguiente = siguiente;
    }

    public LibroBiblioteca getLibro() {
        return libro;
    }

    public NodoHash getSiguiente() {
        return siguiente;
    }
    
}
