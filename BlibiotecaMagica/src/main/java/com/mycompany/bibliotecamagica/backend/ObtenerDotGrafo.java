/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend;

import com.mycompany.bibliotecamagica.backend.estructuras.grafo.NodoGrafo;
import com.mycompany.bibliotecamagica.backend.estructuras.lista_doble.ListaDoble;
import com.mycompany.bibliotecamagica.backend.iterador.IteradorLista;
import com.mycompany.bibliotecamagica.backend.modelos.Biblioteca;
import com.mycompany.bibliotecamagica.backend.estructuras.grafo.Conexion;

/**
 *
 * @author rafael-cayax
 */
public class ObtenerDotGrafo {
        
    private final ListaDoble<NodoGrafo> bibliotecas;
    private final StringBuilder infoTabla;
    private final StringBuilder infoConexiones;
    private final StringBuilder conexionTabla;
    private static final String TABLA = "arreglo";
    private static final String FLECHA = " -> ";
    private static final String ESPACIO = "&#160;&#160;";

    public ObtenerDotGrafo(ListaDoble<NodoGrafo> bibliotecas) {
        this.bibliotecas = bibliotecas;
        infoTabla = new StringBuilder();
        infoConexiones = new StringBuilder();
        conexionTabla = new StringBuilder();
    }
    
    public String obtenerDotGrafo(){
        obtenerDatosGrafo();
        StringBuilder dot = new StringBuilder();
        dot.append("digraph Lists {\n")
                .append("graph[rankdir=LR,nodesep=0.3, ranksep=1.5];\n")
                .append(TABLA)
                .append(" [rankdir=TB, shape=\"plaintext\", label=<\n")
                .append("<TABLE BORDER=\"1\" CELLBORDER=\"1\" CELLSPACING=\"0\" CELLPADDING=\"14\">\n")
                .append("<TR>\n<TD PORT=\"val\" BGCOLOR=\"lavender\"><B>Biblioteca</B></TD>\n</TR>")
                .append(infoTabla)
                .append("</TABLE>\n")
                .append(">];\n")
                .append("subgraph cluster_nodos {\n")
                .append("style=invis;\n")
                .append("graph[rankdir=LR, label=<<TABLE BORDER=\"0\" CELLBORDER=\"0\" ")
                .append("CELLPADDING=\"10\"><TR><TD ALIGN=\"LEFT\"><B>Bibliotecas con")
                .append(" las que tiene conexion</B></TD></TR></TABLE>>, ")
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

    private void obtenerDatosGrafo() {
        IteradorLista<NodoGrafo> iterador = bibliotecas.getIterador();
        while(iterador.haySiguiente()){
            NodoGrafo actual = iterador.getActual();
            Biblioteca bActual = actual.getBiblioteca();
            String id = bActual.getIDNumerico();
            infoTabla.append("\n<TR>\n<TD PORT=\"").append(id).append("\"").append(" BGCOLOR=\"lavender\">")
                    .append("ID: ").append(bActual.getId()).append("<br/>")
                    .append("Nombre: ").append(bActual.getNombre()).append(ESPACIO).append("<br/>")
                    .append("Ubicacion: ").append(bActual.getUbicacion()).append(ESPACIO).append("</TD>\n</TR>");
            IteradorLista<Conexion> conexiones = actual.getConexiones().getIterador();
            String primero = null;
            StringBuilder conexionesGrafo = new StringBuilder();
            while(conexiones.haySiguiente()){
                Conexion c = conexiones.getActual();
                Biblioteca bConexion = c.getBiblioAdyascente().getBiblioteca();
                if(primero == null) primero = bConexion.getIDNumerico();
                infoConexiones.append(id).append(bConexion.getIDNumerico())
                        .append(" [label=\"").append("ID: ").append(bConexion.getId()).append("\\n")
                        .append("Tiempo: ").append(c.getTiempo()).append("ms").append("\\n")
                        .append("Costo: Q").append(c.getPrecio()).append("\\n")
                        .append("\"];\n");
                if(conexionesGrafo.isEmpty()) conexionesGrafo.append(id).append(bConexion.getIDNumerico());
                else conexionesGrafo.append(FLECHA).append(id).append(bConexion.getIDNumerico());
            }
            if(!conexionesGrafo.isEmpty() && actual.getConexiones().getNumElementos() > 1){
                conexionesGrafo.append(";\n");
                infoConexiones.append(conexionesGrafo);
            }
            if(primero != null){
                conexionTabla.append("\n").append(TABLA).append(":").append(id)
                    .append(FLECHA).append(id).append(primero).append(";");
            }  else {
                infoConexiones.append(" inv").append(id).append(" [style=invis label=\"  \\n  \\n  \\n  \"];\n");
                conexionTabla.append("\n").append(TABLA).append(":").append(id)
                        .append(FLECHA).append("inv").append(id).append(" [style=invis];");
            }
        }
    }
}
