/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.grafo;

import com.mycompany.bibliotecamagica.backend.estructuras.lista_doble.ListaDoble;
import com.mycompany.bibliotecamagica.backend.modelos.Biblioteca;
import com.mycompany.bibliotecamagica.backend.modelos.Conexion;

/**
 *
 * @author rafael-cayax
 */
public class NodoGrafo  implements Comparable<NodoGrafo>{
    private final Biblioteca biblioteca;
    private final ListaDoble<Conexion> conexiones;

    public NodoGrafo(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        conexiones = new ListaDoble<>();
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public boolean agregarConexion(Conexion conexion){
        return conexiones.agregar(conexion);
    }

    public ListaDoble<Conexion> getConexiones() {
        return conexiones;
    }
    
    @Override
    public int compareTo(NodoGrafo o) {
        return this.biblioteca.compareTo(o.biblioteca);
    }

    @Override
    public String toString() {
        return biblioteca.toString();
    }

}