/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bibliotecamagica.controllers;

import com.mycompany.bibliotecamagica.backend.RedBibliotecas;
import com.mycompany.bibliotecamagica.backend.calculo_ruta.InfoTraslado;
import com.mycompany.bibliotecamagica.backend.iterador.IteradorLista;
import com.mycompany.bibliotecamagica.frontend.Auxiliar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author rafael-cayax
 */
public class RegistroTrasladoController implements Initializable {

    @FXML private TableView<InfoTraslado> registros;
    @FXML private TableColumn<InfoTraslado, String> numero;
    @FXML private TableColumn<InfoTraslado, String> isbn;
    @FXML private TableColumn<InfoTraslado, String> ruta;
    @FXML private TableColumn<InfoTraslado, String> tiempo;
    @FXML private TableColumn<InfoTraslado, String> costo;
    @FXML private TableColumn<InfoTraslado, String> prioridad;

    private final ObservableList<InfoTraslado> datos = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numero.setPrefWidth(55);
        numero.setMinWidth(55);
        numero.setMaxWidth(55);
        configurarColumnas();
        agregarDatos();
    }

    private void agregarDatos() {
        IteradorLista<InfoTraslado> info = RedBibliotecas.INSTANCIA.getTraslados().getIterador();
        while (info.haySiguiente()) {
            datos.add(info.getActual());
        }
    }

    @FXML
    private void recargar() {
        datos.clear();
        agregarDatos();
    }

    private void configurarColumnas() {
        numero.setCellValueFactory(cellData -> {
            int index = registros.getItems().indexOf(cellData.getValue()) + 1;
            return new SimpleStringProperty(String.valueOf(index));
        });
        costo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCosto().toString()));
        isbn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLibro().getIsbn()));
        ruta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRuta()));
        tiempo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTiempo().toString()));
        prioridad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrioridad().toString().toLowerCase()));
        Auxiliar.configurarSaltoDeLinea(ruta);
        registros.setItems(datos);
    }

}
