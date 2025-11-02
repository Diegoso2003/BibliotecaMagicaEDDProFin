/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend;

import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author rafael-cayax
 */
public class ExportadorSvg {

    public void exportarGrafica(String nombreArchivo, String svg) throws EntradaException {
        try {
            Path carpeta = Paths.get("graficas");
            Files.createDirectories(carpeta);

            String nombreArchivoFinal = String.format("grafica_%s.svg",
                    nombreArchivo
            );

            Path archivo = carpeta.resolve(nombreArchivoFinal);

            try (BufferedWriter writer = Files.newBufferedWriter(archivo, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                writer.write(svg);
                writer.flush();
            }

        } catch (IOException e) {
            throw new EntradaException("error al exportar el archivo");
        }
    }
}
