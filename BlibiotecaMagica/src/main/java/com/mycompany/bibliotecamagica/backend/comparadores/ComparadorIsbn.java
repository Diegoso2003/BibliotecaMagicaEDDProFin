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
public class ComparadorIsbn extends Comparador{

    public ComparadorIsbn() {
        super("Ordenar por isbn");
    }

    @Override
    public int compare(LibroBiblioteca o1, LibroBiblioteca o2) {
        return o1.getLibro().compareTo(o2.getLibro());
    }
    
}
