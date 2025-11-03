/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.comparadores;

import com.mycompany.bibliotecamagica.backend.modelos.LibroBiblioteca;

/**
 *
 * @author rafael-cayax
 */
public class ComparadorGenero extends Comparador{

    public ComparadorGenero() {
        super("Ordenar por genero");
    }

    @Override
    public int compare(LibroBiblioteca o1, LibroBiblioteca o2) {
        return compararEspañol(o1.getLibro().getGenero(), o2.getLibro().getGenero());
    }
    
}
