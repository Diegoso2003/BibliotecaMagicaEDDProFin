/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.validadores;

import com.mycompany.bibliotecamagica.backend.RedBibliotecas;
import com.mycompany.bibliotecamagica.backend.estructuras.grafo.NodoGrafo;
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
        Conexion conexion = new Conexion();
        validarIDBiblio(origen);
        validarIDBiblio(destino);
        if(destino.compareTo(origen) == 0){
            throw new EntradaException("Una biblioteca no puede tener una conexion consigo misma");
        }
        conexion.setTiempo(obtenerTiempo(tiempo.toString()));
        conexion.setPrecio(obtenerPrecio(costo.toString()));
        return conexion;
    }

    @Override
    protected void agregarRegistro(Conexion nuevo) throws EntradaException {
        nuevo.setBiblioAdyascente(obtenerNodoGrafo(destino.toString()).getBiblioteca());
        NodoGrafo nodoOrigen = obtenerNodoGrafo(origen.toString());
        if(!nodoOrigen.agregarConexion(nuevo)){
            throw new EntradaException("Ya existe una conexion entre ambas bibliotecas.");
        }
    }
    
    public BigDecimal obtenerPrecio(String costo) throws EntradaException{
        try {
            if(costo.charAt(0) == '0' || costo.charAt(0) == '-') throw new NumberFormatException();
            return new BigDecimal(costo).setScale(2, RoundingMode.HALF_UP);
        } catch (NumberFormatException e){
            throw new EntradaException("Precio no valido: \"" + costo + "\"");
        }
    }
    
    private NodoGrafo obtenerNodoGrafo(String id) throws EntradaException{
        return RedBibliotecas.INSTANCIA.buscar(id).orElseThrow(() ->
        new EntradaException("No se encontro una biblioteca con el id: \"" + id + "\""));
    }
    
}
