/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend;

import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 *
 * @author rafael-cayax
 */
public class GeneradorSvg {
    private final String dot;

    public GeneradorSvg(String dot) {
        this.dot = dot;
    }
    
    public String generarSVG() throws EntradaException {
        try {
            ProcessBuilder pb = new ProcessBuilder("dot", "-Tsvg");
            Process process = pb.start();
            
            try (OutputStream stdin = process.getOutputStream();
                 OutputStreamWriter writer = new OutputStreamWriter(stdin)) {
                writer.write(dot);
                writer.flush();
            }
            
            StringBuilder svg = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    svg.append(line).append("\n");
                }
            }
            
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new EntradaException("Error al cargar la grafica");
            }
            return svg.toString();
            
        } catch (IOException | InterruptedException e) {
            throw new EntradaException("Error al cargar la grafica");
        }
    }
}
