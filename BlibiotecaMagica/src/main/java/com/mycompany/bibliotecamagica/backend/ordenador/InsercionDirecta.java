/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.ordenador;

import com.mycompany.bibliotecamagica.backend.comparadores.Comparador;
import com.mycompany.bibliotecamagica.backend.modelos.LibroBiblioteca;

/**
 *
 * @author rafael-cayax
 */
public class InsercionDirecta extends Ordenador {

    public InsercionDirecta() {
        super("Metodo de insercion directa");
    }

    @Override
    public LibroBiblioteca[] realizarAlgoritmo(LibroBiblioteca[] arreglo, Comparador comparador) {
        for (int i = 1; i < arreglo.length; i++) {
            LibroBiblioteca aux = arreglo[i];
            int j = i - 1;
            while (j >= 0 && comparador.compare(arreglo[j], aux) > 0) {
                arreglo[j + 1] = arreglo[j];
                j--;
            }
            arreglo[j + 1] = aux;
        }
        return arreglo;
    }

}
