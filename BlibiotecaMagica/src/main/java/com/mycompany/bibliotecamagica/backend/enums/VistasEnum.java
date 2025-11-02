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
    CARGAR_ARCHIVO("carga_archivo"),
    COLAS_DESPACHO("colas_despacho"),
    FORM_BIBLIOTECA("form_biblioteca"),
    FORM_CONEXION("form_conexion"),
    FORM_INGRESO_LIBRO("ingreso_libro"),
    GRAFICA_GRAFO("grafica_grafo"),
    REGISTRO_TRASLADO("registro_traslado"),
    VISTA_ESTRUCTURAS("vista_estructuras");
    
    private final String ventana;
    
    private VistasEnum(String ventana){
        this.ventana = ventana;
    }

    public String getVentana() {
        return ventana;
    }
    
}
