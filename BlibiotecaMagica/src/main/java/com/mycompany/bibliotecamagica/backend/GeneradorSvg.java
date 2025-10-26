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
    }
    
    public String generarSVG() {
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
            return "<svg><text x='10' y='20'>Error: error al generar la imagen </text></svg>";
        }
    }
}
