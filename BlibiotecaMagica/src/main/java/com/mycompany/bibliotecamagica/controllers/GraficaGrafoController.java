/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bibliotecamagica.controllers;


import com.mycompany.bibliotecamagica.backend.GeneradorSvg;
import com.mycompany.bibliotecamagica.backend.RedBibliotecas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        GeneradorSvg generador = new GeneradorSvg(RedBibliotecas.INSTANCIA.obtenerDot());
        webView = new WebView();
        webView.getEngine().loadContent(generador.generarSVG(), "image/svg+xml");
        panelGrafo.setCenter(webView);
    }    

}
