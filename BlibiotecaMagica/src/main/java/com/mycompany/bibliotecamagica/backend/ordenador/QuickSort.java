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
public class QuickSort extends Ordenador{

    public QuickSort() {
        super("Algoritmo quicksort");
    }

    @Override
    public LibroBiblioteca[] realizarAlgoritmo(LibroBiblioteca[] arreglo, Comparador comparador) {
        ordenacionQuickSort(arreglo, comparador, 0, arreglo.length);
        return arreglo;
    }

    private void ordenacionQuickSort(LibroBiblioteca[] arreglo, Comparador comparador, int primero, int ultimo) {
        int central = (primero + ultimo) / 2;
        LibroBiblioteca pivote = arreglo[central];
        int i = primero;
        int j = ultimo;
        do{
            while(comparador.compare(arreglo[i], pivote) < 0) i++;
            while(comparador.compare(arreglo[j], pivote) > 0) j--;
            if(i <= j){
                LibroBiblioteca aux = arreglo[j];
                arreglo[j] = arreglo[i];
                arreglo[i] = aux;
                i++;
                j--;
            }
        }while( i <= j);
        if(primero < j) ordenacionQuickSort(arreglo, comparador, primero, j);
        if(i < ultimo) ordenacionQuickSort(arreglo, comparador, i, ultimo);
    }
    
}
