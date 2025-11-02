/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bibliotecamagica.controllers;


import com.mycompany.bibliotecamagica.backend.GeneradorSvg;
import com.mycompany.bibliotecamagica.backend.RedBibliotecas;
import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.frontend.Auxiliar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author rafael-cayax
 */
public class GraficaGrafoController implements Initializable {
    @FXML private BorderPane panelGrafo;
    private WebView webView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                Platform.runLater(() -> {
                    try {
                        webView = new WebView();
                        panelGrafo.setCenter(webView);
                        GeneradorSvg generador = new GeneradorSvg(RedBibliotecas.INSTANCIA.obtenerDot());
                        webView.getEngine().loadContent(generador.generarSVG(), "image/svg+xml");
                    } catch (EntradaException ex) {
                        Auxiliar.lanzarAlerta(Alert.AlertType.ERROR, "Error", ex.getMessage(), panelGrafo);
                    }
                });
                return null;
            }
        };
        new Thread(task).start();
    }    

}
