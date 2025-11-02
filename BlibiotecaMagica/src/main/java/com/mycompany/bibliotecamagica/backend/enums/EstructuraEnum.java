/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.enums;

/**
 *
 * @author rafael-cayax
 */
public enum EstructuraEnum {
    ARBOL_TITULO("arbol avl por titulo"),
    ARBOL_GENERO("arbol b+ por genero"),
    ARBOL_AÑO("arbol b por año"),
    ARBOL_AUTOR("arbol b+ por autor"),
    TABLA_HASH("tabla hash con clave isbn");
    
    private final String descripcion;
    
    private EstructuraEnum(String descripcion){
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}