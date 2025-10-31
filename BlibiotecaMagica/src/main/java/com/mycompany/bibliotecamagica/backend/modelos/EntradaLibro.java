/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.modelos;

import com.mycompany.bibliotecamagica.backend.calculo_ruta.InfoTraslado;
import com.mycompany.bibliotecamagica.backend.estructuras.cola.Cola;

/**
 *
 * @author rafael-cayax
 */
public class EntradaLibro {
    private final InfoLibro libro;
    private final boolean nuevoIngreso;
    private final Cola<Biblioteca> ruta;
    private final InfoTraslado info;

    public EntradaLibro(InfoLibro libro, boolean nuevoIngreso, Cola<Biblioteca> ruta, InfoTraslado info) {
        this.libro = libro;
        this.nuevoIngreso = nuevoIngreso;
        this.ruta = ruta;
        this.info = info;
    }

    public InfoLibro getLibro() {
        return libro;
    }

    public boolean isNuevoIngreso() {
        return nuevoIngreso;
    }

    public Cola<Biblioteca> getRuta() {
        return ruta;
    }

    public InfoTraslado getInfo() {
        return info;
    }
    
}