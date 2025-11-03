/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.modelos;

import static com.mycompany.bibliotecamagica.backend.enums.EstadoLibroEnum.AGOTADO;
import static com.mycompany.bibliotecamagica.backend.enums.EstadoLibroEnum.DISPONIBLE;
import static com.mycompany.bibliotecamagica.backend.enums.EstadoLibroEnum.EN_TRANSITO;

/**
 *
 * @author rafael-cayax
 */
public final class LibroBiblioteca implements Comparable<LibroBiblioteca>{

    private Libro libro;
    private int prestado = 0;
    private int agotado = 0;
    private int disponible = 0;
    private int enTraspaso = 0;
    
    public LibroBiblioteca(InfoLibro nuevo){
        libro = nuevo.getLibro();
        aumentarIndices(nuevo);
    }
    
    /**
     * constructor usado para busquedas
     * @param libro 
     */
    public LibroBiblioteca(Libro libro){
        this.libro = libro;
    }
    
    @Override
    public int compareTo(LibroBiblioteca o) {
        return libro.compareTo(o.libro);
    }

    public void aumentarIndices(InfoLibro nuevo) {
        switch(nuevo.getEstado()){
            case AGOTADO -> agotado++;
            case DISPONIBLE -> disponible++;
            case EN_TRANSITO -> enTraspaso++;
            default -> prestado++;
        }
    }

    public Libro getLibro() {
        return libro;
    }

    public int getPrestado() {
        return prestado;
    }

    public int getAgotado() {
        return agotado;
    }

    public int getDisponible() {
        return disponible;
    }

    public int getEnTraspaso() {
        return enTraspaso;
    }

    public int getTotal() {
        return prestado + disponible + enTraspaso + agotado;
    }
    
}