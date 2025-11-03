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
public class Intercambio extends Ordenador{

    public Intercambio() {
        super("Metodo de intercambio");
    }

    @Override
    public LibroBiblioteca[] realizarAlgoritmo(LibroBiblioteca[] arreglo, Comparador comparador) {
        for(int i = 0; i < arreglo.length - 1; i++){
            for(int j = i +1; j < arreglo.length; j++){
                if(comparador.compare(arreglo[i], arreglo[j]) > 0){
                    LibroBiblioteca aux = arreglo[i];
                    arreglo[i] = arreglo[j];
                    arreglo[j] = aux;
                }
            }
        }
        return arreglo;
    }
    
}
