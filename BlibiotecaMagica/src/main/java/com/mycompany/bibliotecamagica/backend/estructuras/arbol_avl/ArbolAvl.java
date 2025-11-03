/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.arbol_avl;

import com.mycompany.bibliotecamagica.backend.estructuras.lista_doble.ListaDoble;
import java.util.Comparator;

/**
 *
 * @author rafael-cayax
 */
public class ArbolAvl<T extends Comparable> {
    private NodoAvl<T> raiz = null;
    private final Comparator<T> comparador;
    private int numElementos = 0;

    public ArbolAvl(Comparator<T> comparador) {
        this.comparador = comparador;
    }
    
    public void agregarElemento(T elemento){
        boolean[] verificarAltura = {false};
        raiz = agregarElemento(raiz, elemento, verificarAltura);
    }
    
    private NodoAvl<T> agregarElemento(NodoAvl<T> nodo, T elemento, boolean[] verificarAltura){
        if(nodo != null){
            int resultado = comparador.compare(elemento, nodo.obtenerPrimero());
            if(resultado < 0){
                nodo.setIzquierda(agregarElemento(nodo.getIzquierda(), elemento, verificarAltura));
                if(verificarAltura[0]){
                    return evaluarNodoIzquierdo(nodo);
                }
            } else if(resultado > 0){
                nodo.setDerecha(agregarElemento(nodo.getDerecha(), elemento, verificarAltura));
                if(verificarAltura[0]){
                    return evaluarNodoDerecho(nodo);
                }
            } else {
                nodo.getLista().agregar(elemento);
            }
        } else{
            nodo = new NodoAvl<>(elemento);
            verificarAltura[0] = true;
            numElementos++;
        }
        return nodo;
    }

    private NodoAvl<T> evaluarNodoIzquierdo(NodoAvl<T> nodo) {
        nodo.recalcularAltura();
        if(nodo.getFe() <= -2){
            return reorganizarArbolIzquierdo(nodo);
        }
        return nodo;
    }

    private NodoAvl<T> reorganizarArbolIzquierdo(NodoAvl<T> nodo) {
        NodoAvl<T> nodo1 = nodo.getIzquierda();
        if (nodo1.getFe() <= -1) {
            return rotacionII(nodo, nodo1);
        }
        NodoAvl<T> nodo2 = nodo1.getDerecha();
        return rotacionID(nodo, nodo1, nodo2);
    }

    private NodoAvl<T> evaluarNodoDerecho(NodoAvl<T> nodo) {
        nodo.recalcularAltura();
        if(nodo.getFe() >= 2){
            return reorganizarArbolDerecho(nodo);
        }
        return nodo;
    }

    private NodoAvl<T> reorganizarArbolDerecho(NodoAvl<T> nodo) {
        NodoAvl<T> nodo1 = nodo.getDerecha();
        if (nodo1.getFe() >= 1) {
            return rotacionDD(nodo, nodo1);
        }
        NodoAvl<T> nodo2 = nodo1.getIzquierda();
        return rotacionDI(nodo, nodo1, nodo2);
    }

    private NodoAvl<T> rotacionII(NodoAvl<T> nodo, NodoAvl<T> nodo1) {
        nodo.setIzquierda(nodo1.getDerecha());
        nodo1.setDerecha(nodo);
        nodo.recalcularAltura();
        nodo1.recalcularAltura();
        return nodo1;
    }

    private NodoAvl<T> rotacionDD(NodoAvl<T> nodo, NodoAvl<T> nodo1) {
        nodo.setDerecha(nodo1.getIzquierda());
        nodo1.setIzquierda(nodo);
        nodo.recalcularAltura();
        nodo1.recalcularAltura();
        return nodo1;
    }
    
    public boolean estaVacia(){
        return numElementos == 0;
    }

    public NodoAvl<T> getRaiz() {
        return raiz;
    }

    private NodoAvl<T> rotacionDI(NodoAvl<T> nodo, NodoAvl<T> nodo1, NodoAvl<T> nodo2) {
        nodo.setDerecha(nodo2.getIzquierda());
        nodo2.setIzquierda(nodo);
        nodo1.setIzquierda(nodo2.getDerecha());
        nodo2.setDerecha(nodo1);
        nodo.recalcularAltura();
        nodo1.recalcularAltura();
        nodo2.recalcularAltura();
        return nodo2;
    }

    private NodoAvl<T> rotacionID(NodoAvl<T> nodo, NodoAvl<T> nodo1, NodoAvl<T> nodo2) {
        nodo.setIzquierda(nodo2.getDerecha());
        nodo2.setDerecha(nodo);
        nodo1.setDerecha(nodo2.getIzquierda());
        nodo2.setIzquierda(nodo1);
        nodo.recalcularAltura();
        nodo1.recalcularAltura();
        nodo2.recalcularAltura();
        return nodo2;
    }

    private NodoAvl<T> buscarNodo(NodoAvl<T> nodo, T elemento){
        if(nodo == null) return null;
        int resultado = comparador.compare(elemento, nodo.obtenerPrimero());
        if(resultado < 0){
            return buscarNodo(nodo.getIzquierda(), elemento);
        }
        if(resultado > 0){
            return buscarNodo(nodo.getDerecha(), elemento);
        }
        return nodo;
    }
    
    public ListaDoble<T> buscar(T elemento){
        NodoAvl<T> nodo;
        return (nodo = buscarNodo(raiz, elemento)) == null ? null : nodo.getLista();
    }
}