/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.calculo_ruta;

import com.mycompany.bibliotecamagica.backend.enums.PrioridadEnum;
import com.mycompany.bibliotecamagica.backend.modelos.Libro;
import java.math.BigDecimal;

/**
 *
 * @author rafael-cayax
 */
public class InfoTraslado implements Comparable<InfoTraslado>{
    private final Libro libro;
    private final BigDecimal costo;
    private final Long tiempo;
    private final String ruta;
    private final PrioridadEnum prioridad;

    public InfoTraslado(Libro libro, BigDecimal costo, long tiempo, String ruta, PrioridadEnum prioridad) {
        this.libro = libro;
        this.costo = costo;
        this.tiempo = tiempo;
        this.ruta = ruta;
        this.prioridad = prioridad;
    }

    @Override
    public int compareTo(InfoTraslado o) {
        return libro.toString().compareTo(o.libro.toString());
    }

    public Libro getLibro() {
        return libro;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public Long getTiempo() {
        return tiempo;
    }

    public String getRuta() {
        return ruta;
    }

    public PrioridadEnum getPrioridad() {
        return prioridad;
    }

}