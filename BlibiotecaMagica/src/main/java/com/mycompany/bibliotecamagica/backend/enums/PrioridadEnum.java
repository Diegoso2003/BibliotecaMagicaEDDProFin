/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.enums;

import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public enum PrioridadEnum {
    COSTO("costo"),
    TIEMPO("tiempo");
    
    private final String descripcion;

    private PrioridadEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public static Optional<PrioridadEnum> obtenerPrioridad(String p){
        for(PrioridadEnum prioridad : values()){
            if(prioridad.descripcion.equalsIgnoreCase(p)){
                return Optional.of(prioridad);
            }
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}
