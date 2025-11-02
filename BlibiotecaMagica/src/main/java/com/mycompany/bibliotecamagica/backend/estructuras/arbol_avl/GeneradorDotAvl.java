/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.arbol_avl;

import com.mycompany.bibliotecamagica.backend.modelos.ListaLibros;

/**
 *
 * @author rafael-cayax
 */
public class GeneradorDotAvl {
    private static final String NOMBRE_NODO = "Libro";
    
    public String obtenerDot(NodoAvl<ListaLibros> raiz){
        StringBuilder datos = new StringBuilder();
        datos.append("digraph G {\n")
                .append("        rankdir=TB;\n")
                .append("        graph [ranksep=2, nodesep=1];\n")
                .append("        node [shape=record, fillcolor=lightgreen, style=filled];\n");
        if(raiz != null){
            agregarDatosRecursivos(raiz, datos);
        } else {
            datos.append("nodo1 [label=\"biblioteca sin libros\"];");
        }
        datos.append("}");
        return datos.toString();
    }

    private void agregarDatosRecursivos(NodoAvl<ListaLibros> nodo, StringBuilder datos) {
        String titulo = nodo.getLista().obtenerPrimero().obtenerPrimero().getLibro().getSinGuiones();
        datos.append(NOMBRE_NODO).append(titulo);
        datos.append(" [label=\" ");
        if(!nodo.esNodoHoja()) datos.append("<f0> |");
        datos.append("Titulo: ").append(nodo.obtenerPrimero().obtenerPrimero().getLibro().getTitulo()).append("\\n");
        datos.append("Cantidad: ").append(nodo.getLista().getNumElementos());
        if(!nodo.esNodoHoja()) datos.append("| <f1>");
        datos.append("\"];\n");
        if (nodo.getIzquierda() != null) {
            agregarDatosRecursivos(nodo.getIzquierda(), datos);
            datos.append(NOMBRE_NODO).append(titulo).append(":f0 -> "); 
            datos.append(NOMBRE_NODO).append(nodo.getIzquierda().obtenerPrimero().obtenerPrimero().getLibro().getSinGuiones()).append(";\n");
        }
        if (nodo.getDerecha() != null) {
            agregarDatosRecursivos(nodo.getDerecha(), datos);
            datos.append(NOMBRE_NODO).append(titulo).append(":f1 -> "); 
            datos.append(NOMBRE_NODO).append(nodo.getDerecha().obtenerPrimero().obtenerPrimero().getLibro().getSinGuiones()).append(";\n");
        }
    }
}
