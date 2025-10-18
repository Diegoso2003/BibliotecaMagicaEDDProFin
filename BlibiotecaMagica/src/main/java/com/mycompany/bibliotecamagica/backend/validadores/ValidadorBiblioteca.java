/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.validadores;

import com.mycompany.bibliotecamagica.backend.exception.EntradaException;

/**
 *
 * @author rafael-cayax
 */
public class ValidadorBiblioteca extends Validador{

    private StringBuilder id;
    private StringBuilder nombre;
    private StringBuilder ubicacion;
    private StringBuilder ingreso;
    private StringBuilder traspaso;
    private StringBuilder despacho;

    public void agregarBiblioteca(String linea) throws EntradaException {
        this.linea = linea;
        limpiarCampos();
        int inicio = buscarCampo(id, 0, false);
        inicio = buscarCampo(nombre, inicio, false);
        inicio = buscarCampo(ubicacion, inicio, false);
        inicio = buscarNumero(ingreso, inicio, false);
        inicio = buscarNumero(traspaso, inicio, false);
        inicio = buscarNumero(despacho, inicio, true);
        validarFinal(inicio);
        if(!camposValidos()) throw new EntradaException("Ingresar todos los campos");
    }
    
    private boolean camposValidos(){
        return !id.toString().isBlank() && !nombre.toString().isBlank() && !ubicacion.toString().isBlank()
                && !ingreso.toString().isBlank() && !traspaso.toString().isBlank() && !despacho.toString().isBlank();
    }
    
    private void limpiarCampos(){
        id = new StringBuilder();
        nombre = new StringBuilder();
        ubicacion = new StringBuilder();
        ingreso = new StringBuilder();
        traspaso = new StringBuilder();
        despacho = new StringBuilder();
    }
}
