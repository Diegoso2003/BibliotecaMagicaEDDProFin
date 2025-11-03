/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.buscadores;

import com.mycompany.bibliotecamagica.backend.estructuras.lista_simple.ListaSimple;
import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.backend.modelos.LibroBiblioteca;

/**
 *
 * @author rafael-cayax
 */
public class ListarAlfabeticamente extends Buscador{

    public ListarAlfabeticamente() {
        super("Listar alfabeticamente");
    }

    @Override
    protected void validarEntrada(String entrada) throws EntradaException {
    }

    @Override
    protected ListaSimple<LibroBiblioteca> obtenerBusqueda(String entrada) throws EntradaException {
        return biblioteca.getLibrosPorTitulo().obtenerInorden();
    }

    @Override
    public boolean obtenerMensaje() {
        return false;
    }
    
}
