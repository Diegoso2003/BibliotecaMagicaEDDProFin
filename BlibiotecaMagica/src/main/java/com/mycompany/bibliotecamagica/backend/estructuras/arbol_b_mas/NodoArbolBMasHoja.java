/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.arbol_b_mas;

import com.mycompany.bibliotecamagica.backend.comparadores.ComparadorClaves;
import com.mycompany.bibliotecamagica.backend.comparadores.Identificable;
import com.mycompany.bibliotecamagica.backend.estructuras.lista_doble.ListaDoble;

/**
 *
 * @author rafael-cayax
 */
public class NodoArbolBMasHoja<T extends Comparable> extends NodoArbolBMas<T> {

    private ListaDoble<T>[] elementos;
    
    public NodoArbolBMasHoja(int ordenArbol, ComparadorClaves comparador, Identificable<T> identificador) {
        super(ordenArbol, comparador, identificador);
        elementos = new ListaDoble[max];
    }

    @Override
    public String obtenerClaveMedia() {
        return claves[ordenArbol];
    }

    @Override
    public NodoArbolBMas<T> obtenerNuevoDerecha() {
        var hoja = new NodoArbolBMasHoja(ordenArbol, comparador, identificador);
        numeroClaves = ordenArbol;
        hoja.numeroClaves = ordenArbol + 1;
        for(int i = 0; i <= ordenArbol; i++){
            hoja.claves[i] = claves[ordenArbol + i];
            hoja.elementos[i] = elementos[ordenArbol + i];
            claves[ordenArbol + i] = null;
            elementos[ordenArbol + i] = null;
        }
        return hoja;
    }

    @Override
    public boolean esNodoHoja() {
        return true;
    }

    @Override
    public void eliminarElemento(T elemento) {
        for(int i = 0; i < numeroClaves; i++){
            if(comparador.compare(identificador.getClave(elemento), claves[i]) == 0){
                elementos[i].eliminar(elemento);
                if(elementos[i].estaVacia()){
                    claves[i] = null;
                    numeroClaves--;
                    correrElementos(i);
                }
                break;
            }
        }
    }

    @Override
    public void agregarElemento(T elemento) {
        String aux = "";
        String aux2;
        ListaDoble<T> auxElemento = null;
        ListaDoble<T> auxElemento2;
        boolean agregado = false;
        numeroClaves++;
        for(int i = 0; i < numeroClaves; i++){
            if(agregado){
                aux2 = claves[i];
                claves[i] = aux;
                aux = aux2;
                auxElemento2 = elementos[i];
                elementos[i] = auxElemento;
                auxElemento = auxElemento2;
            } else {
                if(claves[i] == null || comparador.compare(claves[i], identificador.getClave(elemento)) > 0){
                    aux = claves[i];
                    auxElemento = elementos[i];
                    claves[i] = identificador.getClave(elemento);
                    elementos[i] = new ListaDoble<>(false);
                    elementos[i].agregar(elemento);
                    agregado = true;
                } else if(comparador.compare(claves[i], identificador.getClave(elemento)) == 0){
                    elementos[i].agregar(elemento);
                    numeroClaves--;
                    return;
                }
            }
        }
    }

    @Override
    public String prestarDerecha(NodoArbolBMas<T> nodo, String clavePadre) {
        var nodoHoja = (NodoArbolBMasHoja) nodo;
        elementos[numeroClaves] = nodoHoja.elementos[0];
        claves[numeroClaves] = nodoHoja.claves[0];
        numeroClaves++;
        String aux = nodoHoja.claves[1];
        nodoHoja.elementos[0] = null;
        nodoHoja.claves[0] = null;
        nodoHoja.correrElementos(0);
        nodoHoja.numeroClaves--;
        return aux;
    }

    @Override
    public String prestarIzquierda(NodoArbolBMas<T> nodo, String clavePadre) {
        var nodoHoja = (NodoArbolBMasHoja) nodo;
        int ultimoElemento = nodoHoja.numeroClaves - 1;
        nodoHoja.numeroClaves--;
        numeroClaves++;
        ListaDoble<T> aux = nodoHoja.elementos[ultimoElemento];
        String auxClave = nodoHoja.claves[ultimoElemento];
        String aux2Clave = nodoHoja.claves[ultimoElemento];
        ListaDoble<T> auxElemento;
        String auxClave2;
        for(int i = 0; i < numeroClaves; i++){
            auxElemento = elementos[i];
            elementos[i] = aux;
            aux = auxElemento;
            auxClave2 = claves[i];
            claves[i] = auxClave;
            auxClave = auxClave2;
        }
        nodoHoja.claves[ultimoElemento] = null;
        return aux2Clave;
    }

    @Override
    public void fusionar(NodoArbolBMas nodo, String clave) {
        var nodoHoja = (NodoArbolBMasHoja) nodo;
        int contador = 0;
        for(int i = numeroClaves; i < numeroClaves + nodoHoja.numeroClaves; i++){
            claves[i] = nodoHoja.claves[contador];
            elementos[i] = nodoHoja.elementos[contador];
            nodoHoja.claves[contador] = null;
            nodoHoja.elementos[contador] = null;
            contador++;
        }
        numeroClaves += nodoHoja.numeroClaves;
        nodoHoja.numeroClaves = 0;
    }

    @Override
    public ListaDoble<T> buscarElemento(T elemento) {
        for(int i = 0; i < numeroClaves; ++i){
            if(comparador.compare(identificador.getClave(elemento), claves[i]) == 0){
                return elementos[i];
            }
        }
        return null;
    }

    private void correrElementos(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ListaDoble<T>[] getElementos() {
        return elementos;
    }
    
}
