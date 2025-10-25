/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend;

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
        System.out.println(dot);
    }
    
    public String generarSVG() {
        try {
            // Crear el proceso de Graphviz
            ProcessBuilder pb = new ProcessBuilder("dot", "-Tsvg");
            Process process = pb.start();
            
            // Escribir el string DOT al stdin del proceso
            try (OutputStream stdin = process.getOutputStream();
                 OutputStreamWriter writer = new OutputStreamWriter(stdin)) {
                writer.write(dot);
                writer.flush();
            }
            
            // Leer el SVG del stdout
            StringBuilder svg = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    svg.append(line).append("\n");
                }
            }
            
            // Esperar a que termine el proceso
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                // Leer errores si los hay
                try (BufferedReader errorReader = new BufferedReader(
                        new InputStreamReader(process.getErrorStream()))) {
                    String errorLine;
                    while ((errorLine = errorReader.readLine()) != null) {
                        System.err.println("Graphviz error: " + errorLine);
                    }
                }
                throw new RuntimeException("Graphviz falló con código: " + exitCode);
            }
            
            return svg.toString();
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "<svg><text x='10' y='20'>Error: error al generar la imagen </text></svg>";
        }
    }
}
