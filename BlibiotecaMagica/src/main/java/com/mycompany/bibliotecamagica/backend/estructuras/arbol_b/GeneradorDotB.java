/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.arbol_b;

import com.mycompany.bibliotecamagica.backend.estructuras.lista_doble.ListaDoble;
import com.mycompany.bibliotecamagica.backend.modelos.LibroBiblioteca;

/**
 *
 * @author rafael-cayax
 */
class GeneradorDotB {

    public String generar(NodoArbolB<LibroBiblioteca> raiz) {
        StringBuilder datos = new StringBuilder();
        datos.append("digraph ArbolB {\n")
                .append("rankdir=TB;\n")
                .append("graph [ranksep=2, nodesep=1];\n")
                .append("node [shape=record, fillcolor=lightgreen, style=filled];\n");
        if (raiz.getNumeroClaves() == 0) {
            datos.append("nodo1 [label=\"biblioteca sin libros\"];");
        } else {
            int numNodo = 1;
            agregarDatosRecursivos(datos, raiz, numNodo);
        }
        datos.append("}");
        return datos.toString();
    }

    private void agregarDatosRecursivos(StringBuilder datos, NodoArbolB<LibroBiblioteca> nodo, int numNodo) {
        datos.append(numNodo).append(" [label=\"");
        boolean esHoja = nodo.esNodoHoja();
        if(!esHoja) datos.append("<f0> | ");
        ListaDoble<LibroBiblioteca> []claves = nodo.getClaves();
        int numeroClaves = nodo.getNumeroClaves();
        for(int i = 0; i < numeroClaves; i++){
            datos.append(claves[i].obtenerPrimero().getLibro().getAño()).append("\\n")
                    .append("C: ").append(claves[i].getNumElementos());
            if(!esHoja) datos.append(" | <f").append((i+1)).append(">");
            if(i == numeroClaves -1){
                datos.append("\"];\n");
            } else {
                datos.append(" | ");
            }
        }
        if(esHoja) return;
        int numeroPadre = numNodo;
        NodoArbolB<LibroBiblioteca> hijos[] = nodo.getHijos();
        for(int i = 0; i <= numeroClaves; i++){
            int numeroHijo = ++numNodo;
            agregarDatosRecursivos(datos, hijos[i], numNodo);
            datos.append(numeroPadre).append(":f").append(i).append(" -> ");
            datos.append(numeroHijo).append(";\n");
        }
    }

}
