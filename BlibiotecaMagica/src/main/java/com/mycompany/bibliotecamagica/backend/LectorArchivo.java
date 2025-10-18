/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend;

import com.mycompany.bibliotecamagica.backend.exception.ArchivoException;
import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.backend.validadores.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author rafael-cayax
 */
public class LectorArchivo {
    private final File[] archivos;
    private boolean hayError;
    private StringBuilder log;

    public LectorArchivo(File[] archivos) {
        this.archivos = archivos;
        hayError = false;
        log = new StringBuilder();
    }

    public void leer() throws ArchivoException{
        if(!hayArchivo())throw new ArchivoException("Ingresar almenos un archivo para analizar");
        for(File archivo : archivos) validarArchivo(archivo);
        for(int i = 0; i < archivos.length; i++) leerArchivo(i);
    }
    
    private boolean hayArchivo(){
        for(File archivo: archivos){
            if(archivo != null) return true;
        }
        return false;
    }
    
    private void validarArchivo(File file) throws ArchivoException{
        if(file == null) return;
        if(!file.exists()) throw new ArchivoException("El archivo: " + file.getName() + " no existe");
        if(!file.canRead()) throw new ArchivoException("El archivo: " + file.getName() + " no se puede leer");
    }
    
    private void leerArchivo(int tipo) throws ArchivoException{
        File archivo = archivos[tipo];
        if (archivo == null) return;
        boolean error = false;
        int contador = 0;
        try (BufferedReader br = new BufferedReader(
                new FileReader(archivo, StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contador++;
                if(linea.isBlank()) continue;
                try {
                    switch(tipo){
                    case 0 -> {
                        var validador = new ValidadorBiblioteca();
                        validador.agregarBiblioteca(linea);
                    }
                    default -> {
                        
                    }
                }
                } catch (EntradaException e){
                    hayError = true;
                    if(!error){
                        error = true;
                        log.append("Error en el archivo : ").append(archivo.getName());
                    }
                    log.append("\n\tError en la linea: ").append(contador).append("\n");
                    log.append("\t\t").append(e.getMessage()).append("\n");
                    log.append("\t\t").append(linea);
                }
            }
        } catch (IOException e) {
            throw new ArchivoException("Error al leer el archivo " + archivo.getName());
        }
    }

    public boolean isHayError() {
        return hayError;
    }
    
    public String getLog() {
        return log.toString();
    }
}
