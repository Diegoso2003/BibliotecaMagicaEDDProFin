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
public class SeleccionDirecta extends Ordenador{

    public SeleccionDirecta() {
        super("Metodo de seleccion directa");
    }

    @Override
    public LibroBiblioteca[] realizarAlgoritmo(LibroBiblioteca[] arreglo, Comparador comparador) {
        int indiceMenor;
        for(int i = 0; i < arreglo.length - 1; i++){
            indiceMenor = i;
            for(int j = i + 1; j < arreglo.length; j++){
                if(comparador.compare(arreglo[j], arreglo[indiceMenor]) < 0){
                    indiceMenor = j;
                }
            }
            if(indiceMenor != i){
                LibroBiblioteca aux = arreglo[indiceMenor];
                arreglo[indiceMenor] = arreglo[i];
                arreglo[i] = aux;
            }
        }
        return arreglo;
    }
    
}
