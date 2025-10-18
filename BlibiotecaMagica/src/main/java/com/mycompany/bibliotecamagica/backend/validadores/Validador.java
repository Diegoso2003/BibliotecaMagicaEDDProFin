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
public abstract class Validador {
    protected String linea;
        
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
        for(; inicio < this.linea.length(); inicio++){
            char c = this.linea.charAt(inicio);
            if(!num && esNumero(c)){
                num = true;
                i = inicio;
            }else if((esCaracterIgnorable(c) || (c == ',' && !ultimo)) && num){
                linea.append(this.linea.substring(i, inicio));
                return ++inicio;
            }else if(!esCaracterIgnorable(c) && !esNumero(c)){
                throw new EntradaException("Tiempo invalido : " + c);
            }
        }
        linea.append(this.linea.substring(i));
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
    
    private boolean esNumero(char c){
        return c >= '0' && c <= '9';
    }
    
}
