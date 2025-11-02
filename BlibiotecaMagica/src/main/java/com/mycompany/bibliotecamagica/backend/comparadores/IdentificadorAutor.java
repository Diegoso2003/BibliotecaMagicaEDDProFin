/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.comparadores;

import com.mycompany.bibliotecamagica.backend.modelos.LibroBiblioteca;

/**
 *
 * @author rafael-cayax
 */
public class IdentificadorAutor implements Identificable<LibroBiblioteca>{

    @Override
    public String getClave(LibroBiblioteca elemento) {
        return elemento.getLibro().getAutor();
    }
    
}
