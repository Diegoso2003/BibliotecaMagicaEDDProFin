/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.arbol_b_mas;

import com.mycompany.bibliotecamagica.backend.estructuras.lista_doble.ListaDoble;

/**
 *
 * @author rafael-cayax
 */
public class GeneradorDotBMas {

    public String obtenerDot(NodoArbolBMas raiz) {
        StringBuilder datos = new StringBuilder();
        datos.append("digraph ArbolBMas { \n")
                .append("rankdir=TB\n")
                .append("graph [ranksep=3, nodesep=0.5];\n")
                .append("node [shape=record, fillcolor=lightgreen, style=filled];\n");
        if (raiz.numeroClaves == 0) {
            datos.append("nodo1 [label=\"biblioteca sin libros\"];");
        } else {
            int numNodo = 1;
            agregarDatosRecursivos(datos, raiz, numNodo);
        }
        datos.append("}");
        System.out.println(datos);
        return datos.toString();
    }

    private void agregarDatosRecursivos(StringBuilder datos, NodoArbolBMas nodo, int numNodo) {
        datos.append(numNodo).append(" [label=\"");
        boolean esHoja = nodo.esNodoHoja();
        if(!esHoja) datos.append("<f0> | ");
        String claves[] = nodo.getClaves();
        int numClaves = nodo.numeroClaves;
        ListaDoble libros[] = null;
        if(esHoja){
            var hoja = (NodoArbolBMasHoja) nodo;
            libros = hoja.getElementos();
        }
        for(int i = 0; i < numClaves; i++){
            datos.append(claves[i]);
            if(esHoja) {
                datos.append("\\nC: ").append(libros[i].getNumElementos());
            } else {
                datos.append(" | <f").append((i+1)).append(">");
            }
            if(i == numClaves - 1){
                datos.append("\"];\n");
            } else {
                datos.append(" | ");
            }
        }
        if(esHoja) return;
        int numeroPadre = numNodo;
        var nodoInterno = (NodoArbolBMasInterno) nodo;
        NodoArbolBMas []hijos = nodoInterno.getHijos();
        for(int i = 0; i <= numClaves; i++){
            int numeroHijo = ++numNodo;
            agregarDatosRecursivos(datos, hijos[i], numNodo);
            datos.append(numeroPadre).append(":f").append(i).append(" -> ");
            datos.append(numeroHijo).append(";\n");
        }
    }
}
