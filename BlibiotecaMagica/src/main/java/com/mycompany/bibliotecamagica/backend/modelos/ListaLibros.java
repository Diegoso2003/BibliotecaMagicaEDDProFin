/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.modelos;

import com.mycompany.bibliotecamagica.backend.estructuras.lista_simple.ListaSimple;

/**
 *
 * @author rafael-cayax
 */
public class ListaLibros extends ListaSimple<InfoLibro> implements Comparable<ListaLibros>{

    @Override
    public int compareTo(ListaLibros o) {
        return obtenerPrimero().compareTo(o.obtenerPrimero());
    }
    
}
