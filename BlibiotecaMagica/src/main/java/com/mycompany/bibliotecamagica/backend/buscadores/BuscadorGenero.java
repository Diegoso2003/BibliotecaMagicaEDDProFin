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
public class BuscadorGenero extends Buscador{

    public BuscadorGenero() {
        super("Buscar por genero");
    }

    @Override
    protected void validarEntrada(String entrada) throws EntradaException {
        if(entrada.isBlank()){
            throw new EntradaException("Ingrese un campo valido");
        }
    }

    @Override
    protected ListaSimple<LibroBiblioteca> obtenerBusqueda(String entrada) throws EntradaException {
        return biblioteca.buscarPorGenero(entrada);
    }

    @Override
    public boolean obtenerMensaje() {
        return false;
    }
    
}
