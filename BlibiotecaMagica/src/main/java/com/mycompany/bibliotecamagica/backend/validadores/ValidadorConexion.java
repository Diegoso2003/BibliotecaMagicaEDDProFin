/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.validadores;

import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.backend.modelos.Conexion;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author rafael-cayax
 */
public class ValidadorConexion extends Validador<Conexion>{
    private StringBuilder origen;
    private StringBuilder destino;
    private StringBuilder tiempo;
    private StringBuilder costo;

    @Override
    protected void limpiarCampos() {
        origen = new StringBuilder();
        destino = new StringBuilder();
        tiempo = new StringBuilder();
        costo = new StringBuilder();
    }

    @Override
    protected void extraerDatos() throws EntradaException {
        int inicio = buscarCampo(origen, 0, false);
        inicio = buscarCampo(destino, inicio, false);
        inicio = buscarNumero(tiempo, inicio, false);
        inicio = buscarNumero(costo, inicio, true);
        validarFinal(inicio);
    }

    @Override
    protected boolean camposValidos() {
        return !origen.toString().isBlank() && !destino.toString().isBlank() && !tiempo.toString().isBlank()
                && !costo.toString().isBlank();
    }

    @Override
    protected Conexion validarYObtenerRegistro() throws EntradaException {
        validarIDBiblio(origen);
        validarIDBiblio(destino);
        long t = obtenerTiempo(tiempo);
        try {
            BigDecimal c = new BigDecimal(costo.toString()).setScale(2, RoundingMode.HALF_UP);
        } catch (NumberFormatException e){
            throw new EntradaException("Precio no valido: \"" + costo + "\"");
        }
        return new Conexion();
    }

    @Override
    protected void agregarRegistro(Conexion nuevo) {

    }
    
}
