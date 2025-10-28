/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.estructuras.grafo;

import java.math.BigDecimal;

/**
 *
 * @author rafael-cayax
 */
public class Conexion implements Comparable<Conexion>{
    private NodoGrafo biblioAdyascente;
    private long tiempo;
    private BigDecimal precio;

    public NodoGrafo getBiblioAdyascente() {
        return biblioAdyascente;
    }

    public void setBiblioAdyascente(NodoGrafo biblioAdyascente) {
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