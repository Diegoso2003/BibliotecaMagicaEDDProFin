/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.comparadores;

import com.mycompany.bibliotecamagica.backend.modelos.Libro;
import java.util.Comparator;

/**
 *
 * @author rafael-cayax
 */
public class ComparadorAño extends Comparador implements Comparator<Libro>{

    @Override
    public int compare(Libro o1, Libro o2) {
        return o1.getAño().compareTo(o2.getAño());
    }
    
}
