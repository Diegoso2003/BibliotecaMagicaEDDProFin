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
public abstract class Comparador  implements Comparator<LibroBiblioteca>{

    private final String nombre;

    public Comparador(String nombre) {
        this.nombre = nombre;
    }
    
    protected int compararEspañol(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        return str1.compareTo(str2);
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
