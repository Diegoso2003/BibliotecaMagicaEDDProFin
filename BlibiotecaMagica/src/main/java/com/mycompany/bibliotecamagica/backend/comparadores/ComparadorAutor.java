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
public class ComparadorAutor extends Comparador{

    public ComparadorAutor() {
        super("Ordenar por autor");
    }

    @Override
    public int compare(LibroBiblioteca o1, LibroBiblioteca o2) {
        return compararEspañol(o1.getLibro().getAutor(), o2.getLibro().getAutor());
    }
    
}
