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
public enum EstadoLibroEnum {
    DISPONIBLE("disponible"),
    PRESTADO("prestado"),
    AGOTADO("agotado"),
    EN_TRANSITO("en tránsito");
    
    private final String descripcion;

    private EstadoLibroEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static Optional<EstadoLibroEnum> obtenerEstado(String estado){
        for(EstadoLibroEnum estadoLibro : values()){
            if(estadoLibro.descripcion.equalsIgnoreCase(estado)){
                return Optional.of(estadoLibro);
            }
        }
        return Optional.empty();
    }
}