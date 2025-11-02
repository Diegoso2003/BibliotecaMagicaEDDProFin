/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.arbol_b;

import com.mycompany.bibliotecamagica.backend.estructuras.lista_doble.ListaDoble;
import java.util.Comparator;

/**
 *
 * @author rafael-cayax
 */
public class NodoArbolB<T extends Comparable> {
    private ListaDoble<T>[] claves;
    private NodoArbolB<T>[] hijos;
    private boolean esHoja = true;
    private int numeroClaves = 0;
    private int max;
    private final int ordenArbol;
    private final Comparator<T> comparador;

    public NodoArbolB(int ordenArbol, Comparator<T> comparador) {
        this.ordenArbol = ordenArbol;
        max = 2*ordenArbol+1;
        claves = new ListaDoble[max];
        hijos = new NodoArbolB[max+1];
        this.comparador = comparador;
    }

    private NodoArbolB(NodoArbolB<T> otro){
        this.ordenArbol = otro.ordenArbol;
        comparador = otro.comparador;
        max = 2*ordenArbol+1;
        claves = new ListaDoble[max];
        hijos = new NodoArbolB[max+1];
        numeroClaves = ordenArbol;
        esHoja = otro.esHoja;
        for(int i = 0; i <= ordenArbol; i++){
            if(i < ordenArbol){
                claves[i] = otro.claves[ordenArbol+i+1];
                otro.claves[ordenArbol+i+1] = null;
            }
            hijos[i] = otro.hijos[ordenArbol+i+1];
            otro.hijos[ordenArbol+i+1] = null;
        }
    }
    
    public void agregarElemento(T elemento){
        ListaDoble<T> aux = null;
        ListaDoble<T> aux2 = null;
        boolean agregado = false;
        numeroClaves++;
        for(int i = 0; i < numeroClaves; i++){
            if(agregado){
                aux2 = claves[i];
                claves[i] = aux;
                aux = aux2;
            } else {
                if(claves[i] == null || comparador.compare(claves[i].obtenerPrimero(), elemento) > 0){
                    aux = claves[i];
                    claves[i] = new ListaDoble<>(false);
                    claves[i].agregar(elemento);
                    agregado = true;
                } else if(comparador.compare(claves[i].obtenerPrimero(), elemento) == 0){
                    claves[i].agregar(elemento);
                    numeroClaves--;
                    return;
                }
            }
        }
    }
    
    public void dividirNodoHijo(int posicion){
        NodoArbolB<T> aux = hijos[posicion+1];
        hijos[posicion+1] = hijos[posicion].obtenerNuevoDerecho();
        ListaDoble<T> listaAux = claves[posicion];
        claves[posicion] = hijos[posicion].obtenerMedio();
        numeroClaves++;
        NodoArbolB<T> aux1;
        ListaDoble<T> aux2;
        for(int i = posicion + 1; i < numeroClaves; i++){
            aux1 = hijos[i+1];
            hijos[i+1] = aux;
            aux = aux1;
            aux2 = claves[i];
            claves[i] = listaAux;
            listaAux = aux2;
        }
    }
    
    public boolean esNodoHoja(){
        return esHoja;
    }

    public void setEsHoja(boolean esHoja) {
        this.esHoja = esHoja;
    }
    
    public ListaDoble<T>[] getClaves(){
        return claves;
    }
    
    public ListaDoble<T> obtenerMedio(){
        ListaDoble<T> mitad = claves[ordenArbol];
        claves[ordenArbol] = null;
        return mitad;
    }
    
    public NodoArbolB<T> obtenerNuevoDerecho(){
        var aux = new NodoArbolB<>(this);
        numeroClaves = ordenArbol;
        return aux;
    }

    public NodoArbolB<T>[] getHijos() {
        return hijos;
    }

    public int getNumeroClaves() {
        return numeroClaves;
    }
    
}