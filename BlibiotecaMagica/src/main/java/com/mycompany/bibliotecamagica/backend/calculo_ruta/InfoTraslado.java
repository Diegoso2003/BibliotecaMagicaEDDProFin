/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.calculo_ruta;

import com.mycompany.bibliotecamagica.backend.estructuras.lista_simple.ListaSimple;
import com.mycompany.bibliotecamagica.backend.modelos.Biblioteca;
import com.mycompany.bibliotecamagica.backend.modelos.Libro;
import java.math.BigDecimal;

/**
 *
 * @author rafael-cayax
 */
public class InfoTraslado implements Comparable<InfoTraslado>{
    private final Libro libro;
    private final BigDecimal costo;
    private final long tiempo;
    private final ListaSimple<Biblioteca> ruta;

    public InfoTraslado(Libro libro, BigDecimal costo, long tiempo, ListaSimple<Biblioteca> ruta) {
        this.libro = libro;
        this.costo = costo;
        this.tiempo = tiempo;
        this.ruta = ruta;
    }

    public Libro getLibro() {
        return libro;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public long getTiempo() {
        return tiempo;
    }

    public ListaSimple<Biblioteca> getRuta() {
        return ruta;
    }

    @Override
    public int compareTo(InfoTraslado o) {
        return libro.compareTo(o.libro);
    }
    
}