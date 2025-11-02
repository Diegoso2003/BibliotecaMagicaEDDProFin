/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bibliotecamagica.controllers;

import com.mycompany.bibliotecamagica.backend.ExportadorSvg;
import com.mycompany.bibliotecamagica.backend.GeneradorSvg;
import com.mycompany.bibliotecamagica.backend.RedBibliotecas;
import com.mycompany.bibliotecamagica.backend.enums.EstructuraEnum;
import com.mycompany.bibliotecamagica.backend.estructuras.grafo.NodoGrafo;
import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.backend.iterador.IteradorLista;
import com.mycompany.bibliotecamagica.backend.modelos.Biblioteca;
import com.mycompany.bibliotecamagica.frontend.Auxiliar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author rafael-cayax
 */
public class VistaEstructurasController implements Initializable {

    @FXML ComboBox<Biblioteca> biblioteca;
    @FXML ComboBox<EstructuraEnum> estructura;
    @FXML Button exportar;
    @FXML Button botonRecargar;
    @FXML WebView vistaGrafica;
    private String svg;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Biblioteca> bibliotecas = FXCollections.observableArrayList();
        ObservableList<EstructuraEnum> estructuras = FXCollections.observableArrayList(EstructuraEnum.values());
        IteradorLista<NodoGrafo> lista = RedBibliotecas.INSTANCIA.getBibliotecas().getIterador();
        while(lista.haySiguiente()){
            bibliotecas.add(lista.getActual().getBiblioteca());
        }
        estructura.setItems(estructuras);
        biblioteca.setItems(bibliotecas);
    }    

    @FXML
    private void validarSeleccion(){
        Biblioteca bibliotecaSeleccionada = biblioteca.getValue();
        EstructuraEnum estructuraSeleccionada = estructura.getValue();
        if(bibliotecaSeleccionada != null && estructuraSeleccionada != null){
            try {
                String auxiliar = "";
                switch(estructuraSeleccionada){
                    case ARBOL_AUTOR -> auxiliar = bibliotecaSeleccionada.obtenerDotBMasAutor();
                    case ARBOL_AÑO -> auxiliar = bibliotecaSeleccionada.obtenerDotBAño();
                    case ARBOL_GENERO -> auxiliar = bibliotecaSeleccionada.obtenerDotBMasGenero();
                    case ARBOL_TITULO -> auxiliar = bibliotecaSeleccionada.obtenerDotAvl();
                    default -> auxiliar = bibliotecaSeleccionada.obtenerDotTabla();
                }
                GeneradorSvg generador = new GeneradorSvg(auxiliar);
                svg = generador.generarSVG();
                vistaGrafica.getEngine().loadContent(svg, "image/svg+xml");
                exportar.setDisable(false);
                botonRecargar.setDisable(false);
            } catch (EntradaException ex) {
                Auxiliar.lanzarAlerta(Alert.AlertType.ERROR, "Error", ex.getMessage(), exportar);
            }
        }
    }
    
    @FXML
    private void exportarGrafica(){
        ExportadorSvg exportador = new ExportadorSvg();
        Biblioteca bibliotecaSeleccionada = biblioteca.getValue();
        EstructuraEnum estructuraSeleccionada = estructura.getValue();
        if(bibliotecaSeleccionada != null && estructuraSeleccionada != null){
            try {
                String nombre = bibliotecaSeleccionada.getId() + estructuraSeleccionada.name();
                exportador.exportarGrafica(nombre, svg);
                Auxiliar.lanzarAlerta(Alert.AlertType.INFORMATION, "Exito", "grafica guardada en la carpeta graficas con el nombre: "
                        + nombre +".svg", exportar);
            } catch (EntradaException ex) {
                Auxiliar.lanzarAlerta(Alert.AlertType.ERROR, "Error", ex.getMessage(), exportar);
            }
        }
    }
}
