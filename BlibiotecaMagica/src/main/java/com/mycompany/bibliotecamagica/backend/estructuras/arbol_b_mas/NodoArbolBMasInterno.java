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
public class NodoArbolBMasInterno <T extends Comparable> extends NodoArbolBMas<T>{

    private final NodoArbolBMas<T>[] hijos;
    
    public NodoArbolBMasInterno(int ordenArbol, ComparadorClaves comparador, Identificable identificador) {
        super(ordenArbol, comparador, identificador);
        hijos = new NodoArbolBMas[(max+1)];
    }

    @Override
    public String obtenerClaveMedia() {
        String claveMedia = claves[ordenArbol];
        claves[ordenArbol] = null;
        return claveMedia;
    }

    @Override
    public NodoArbolBMas obtenerNuevoDerecha() {
        var nuevo = new NodoArbolBMasInterno(ordenArbol, comparador, identificador);
        numeroClaves = ordenArbol;
        nuevo.numeroClaves = ordenArbol;
        int mitad = ordenArbol + 1;
        for(int i = 0; i <= ordenArbol; i++){
            if(i < ordenArbol){
                nuevo.claves[i] = this.claves[mitad+i];
                this.claves[mitad + i] = null;
            }
            nuevo.hijos[i] = this.hijos[mitad+i];
            this.hijos[mitad+i] = null;
        }
        return nuevo;
    }

    @Override
    public boolean esNodoHoja() {
        return false;
    }

    @Override
    public void eliminarElemento(T elemento) {
        for(int i = 0; i <= numeroClaves; i++){
            if(claves[i] == null || 
                    comparador.compare(identificador.getClave(elemento), claves[i]) < 0){
                hijos[i].eliminarElemento(elemento);
                if(hijos[i].numeroClaves < ordenArbol){
                    if(i != numeroClaves && hijos[i+1].getNumeroClaves() > ordenArbol){
                        claves[i] = hijos[i].prestarDerecha(hijos[i+1], claves[i]);
                    } else if(i != 0 && hijos[i-1].numeroClaves > ordenArbol){
                        claves[i-1] = hijos[i].prestarIzquierda(hijos[i-1], claves[i-1]);
                    } else if(i != numeroClaves){
                        hijos[i].fusionar(hijos[i+1], claves[i]);
                        hijos[i+1] = hijos[i];
                        hijos[i] = null;
                        claves[i] = null;
                        correrElementos(i);
                        numeroClaves--;
                    } else {
                        hijos[i-1].fusionar(hijos[i], claves[i-1]);
                        hijos[i] = null;
                        claves[i-1] = null;
                        numeroClaves--;
                    }
                }
                break;
            }
        }
    }

    @Override
    public void agregarElemento(T elemento) {
        for(int i = 0; i <= numeroClaves; i++){
            if(claves[i] == null || comparador.compare(identificador.getClave(elemento), claves[i]) < 0){
                hijos[i].agregarElemento(elemento);
                if(hijos[i].getNumeroClaves() == max){
                    dividirNodoHijo(i);
                }
                break;
            }
        }
    }

    @Override
    public String prestarDerecha(NodoArbolBMas nodo, String clavePadre) {
        var nodoInterno = (NodoArbolBMasInterno) nodo;
        this.claves[numeroClaves++] = clavePadre;
        this.hijos[numeroClaves] = nodoInterno.hijos[0];
        String aux = nodoInterno.claves[0];
        nodoInterno.claves[0] = null;
        nodoInterno.hijos[0] = null;
        nodoInterno.correrElementos(0);
        nodoInterno.numeroClaves--;
        return aux;
    }

    @Override
    public String prestarIzquierda(NodoArbolBMas nodo, String clavePadre) {
        var nodoInterno = (NodoArbolBMasInterno) nodo;
        int ultimaClave = nodoInterno.numeroClaves-1;
        String aux = nodoInterno.claves[ultimaClave];
        nodoInterno.claves[ultimaClave] = null;
        nodoInterno.numeroClaves--;
        this.numeroClaves++;
        String aux2 = clavePadre;
        NodoArbolBMas aux3 = nodoInterno.hijos[ultimaClave+1];
        nodoInterno.hijos[ultimaClave+1] = null;
        NodoArbolBMas<T> auxHijo;
        String auxClave;
        for(int i = 0; i < numeroClaves; i++){
            auxClave = claves[i];
            claves[i] = aux2;
            aux2 = auxClave;
            auxHijo = hijos[i];
            hijos[i] = aux3;
            aux3 = auxHijo;
        }
        auxHijo = hijos[ultimaClave];
        hijos[ultimaClave] = aux3;
        aux3 = auxHijo;
        return aux;
    }

    @Override
    public void fusionar(NodoArbolBMas nodo, String clavePadre) {
        var nodoInterno = (NodoArbolBMasInterno) nodo;
        this.claves[numeroClaves++] = clavePadre;
        int contador = 0;
        int i;
        for(i = numeroClaves; i < numeroClaves + nodoInterno.numeroClaves; i++){
            hijos[i] = nodoInterno.hijos[contador];
            claves[i] = nodoInterno.claves[contador];
            nodoInterno.hijos[contador] = null;
            nodoInterno.claves[contador] = null;
            contador++;
        }
        hijos[i] = nodoInterno.hijos[contador];
        nodoInterno.hijos[contador] = null;
        numeroClaves += nodoInterno.numeroClaves;
        nodoInterno.numeroClaves = 0;
    }

    @Override
    public ListaDoble<T> buscarElemento(T elemento) {
        for(int i = 0; i < numeroClaves; i++){
            if(comparador.compare(identificador.getClave(elemento), claves[i]) < 0){
                return hijos[i].buscarElemento(elemento);
            }
        }
        return hijos[numeroClaves].buscarElemento(elemento);
    }

    private void correrElementos(int posicion) {
        int i;
        NodoArbolBMas<T> aux;
        String auxClave;
        for(i = posicion; i < numeroClaves; i++){
            aux = hijos[i];
            hijos[i] = hijos[i+1];
            hijos[i+1] = aux;
            auxClave = claves[i];
            claves[i] = claves[i+1];
            claves[i+1] = auxClave;
        }
        aux = hijos[i];
        hijos[i] = hijos[i+1];
        hijos[i+1] = aux;
    }

    private void dividirNodoHijo(int posicion) {
        String claveAux = claves[posicion];
        claves[posicion] = hijos[posicion].obtenerClaveMedia();
        NodoArbolBMas aux = hijos[posicion+1];
        hijos[posicion+1] = hijos[posicion].obtenerNuevoDerecha();
        numeroClaves++;
        String aux1;
        NodoArbolBMas aux2;
        for(int i = posicion+1; i < numeroClaves; i++){
            aux2 = hijos[i+1];
            hijos[i+1] = aux;
            aux = aux2;
            aux1 = claves[i];
            claves[i] = claveAux;
            claveAux = aux1;
        }
    }

    public NodoArbolBMas<T>[] getHijos() {
        return hijos;
    }
    
}
