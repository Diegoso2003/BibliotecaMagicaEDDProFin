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
public class OrdenacionShell extends Ordenador{

    public OrdenacionShell() {
        super("Algoritmo shell");
    }

    @Override
    public LibroBiblioteca[] realizarAlgoritmo(LibroBiblioteca[] arreglo, Comparador comparador) {
        int salto = arreglo.length / 2;
        while(salto > 0){
            for(int i = salto; i < arreglo.length; i++){
                int j = i - salto;
                while(j >= 0){
                    int k = j + salto;
                    if(comparador.compare(arreglo[j], arreglo[k]) <= 0){
                        j = -1;
                    } else {
                        LibroBiblioteca aux = arreglo[j+1];
                        arreglo[j+1] = arreglo[j];
                        arreglo[j] = aux;
                        j -= salto;
                    }
                }
            }
            salto /= 2;
        }
        return arreglo;
    }
    
}
