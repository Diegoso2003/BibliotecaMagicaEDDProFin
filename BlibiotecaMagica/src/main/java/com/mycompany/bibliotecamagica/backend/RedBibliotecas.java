/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend;

import com.mycompany.bibliotecamagica.backend.estructuras.grafo.Grafo;

/**
 *
 * @author rafael-cayax
 */
public enum RedBibliotecas {
    INSTANCIA;
    
    private final Grafo red;

    private RedBibliotecas() {
        red = new Grafo();
    }

    public Grafo getRed() {
        return red;
    }
    
}