/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.hilos;

import com.mycompany.bibliotecamagica.backend.enums.EstadoLibroEnum;
import com.mycompany.bibliotecamagica.backend.modelos.Biblioteca;
import com.mycompany.bibliotecamagica.backend.modelos.InfoLibro;

/**
 *
 * @author rafael-cayax
 */
public class IngresoLibro extends Thread{
    private final Biblioteca biblioteca;
    private final InfoLibro libro;
    private final boolean nuevo;

    public IngresoLibro(Biblioteca biblioteca, InfoLibro libro, boolean nuevo) {
        this.biblioteca = biblioteca;
        this.libro = libro;
        this.nuevo = nuevo;
    }
    
    @Override
    public void run(){
        if(!nuevo || libro.getEstado() == EstadoLibroEnum.EN_TRANSITO){
            libro.setEstado(EstadoLibroEnum.DISPONIBLE);
        }
        biblioteca.agregarLibro(libro);
    }
    
}
