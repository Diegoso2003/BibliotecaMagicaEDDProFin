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
public abstract class Validador <T> {
    protected String linea;
    
    protected abstract void extraerDatos() throws EntradaException;
    protected abstract void limpiarCampos();
    protected abstract boolean camposValidos();
    protected abstract T validarYObtenerRegistro() throws EntradaException;
    protected abstract void agregarRegistro(T nuevo) throws EntradaException;
    
    public void iniciarAnalisis(String linea) throws EntradaException{
        this.linea = linea;
        limpiarCampos();
        extraerDatos();
        if(!camposValidos()) throw new EntradaException("Ingresar todos los campos");
        T nueva = validarYObtenerRegistro();
        agregarRegistro(nueva);
    }
        
    protected int buscarCampo(StringBuilder linea, int inicio, boolean ultimo) throws EntradaException{
        int i = inicio;
        boolean comillas = false;
        for(;inicio < this.linea.length();inicio++){
            char c = this.linea.charAt(inicio);
            if(c == '"'){
                if(!linea.isEmpty()) throw new EntradaException("Hace falta una coma");
                if(!comillas){
                    i = inicio + 1;
                } else {
                    linea.append(this.linea.substring(i, inicio));
                    if(ultimo) return ++inicio;
                }
                comillas = !comillas;
            } else if(!comillas && c == ',' && !ultimo){
                return ++inicio;
            } else if(!comillas && !esCaracterIgnorable(c)){
                throw new EntradaException("Formato no valido");
            }
        }
        return ++inicio;
    }
    
    protected int buscarNumero(StringBuilder linea, int inicio, boolean ultimo) throws EntradaException{
        int i = inicio;
        boolean num = false;
        for (; inicio < this.linea.length(); inicio++) {
            char c = this.linea.charAt(inicio);
            if(!esCaracterIgnorable(c) && !num){
                if(!linea.isEmpty()) throw new EntradaException("Hace falta una coma");
                i = inicio;
                num = true;
            } else if(esCaracterIgnorable(c) && num){
                linea.append(this.linea.substring(i, inicio));
                if(ultimo) return ++inicio;
                num = false;
            } else if(c == ',' && !ultimo){
                if(num) linea.append(this.linea.substring(i, inicio));
                return ++inicio;
            }
        }
        if(inicio <= this.linea.length())linea.append(this.linea.substring(i));
        return inicio;
    }
    
    protected void validarFinal(int inicio) throws EntradaException{
        for(;inicio < this.linea.length(); inicio++){
            char c = this.linea.charAt(inicio);
            if(!esCaracterIgnorable(c)){
                throw new EntradaException("Formato invalido");
            }
        }
    }
    
    private boolean esCaracterIgnorable(char c){
        return c == ' ' || c == '\t' || c == '\n';
    }
    
    private boolean esLetra(char c){
        return (c >= 'A' && c <= 'Z') || c == 'Ñ';
    }
    
    protected boolean esNumero(char c){
        return c >= '0' && c <= '9';
    }
    
    protected void validarIDBiblio(StringBuilder texto) throws EntradaException{
        if (texto.length() != 5 || !esLetra(texto.charAt(0)) || texto.charAt(1) != '-') {
            throw new EntradaException("ID de biblioteca invalido: \"" + texto + "\"");
        }
        for (int i = 2; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if(!esNumero(c)) throw new EntradaException("ID de biblioteca invalido: \"" + texto + "\"");
        }
    }
    
    protected long obtenerTiempo(StringBuilder texto) throws EntradaException{
        try {
            if(texto.charAt(0) == '0') throw new NumberFormatException();
            return Long.parseLong(texto.toString());
        } catch (NumberFormatException e) {
            throw new EntradaException("Tiempo ingresado invalido: \"" + texto + "\"");
        }
    }
    
}
