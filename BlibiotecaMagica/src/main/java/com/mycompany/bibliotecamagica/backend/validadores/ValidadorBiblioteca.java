/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.validadores;

import com.mycompany.bibliotecamagica.backend.RedBibliotecas;
import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.backend.modelos.Biblioteca;

/**
 *
 * @author rafael-cayax
 */
public class ValidadorBiblioteca extends Validador<Biblioteca>{

    private StringBuilder id;
    private StringBuilder nombre;
    private StringBuilder ubicacion;
    private StringBuilder ingreso;
    private StringBuilder traspaso;
    private StringBuilder despacho;

    @Override
    protected void extraerDatos() throws EntradaException {
        int inicio = buscarCampo(id, 0, false);
        inicio = buscarCampo(nombre, inicio, false);
        inicio = buscarCampo(ubicacion, inicio, false);
        inicio = buscarNumero(ingreso, inicio, false);
        inicio = buscarNumero(traspaso, inicio, false);
        inicio = buscarNumero(despacho, inicio, true);
        validarFinal(inicio);
    }
    
    @Override
    protected boolean camposValidos(){
        return !id.toString().isBlank() && !nombre.toString().isBlank() && !ubicacion.toString().isBlank()
                && !ingreso.toString().isBlank() && !traspaso.toString().isBlank() && !despacho.toString().isBlank();
    }
    
    @Override
    protected void limpiarCampos(){
        id = new StringBuilder();
        nombre = new StringBuilder();
        ubicacion = new StringBuilder();
        ingreso = new StringBuilder();
        traspaso = new StringBuilder();
        despacho = new StringBuilder();
    }

    @Override
    protected Biblioteca validarYObtenerRegistro() throws EntradaException {
        Biblioteca biblioteca = new Biblioteca();
        validarIDBiblio(id);
        biblioteca.setId(id.toString());
        biblioteca.setNombre(nombre.toString());
        biblioteca.setUbicacion(ubicacion.toString());
        biblioteca.settIngreso(obtenerTiempo(ingreso));
        biblioteca.settTraspaso(obtenerTiempo(traspaso));
        biblioteca.setdIntervalo(obtenerTiempo(despacho));
        return biblioteca;
    }

    @Override
    protected void agregarRegistro(Biblioteca nueva) throws EntradaException {
        if(!RedBibliotecas.INSTANCIA.agregar(nueva)){
            throw new EntradaException("El id: \"" + id + "\" ingresado ya pertenece a una biblioteca existente.");
        }
    }
}
