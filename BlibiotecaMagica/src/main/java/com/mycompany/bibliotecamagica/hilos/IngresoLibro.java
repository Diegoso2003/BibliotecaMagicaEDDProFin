/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.hilos;

import com.mycompany.bibliotecamagica.backend.RedBibliotecas;
import com.mycompany.bibliotecamagica.backend.modelos.Biblioteca;
import com.mycompany.bibliotecamagica.backend.modelos.EntradaLibro;

/**
 *
 * @author rafael-cayax
 */
public class IngresoLibro extends Thread{
    private final Biblioteca biblioteca;
    private final EntradaLibro libro;

    public IngresoLibro(Biblioteca biblioteca, EntradaLibro libro) {
        this.biblioteca = biblioteca;
        this.libro = libro;
    }
    
    @Override
    public void run(){
        if(!libro.isNuevoIngreso() && libro.getLibro().tieneEnTransito()){
            libro.getLibro().disminuirEnTransito();
            libro.getLibro().
        }
        biblioteca.agregarLibro(libro.getLibro());
        agregarInfoTraslado();
    }
    
    private synchronized void agregarInfoTraslado(){
        RedBibliotecas.INSTANCIA.getTraslados().agregar(libro.getInfo());
    }
}
