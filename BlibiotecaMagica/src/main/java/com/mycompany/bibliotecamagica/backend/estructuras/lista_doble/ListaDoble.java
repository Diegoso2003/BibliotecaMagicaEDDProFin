/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.lista_doble;

import com.mycompany.bibliotecamagica.backend.estructuras.lista_simple.ListaSimple;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class ListaDoble<T extends Comparable> extends ListaSimple<T>{
    
    private final boolean duplicados;

    public ListaDoble(boolean duplicados) {
        this.duplicados = duplicados;
    }
    
    @Override
    public boolean agregar(T elemento){
        NodoDoble<T> nuevo = new NodoDoble<>(elemento);
        if(estaVacia()){
            primero = nuevo;
            ultimo = nuevo;
        } else if(!agregarExtremos(nuevo) && !agregarEnMedio(nuevo)){
            return false;
        }
        numElementos++;
        return true;
    }
    
    public Optional<T> buscar(T elemento){
        NodoDoble<T> nodo = new NodoDoble<>(elemento);
        if(nodo.compareTo(primero) == 0){
            return Optional.of(primero.getElemento());
        } 
        if (nodo.compareTo(ultimo) == 0){
            return Optional.of(ultimo.getElemento());
        } 
        if(nodo.compareTo(primero) < 0 || nodo.compareTo(ultimo) > 0){
            return Optional.empty();
        }
        if(deboAvanzarDesdeInicio(nodo)){
            return buscarDesdeInicio(nodo);
        }
        return buscarDesdeFinal(nodo);
    }

    private boolean agregarExtremos(NodoDoble<T> nuevo) {
        NodoDoble<T> p = (NodoDoble)primero;
        if(nuevo.compareTo(p) < 0 || (nuevo.compareTo(p) == 0 && duplicados)){
            nuevo.setSiguiente(primero);
            p.setAnterior(nuevo);
            primero = nuevo;
            return true;
        }
        NodoDoble<T> u = (NodoDoble)ultimo;
        if(nuevo.compareTo(u) > 0 || (nuevo.compareTo(u) == 0 && duplicados)){
            nuevo.setAnterior(u);
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
            return true;
        }
        return false;
    }

    private boolean agregarEnMedio(NodoDoble<T> nuevo) {
        if(deboAvanzarDesdeInicio(nuevo)){
            return agregarDesdeInicio(nuevo);
        } else {
            return agregarDesdeFinal(nuevo);
        }
    }

    private boolean deboAvanzarDesdeInicio(NodoDoble<T> nuevo) {
        NodoDoble<T> p = (NodoDoble)primero;
        NodoDoble<T> u = (NodoDoble)ultimo;
        int distanciaP = nuevo.compareTo(p);
        int distanciaU = u.compareTo(nuevo);
        return distanciaP < distanciaU;
    }

    private boolean agregarDesdeInicio(NodoDoble<T> nuevo) {
        NodoDoble<T> actual = (NodoDoble)primero;
        while(nuevo.compareTo(actual) > 0){
            actual = actual.getSiguiente();
        }
        if(actual.compareTo(nuevo) != 0 || duplicados){
            nuevo.setAnterior(actual.getAnterior());
            actual.getAnterior().setSiguiente(nuevo);
            actual.setAnterior(nuevo);
            nuevo.setSiguiente(actual);
            return true;
        }
        return false;
    }

    private boolean agregarDesdeFinal(NodoDoble<T> nuevo) {
        NodoDoble<T> actual = (NodoDoble)ultimo;
        while(nuevo.compareTo(actual) < 0){
            actual = actual.getAnterior();
        }
        if(actual.compareTo(nuevo) != 0 || duplicados){
            nuevo.setAnterior(actual);
            nuevo.setSiguiente(actual.getSiguiente());
            actual.getSiguiente().setAnterior(nuevo);
            actual.setSiguiente(nuevo);
            return true;
        }
        return false;
    }
    
    private Optional<T> buscarDesdeInicio(NodoDoble<T> nodo){
        NodoDoble<T> actual = (NodoDoble)primero;
        while(nodo.compareTo(actual) > 0){
            actual = actual.getSiguiente();
        }
        if(actual.compareTo(nodo) == 0) return Optional.of(actual.getElemento());
        return Optional.empty();
    }
    
    private Optional<T> buscarDesdeFinal(NodoDoble<T> nodo){
        NodoDoble<T> actual = (NodoDoble)ultimo;
        while(nodo.compareTo(actual) < 0){
            actual = actual.getAnterior();
        }
        if(actual.compareTo(nodo) == 0) return Optional.of(actual.getElemento());
        return Optional.empty();
    }
    
    public void eliminar(T elemento){
        
    }
}