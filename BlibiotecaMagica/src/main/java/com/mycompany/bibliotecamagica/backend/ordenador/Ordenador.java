/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.ordenador;

import com.mycompany.bibliotecamagica.backend.comparadores.Comparador;
import com.mycompany.bibliotecamagica.backend.estructuras.lista_simple.ListaSimple;
import com.mycompany.bibliotecamagica.backend.iterador.IteradorLista;
import com.mycompany.bibliotecamagica.backend.modelos.LibroBiblioteca;

/**
 *
 * @author rafael-cayax
 */
public abstract class Ordenador {
    
    private String mensaje;
    private final String nombre;

    public Ordenador(String nombre) {
        this.nombre = nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    public LibroBiblioteca[] ordenar(Comparador comparador, ListaSimple<LibroBiblioteca> lista){
        LibroBiblioteca[] arreglo = new LibroBiblioteca[lista.getNumElementos()];
        IteradorLista<LibroBiblioteca> iterador = lista.getIterador();
        int contador = 0;
        while(iterador.haySiguiente()){
            arreglo[contador++] = iterador.getActual();
        }
        long inicio = System.nanoTime();
        arreglo = realizarAlgoritmo(arreglo, comparador);
        long termino = System.nanoTime();
        long tiempo = inicio - termino;
        mensaje = "El algoritmo " + nombre + " tardo en " + comparador + " "+ tiempo + "us";
        return arreglo;
    }
    
    public abstract LibroBiblioteca[] realizarAlgoritmo(LibroBiblioteca[] arreglo, Comparador comparador);
}
