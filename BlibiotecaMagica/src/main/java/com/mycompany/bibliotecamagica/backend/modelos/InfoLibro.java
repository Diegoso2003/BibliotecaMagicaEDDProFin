/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.modelos;

import com.mycompany.bibliotecamagica.backend.enums.EstadoLibroEnum;

/**
 *
 * @author rafael-cayax
 */
public class InfoLibro {
    private final Libro libro;
    private EstadoLibroEnum estado;

    public InfoLibro(Libro libro, EstadoLibroEnum estado) {
        this.libro = libro;
        this.estado = estado;
    }

    public Libro getLibro() {
        return libro;
    }

    public EstadoLibroEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoLibroEnum estado) {
        this.estado = estado;
    }
    
}