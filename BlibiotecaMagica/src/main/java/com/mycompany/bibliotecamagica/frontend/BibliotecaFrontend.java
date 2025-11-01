/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.frontend;

import com.mycompany.bibliotecamagica.backend.modelos.Biblioteca;
import com.mycompany.bibliotecamagica.backend.modelos.Libro;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author rafael-cayax
 */
public class BibliotecaFrontend {

    private static final double TAMAÑO = 300;
    private VBox vista;
    private final ObservableList<String> datosIngreso = FXCollections.observableArrayList();;
    private final ObservableList<String> datosTraspaso = FXCollections.observableArrayList();;
    private final ObservableList<String> datosDespacho = FXCollections.observableArrayList();;
    private final Label tiempoIngreso = new Label();
    private final Label tiempoTraspaso = new Label();
    private final Label tiempoDespacho = new Label();

    public BibliotecaFrontend(Biblioteca biblioteca) {
        crearPanel(new Label(biblioteca.toString()));
    }

    private void crearPanel(Label nombre) {
        vista = new VBox(10);
        vista.setAlignment(Pos.CENTER);
        GridPane grid = new GridPane();
        grid.setHgap(5);
        ColumnConstraints col = new ColumnConstraints();
        col.setPercentWidth(33.33);
        col.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().addAll(col, col, col);
        VBox colaIngreso = crearColumnaConLista("Cola de ingreso", datosIngreso, tiempoIngreso);
        VBox colaTraspaso = crearColumnaConLista("Cola de traspaso", datosTraspaso, tiempoTraspaso);
        VBox colaDespacho = crearColumnaConLista("Cola de despacho", datosDespacho, tiempoDespacho);
        grid.add(colaIngreso, 0, 0);
        grid.add(colaTraspaso, 1, 0);
        grid.add(colaDespacho, 2, 0);
        nombre.getStyleClass().add("titulo");
        vista.getChildren().addAll(nombre, grid);
    }

    private VBox crearColumnaConLista(String titulo, ObservableList<String> colaItems, Label tiempo) {
        VBox columna = new VBox(8);
        Label lblTitulo = new Label(titulo);
        lblTitulo.getStyleClass().add("subtitulo");
        ListView<String> lista = new ListView<>();
        lista.setMinHeight(TAMAÑO);
        lista.setPrefHeight(TAMAÑO);
        lista.setMaxHeight(TAMAÑO);
        lista.setSelectionModel(null);
        lista.setFocusTraversable(false);
        lista.setStyle(
                  "-fx-background-insets: 0;"
                + "-fx-padding: 0;"
        );
        lista.setItems(colaItems);
        columna.getChildren().addAll(lblTitulo, tiempo,lista);
        lista.setCellFactory(lv -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    setAlignment(Pos.CENTER);
                }
            }
        });
        columna.setAlignment(Pos.CENTER);
        return columna;
    }

    public VBox getVista() {
        return vista;
    }

    public void colocarEnEntrada(Libro libro) {
        Platform.runLater(() -> {
            datosIngreso.add(libro.getIsbn());
        });
    }

    public void eliminarPrimeroEntrada() {
        Platform.runLater(() -> {
            datosIngreso.remove(0);
        });
    }

    public void colocarEnTraspaso(Libro libro) {
        Platform.runLater(() -> {
            datosTraspaso.add(libro.getIsbn());
        });
    }

    public void eliminarPrimeroTraspaso() {
        Platform.runLater(() -> {
            datosTraspaso.remove(0);
        });
    }

    public void colocarEnDespacho(Libro libro) {
        Platform.runLater(() -> {
            datosDespacho.add(libro.getIsbn());
        });
    }

    public void eliminarPrimeroDespacho() {
        Platform.runLater(() -> {
            datosDespacho.remove(0);
        });
    }

    public Label getTiempoIngreso() {
        return tiempoIngreso;
    }

    public Label getTiempoTraspaso() {
        return tiempoTraspaso;
    }

    public Label getTiempoDespacho() {
        return tiempoDespacho;
    }
    
}
