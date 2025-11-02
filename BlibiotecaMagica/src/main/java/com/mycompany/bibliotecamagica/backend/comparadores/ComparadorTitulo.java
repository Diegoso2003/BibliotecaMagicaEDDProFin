/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.comparadores;

import com.mycompany.bibliotecamagica.backend.modelos.LibroBiblioteca;
import java.util.Comparator;

/**
 *
 * @author rafael-cayax
 */
public class ComparadorTitulo extends Comparador implements Comparator<LibroBiblioteca>{

    @Override
    public int compare(LibroBiblioteca o1, LibroBiblioteca o2) {
        return compararEspañol(o1.getLibro().getTitulo(), 
                o2.getLibro().getTitulo());
    }
    
}
