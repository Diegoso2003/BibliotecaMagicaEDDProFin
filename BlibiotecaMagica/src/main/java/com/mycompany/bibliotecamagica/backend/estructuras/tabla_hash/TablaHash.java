/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.tabla_hash;

import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class TablaHash<K, V> {

    private static final float FACTOR_DE_CARGA = 0.75f;
    private NodoHash<K, V>[] tabla = new NodoHash[16];
    private int numElementos = 0;

    public void agregar(K clave, V valor) {
        int indice = obtenerIndice(obtenerHash(clave));
        NodoHash<K, V> nuevo = crearNodo(clave, valor);
        if (agregarNodo(nuevo, indice)) {
            numElementos++;
            if (debeRedimensionarse()) {
                redimensionar();
            }
        }
    }

    public int getNumElementos() {
        return numElementos;
    }

    public boolean estaVacia() {
        return numElementos == 0;
    }

    private int obtenerIndice(int hash) {
        return hash % tabla.length;
    }

    private int obtenerHash(K clave) {
        if (clave == null) {
            return 0;
        }
        String s = clave.toString();
        int h = 0;
        for (int i = 0; i < s.length(); i++) {
            h = 31 * h + s.charAt(i);
        }
        return h;
    }

    private NodoHash<K, V> crearNodo(K clave, V valor) {
        return new NodoHash<>(clave, valor, obtenerHash(clave));
    }

    private boolean debeRedimensionarse() {
        return ((float) numElementos / tabla.length) > FACTOR_DE_CARGA;
    }

    private void redimensionar() {
        NodoHash<K, V> vieja[] = tabla;
        NodoHash<K, V> nuevaTabla[] = new NodoHash[tabla.length * 2];
        tabla = nuevaTabla;
        NodoHash<K, V> auxiliar;
        for (int i = 0; i < vieja.length; i++) {
            if (vieja[i] != null) {
                NodoHash<K, V> nodo = vieja[i];
                while (nodo != null) {
                    auxiliar = nodo.getSiguiente();
                    nodo.setSiguiente(null);
                    agregarNodo(nodo, obtenerIndice(nodo.getHash()));
                    nodo = auxiliar;
                }
            }
        }
    }

    private boolean agregarNodo(NodoHash<K, V> nodo, int indice) {
        NodoHash<K, V> actual = tabla[indice];
        if (actual == null) {
            tabla[indice] = nodo;
        } else {
            NodoHash<K, V> anterior = null;
            while (actual != null) {
                if (actual.getHash() == nodo.getHash() && (actual.getClave() == nodo.getClave()
                        || actual.getClave().equals(nodo.getClave()))) {
                    actual.setValor(nodo.getValor());
                    return false;
                }
                anterior = actual;
                actual = actual.getSiguiente();
            }
            anterior.setSiguiente(nodo);
        }
        return true;
    }

    public Optional<V> obtenerValor(K clave) {
        int hash = obtenerHash(clave);
        NodoHash<K, V> nodo = tabla[obtenerIndice(hash)];
        while (nodo != null) {
            if (nodo.getHash() == hash && (nodo.getClave() == clave || clave.equals(nodo.getClave()))) {
                return Optional.of(nodo.getValor());
            }
        }
        return Optional.empty();
    }

    public V eliminar(K clave) {
        int hash = obtenerHash(clave);
        int indice = obtenerIndice(hash);
        NodoHash<K, V> nodo = tabla[indice];
        if (nodo.getHash() == hash && (nodo.getClave() == clave || clave.equals(nodo.getClave()))) {
            tabla[indice] = nodo.getSiguiente();
            numElementos--;
            return nodo.getValor();
        }
        NodoHash<K, V> anterior = nodo;
        nodo = nodo.getSiguiente();
        while (nodo != null) {
            if (nodo.getHash() == hash && (nodo.getClave() == clave || clave.equals(nodo.getClave()))) {
                anterior.setSiguiente(nodo.getSiguiente());
                numElementos--;
                return nodo.getValor();
            }
            anterior = nodo;
            nodo = nodo.getSiguiente();
        }
        return null;
    }
}
