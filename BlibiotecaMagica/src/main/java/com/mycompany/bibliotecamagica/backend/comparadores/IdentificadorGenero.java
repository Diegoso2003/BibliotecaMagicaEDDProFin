/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.comparadores;

import com.mycompany.bibliotecamagica.backend.modelos.ListaLibros;

/**
 *
 * @author rafael-cayax
 */
public class IdentificadorGenero implements Identificable<ListaLibros> {

    @Override
    public String getClave(ListaLibros elemento) {
        return elemento.obtenerPrimero().getLibro().getGenero();
    }
    
}
