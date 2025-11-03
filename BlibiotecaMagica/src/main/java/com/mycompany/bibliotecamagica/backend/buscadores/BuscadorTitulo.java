/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.buscadores;

import com.mycompany.bibliotecamagica.backend.estructuras.lista_doble.ListaDoble;
import com.mycompany.bibliotecamagica.backend.estructuras.lista_simple.ListaSimple;
import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.backend.modelos.LibroBiblioteca;
import java.time.Duration;
import java.time.Instant;

/**
 *
 * @author rafael-cayax
 */
public class BuscadorTitulo extends Buscador{

    public BuscadorTitulo() {
        super("Buscar por titulo");
    }
    
    @Override
    protected void validarEntrada(String entrada) throws EntradaException {
        if(entrada.isBlank()) throw new EntradaException("ingresar un campo valido");
    }

    @Override
    protected ListaSimple<LibroBiblioteca> obtenerBusqueda(String entrada) throws EntradaException {
        long inicio = System.nanoTime();
        biblioteca.buscarPorTituloLista(entrada);
        long termino = System.nanoTime();
        long lista = termino - inicio;
        inicio = System.nanoTime();
        ListaDoble<LibroBiblioteca> libros = biblioteca.buscarPorTitulo(entrada);
        termino = System.nanoTime();
        long arbol = termino - inicio;
        mensaje = "Tiempo que tardo en lista: " + lista + " us.\n";
        mensaje += "Tiempo que tardo en arbol: " + arbol + " us.";
        return libros;
    }

    @Override
    public boolean obtenerMensaje() {
        return true;
    }
    
}
