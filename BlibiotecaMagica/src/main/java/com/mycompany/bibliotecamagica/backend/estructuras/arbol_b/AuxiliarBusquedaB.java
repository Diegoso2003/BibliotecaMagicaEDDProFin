/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.arbol_b;

import com.mycompany.bibliotecamagica.backend.estructuras.lista_simple.ListaSimple;
import com.mycompany.bibliotecamagica.backend.modelos.LibroBiblioteca;

/**
 *
 * @author rafael-cayax
 */
public class AuxiliarBusquedaB {

    private boolean buscar = true;
    private ListaSimple<LibroBiblioteca> lista;
    private final int fechaFin;
    private final int fechaInicio;

    public AuxiliarBusquedaB(int fechaFin, int fechaInicio) {
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
    }
    
    public boolean seguirBuscando() {
        return buscar;
    }

    public int fechaInicio() {
        return fechaInicio;
    }

    public int fechaFin() {
        return fechaFin;
    }

    public ListaSimple<LibroBiblioteca> getLista() {
        return lista;
    }

    public void pararBusqueda() {
        buscar = false;
    }

    public boolean esFechaUnica() {
        return fechaInicio == fechaFin;
    }

    public void setLista(ListaSimple<LibroBiblioteca> lista) {
        this.lista = lista;
    }
    
}
