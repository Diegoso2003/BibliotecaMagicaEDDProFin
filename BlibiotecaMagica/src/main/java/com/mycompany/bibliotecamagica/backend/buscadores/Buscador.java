/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.buscadores;

import com.mycompany.bibliotecamagica.backend.comparadores.ComparadorClaves;
import com.mycompany.bibliotecamagica.backend.estructuras.lista_simple.ListaSimple;
import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.backend.modelos.Biblioteca;
import com.mycompany.bibliotecamagica.backend.modelos.LibroBiblioteca;

/**
 *
 * @author rafael-cayax
 */
public abstract class Buscador {
    
    protected Biblioteca biblioteca;
    protected ComparadorClaves comparador;
    protected String mensaje;    
    private final String nombre;

    public Buscador(String nombre) {
        this.nombre = nombre;
        comparador = new ComparadorClaves();
    }
    
    public ListaSimple<LibroBiblioteca> realizarBusqueda(String entrada, Biblioteca biblioteca) throws EntradaException{
        validarEntrada(entrada);
        this.biblioteca = biblioteca;
        return obtenerBusqueda(entrada);
    }
    
    protected abstract void validarEntrada(String entrada) throws EntradaException;
    protected abstract ListaSimple<LibroBiblioteca> obtenerBusqueda(String entrada) throws EntradaException;
    public abstract boolean obtenerMensaje();

    public String getMensaje() {
        return mensaje;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
