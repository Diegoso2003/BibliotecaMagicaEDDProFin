/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.tabla_hash;

/**
 *
 * @author rafael-cayax
 */
public class GeneradorDotTablaHash {

    private StringBuilder infoTabla;
    private StringBuilder infoConexiones;
    private StringBuilder conexionTabla;
    private static final String TABLA = "arreglo";
    private static final String FLECHA = " -> ";

    public GeneradorDotTablaHash() {
        infoTabla = new StringBuilder();
        infoConexiones = new StringBuilder();
        conexionTabla = new StringBuilder();
    }

    public String getDotTablaHash(NodoHash[] tabla, double factorCarga) {
        agregarDatos(tabla);
        String formateado = String.format("%.2f", factorCarga);
        StringBuilder dot = new StringBuilder();
        dot.append("digraph Lists {\n")
                .append("graph[rankdir=LR,nodesep=0.15, ranksep=1.5];\n")
                .append(TABLA)
                .append(" [rankdir=TB, shape=\"plaintext\", label=<\n")
                .append("<TABLE BORDER=\"1\" CELLBORDER=\"1\" CELLSPACING=\"0\" CELLPADDING=\"16.5\">\n")
                .append("<TR>\n<TD PORT=\"val\" BGCOLOR=\"lavender\"><B>Arreglo</B></TD>\n</TR>")
                .append(infoTabla)
                .append("</TABLE>\n")
                .append(">];\n")
                .append("subgraph cluster_nodos {\n")
                .append("style=invis;\n")
                .append("graph[rankdir=LR, label=<<TABLE BORDER=\"0\" CELLBORDER=\"0\" ")
                .append("CELLPADDING=\"10\"><TR><TD ALIGN=\"LEFT\"><B> Factor de carga: ")
                .append(formateado)
                .append("</B></TD></TR></TABLE>>, ")
                .append("labelloc=\"t\", labeljust=\"c\", fontsize=14];\n")
                .append("nodo_invisible [style=invis, width=0, height=0, label=\"\"];")
                .append("node [shape=box, fillcolor=lightgreen, style=filled];\n")
                .append(infoConexiones)
                .append("\n}")
                .append(conexionTabla)
                .append("\narreglo:val -> nodo_invisible [style=invis];")
                .append("}");
        return dot.toString();
    }

    private void agregarDatos(NodoHash[] tabla) {
        for (int i = 0; i < tabla.length; i++) {
            infoTabla.append("\n<TR>\n<TD PORT=\"").append(i).append("\"").append(" BGCOLOR=\"lavender\">")
                    .append("posicion: ").append(i).append("</TD>\n</TR>");
            String primero = null;
            StringBuilder conexionesGrafo = new StringBuilder();
            NodoHash nodo = tabla[i];
            while (nodo != null) {
                if (primero == null) {
                    primero = nodo.getLibro().getLibro().getSinGuiones();
                }
                infoConexiones.append(nodo.getLibro().getLibro().getSinGuiones())
                        .append(" [label=\"").append("ISBN: ").append(nodo.getLibro().getLibro().getIsbn()).append("\\n")
                        .append("Cantidad: ").append(nodo.getLibro().getTotal())
                        .append("\"];\n");
                if (conexionesGrafo.isEmpty()) {
                    conexionesGrafo.append(nodo.getLibro().getLibro().getSinGuiones());
                } else {
                    conexionesGrafo.append(FLECHA).append(nodo.getLibro().getLibro().getSinGuiones());
                }
                nodo = nodo.getSiguiente();
            }
            if (!conexionesGrafo.isEmpty() && tabla[i].getSiguiente() != null) {
                conexionesGrafo.append(";\n");
                infoConexiones.append(conexionesGrafo);
            }
            if (primero != null) {
                conexionTabla.append("\n").append(TABLA).append(":").append(i)
                        .append(FLECHA).append(primero).append(";");
            } else {
                infoConexiones.append(" inv").append(i).append(" [style=invis label=\"  \\n  \"];\n");
                conexionTabla.append("\n").append(TABLA).append(":").append(i)
                        .append(FLECHA).append("inv").append(i).append(" [style=invis];");
            }
        }
    }
}
