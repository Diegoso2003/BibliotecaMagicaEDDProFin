/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.validadores;

/**
 *
 * @author rafael-cayax
 */
public class ValidadorBiblioteca extends Validador{

    private String id = "";
    private String nombre = "";
    private String ubicacion = "";
    private String ingreso = "";
    private String traspaso = "";
    private String despacho = "";

    public void agregarBiblioteca(String linea) {
        int inicio = buscarCampo(id, 0, false);
        inicio = buscarCampo(nombre, inicio, false);
        inicio = buscarCampo(ubicacion, inicio, false);
        
    }
    
}
