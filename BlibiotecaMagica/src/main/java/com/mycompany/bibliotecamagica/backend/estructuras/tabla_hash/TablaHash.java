/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.tabla_hash;

import com.mycompany.bibliotecamagica.backend.modelos.InfoLibro;
import com.mycompany.bibliotecamagica.backend.modelos.Libro;
import com.mycompany.bibliotecamagica.backend.modelos.LibroBiblioteca;

/**
 *
 * @author rafael-cayax
 */
public class TablaHash {

    private static final float FACTOR_CARGA = 0.8f;
    private static final int[] PRIMOS = {17, 37, 79, 163, 331, 673, 1361};
    private int posicion = 0;
    private int numLibros = 0;
    private NodoHash[] libros;

    public TablaHash() {
        libros = new NodoHash[PRIMOS[posicion]];
    }

    public boolean agregar(InfoLibro libro) {
        int indice = obtenerIndice(libro.getLibro());
        NodoHash nodo = libros[indice];
        numLibros++;
        if (nodo == null) {
            LibroBiblioteca nuevo = new LibroBiblioteca(libro);
            libros[indice] = new NodoHash(nuevo);
            verificarFactorCarga();
            return true;
        }
        NodoHash anterior = null;
        do {
            if (nodo.getLibro().getLibro().equals(libro.getLibro())) {
                nodo.getLibro().aumentarIndices(libro);
                numLibros--;
                return false;
            }
            anterior = nodo;
            nodo = nodo.getSiguiente();
        } while (nodo != null);
        LibroBiblioteca nuevo = new LibroBiblioteca(libro);
        anterior.setSiguiente(new NodoHash(nuevo));
        verificarFactorCarga();
        return true;
    }

    private int obtenerIndice(Libro libro) {
        Long valor = Long.valueOf(libro.getSinGuiones().substring(3));
        double A = 0.6180339887;
        double fraccion = (valor * A) % 1;
        return (int) (libros.length * fraccion);
    }

    private void verificarFactorCarga() {
        if (factorCarga() >= FACTOR_CARGA) {
            redimensionar();
        }
    }

    private double factorCarga() {
        return (double) numLibros / libros.length;
    }

    public String obtenerDotTabla(){
        GeneradorDotTablaHash generador = new GeneradorDotTablaHash();
        return generador.getDotTablaHash(libros, factorCarga());
    }
    
    private void redimensionar() {
        if (posicion < PRIMOS.length - 1) {
            NodoHash[] vieja = libros;
            NodoHash[] nueva = new NodoHash[PRIMOS[++posicion]];
            libros = nueva;
            for (NodoHash nodo : vieja) {
                if(nodo != null){
                    agregar(nodo.getLibro());
                    while(nodo.getSiguiente() != null){
                        nodo = nodo.getSiguiente();
                        agregar(nodo.getLibro());
                    }
                }
            }
        }
    }
    
    private void agregar(LibroBiblioteca libro){
        int indice = obtenerIndice(libro.getLibro());
        NodoHash nodo = libros[indice];
        if(nodo == null){
            libros[indice] = new NodoHash(libro);
        } else {
            while(nodo.getSiguiente() != null){
                nodo = nodo.getSiguiente();
            }
            nodo.setSiguiente(new NodoHash(libro));
        }
    }
    
    public LibroBiblioteca buscar(Libro libro){
        int indice = obtenerIndice(libro);
        NodoHash nodo = libros[indice];
        if(nodo == null){
            return null;
        }
        do{
            if(nodo.getLibro().getLibro().equals(libro)){
                return nodo.getLibro();
            }
            nodo = nodo.getSiguiente();
        } while(nodo != null);
        return null;
    }
}
