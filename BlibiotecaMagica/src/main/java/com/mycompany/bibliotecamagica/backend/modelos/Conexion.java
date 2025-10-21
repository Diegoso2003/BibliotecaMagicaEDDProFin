/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.modelos;

import java.math.BigDecimal;

/**
 *
 * @author rafael-cayax
 */
public class Conexion implements Comparable<Conexion>{
    private Biblioteca biblioAdyascente;
    private long tiempo;
    private BigDecimal precio;

    public Biblioteca getBiblioAdyascente() {
        return biblioAdyascente;
    }

    public void setBiblioAdyascente(Biblioteca biblioAdyascente) {
        this.biblioAdyascente = biblioAdyascente;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @Override
    public int compareTo(Conexion o) {
        return this.biblioAdyascente.compareTo(o.biblioAdyascente);
    }
    
}