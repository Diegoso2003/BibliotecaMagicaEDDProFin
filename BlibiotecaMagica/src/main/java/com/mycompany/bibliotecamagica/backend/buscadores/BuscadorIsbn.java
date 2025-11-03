/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.buscadores;

import com.mycompany.bibliotecamagica.backend.estructuras.lista_simple.ListaSimple;
import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.backend.iterador.IteradorLista;
import com.mycompany.bibliotecamagica.backend.modelos.LibroBiblioteca;
import com.mycompany.bibliotecamagica.backend.validadores.ValidadorLibro;

/**
 *
 * @author rafael-cayax
 */
public class BuscadorIsbn extends Buscador {

    public BuscadorIsbn() {
        super("Buscar por isbn");
    }

    @Override
    protected void validarEntrada(String entrada) throws EntradaException {
        ValidadorLibro validador = new ValidadorLibro();
        if (!validador.esIsbnValido(entrada)) {
            throw new EntradaException("Isbn ingresado invalido");
        }
    }

    @Override
    protected ListaSimple<LibroBiblioteca> obtenerBusqueda(String entrada) throws EntradaException {
        long inicio = System.nanoTime();
        LibroBiblioteca libro = biblioteca.buscarPorIsbn(entrada);
        ListaSimple<LibroBiblioteca> lista = new ListaSimple<>();
        if (libro != null) {
            lista.agregar(libro);
        }
        long termino = System.nanoTime();
        long arbol = termino - inicio;
        inicio = System.nanoTime();
        IteradorLista<LibroBiblioteca> libros = biblioteca.getLibrosLista().getIterador();
        while (libros.haySiguiente()) {
            if (libros.getActual().getLibro().getIsbn().equals(entrada)) {
                break;
            }
        }
        termino = System.nanoTime();
        long lista2 = termino - inicio;
        mensaje = "Tiempo que tardo en lista: " + lista2 + " us.\n";
        mensaje += "Tiempo que tardo en arbol: " + arbol + " us.";
        return lista;
    }

    @Override
    public boolean obtenerMensaje() {
        return true;
    }

}
