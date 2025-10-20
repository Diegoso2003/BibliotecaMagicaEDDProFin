/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.enums;

/**
 *
 * @author rafael-cayax
 */
public enum VistasEnum {
    CARGAR_ARCHIVO("carga_archivo");
    
    private final String ventana;
    
    private VistasEnum(String ventana){
        this.ventana = ventana;
    }

    public String getVentana() {
        return ventana;
    }
    
}
