/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bibliotecamagica.controllers;

import com.mycompany.bibliotecamagica.backend.RedBibliotecas;
import com.mycompany.bibliotecamagica.backend.enums.EstadoLibroEnum;
import com.mycompany.bibliotecamagica.backend.enums.PrioridadEnum;
import com.mycompany.bibliotecamagica.backend.estructuras.grafo.NodoGrafo;
import com.mycompany.bibliotecamagica.backend.exception.EntradaException;
import com.mycompany.bibliotecamagica.backend.iterador.IteradorLista;
import com.mycompany.bibliotecamagica.backend.modelos.Biblioteca;
import com.mycompany.bibliotecamagica.backend.modelos.EntradaLibro;
import com.mycompany.bibliotecamagica.backend.modelos.InfoLibro;
import com.mycompany.bibliotecamagica.backend.validadores.ValidadorLibro;
import com.mycompany.bibliotecamagica.frontend.Auxiliar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author rafael-cayax
 */
public class IngresoLibroController implements Initializable {
    @FXML private ComboBox<NodoGrafo> biblioOrigen;
    @FXML private ComboBox<NodoGrafo> biblioDestino;
    @FXML private ComboBox<EstadoLibroEnum> estado;
    @FXML private ComboBox<PrioridadEnum> prioridad;
    @FXML private TextField isbn;
    @FXML private TextField titulo;
    @FXML private TextField genero;
    @FXML private TextField año;
    @FXML private TextField autor;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarBibliotecas();
        iniciarEstadoYPrioridad();
    }    

    private void iniciarBibliotecas() {
        ObservableList<NodoGrafo> lista1 = FXCollections.observableArrayList();
        ObservableList<NodoGrafo> lista2 = FXCollections.observableArrayList();
        IteradorLista<NodoGrafo> iterador = RedBibliotecas.INSTANCIA.getBibliotecas().getIterador();
        while(iterador.haySiguiente()){
            lista1.add(iterador.getActual());
            lista2.add(iterador.getActual());
        }
        biblioOrigen.setItems(lista1);
        biblioDestino.setItems(lista2);
    }

    private void iniciarEstadoYPrioridad() {
        ObservableList<PrioridadEnum> prioridadLista 
                = FXCollections.observableArrayList(PrioridadEnum.COSTO, PrioridadEnum.TIEMPO);
        prioridad.setItems(prioridadLista);
        ObservableList<EstadoLibroEnum> estadoLista 
                = FXCollections.observableArrayList(EstadoLibroEnum.AGOTADO, EstadoLibroEnum.DISPONIBLE, EstadoLibroEnum.PRESTADO);
        estado.setItems(estadoLista);
    }
    
    @FXML
    private void ingresarLibro(){
        try {
            validarEntradas();
            InfoLibro libro = obtenerLibro();
            RedBibliotecas.INSTANCIA.getD().setPrioridad(prioridad.getValue());
            EntradaLibro nuevo = RedBibliotecas.INSTANCIA.getD().iniciarAnalisis(
                    biblioOrigen.getValue(), biblioDestino.getValue(), libro, true);
            Biblioteca primera = nuevo.obtenerSiguiente();
            primera.colocarEnEntrada(nuevo);
            RedBibliotecas.INSTANCIA.agregarNuevoTraslado(primera);
            limpiar();
            Auxiliar.lanzarAlerta(Alert.AlertType.INFORMATION, "Exito", "libro ingresado correctamente", año);
        } catch (EntradaException ex) {
            Auxiliar.lanzarAlerta(Alert.AlertType.ERROR, "Error", ex.getMessage(), año);
        }
    }

    private void validarEntradas() throws EntradaException {
        if(biblioOrigen.getValue() == null || biblioDestino.getValue() == null || prioridad.getValue() == null
                || estado.getValue() == null || isbn.getText().isBlank() || titulo.getText().isBlank() 
                || genero.getText().isBlank() || año.getText().isBlank() || autor.getText().isBlank()){
            throw new EntradaException("Llenar todos los campos para ingresar el libro");
        }
    }

    private InfoLibro obtenerLibro() throws EntradaException {
        ValidadorLibro validador = new ValidadorLibro();
        if(!validador.esIsbnValido(isbn.getText())){
            throw new EntradaException("isbn ingresado invalido: \"" + isbn.getText() + "\"");
        }
        validador.setAutor(new StringBuilder(autor.getText()));
        validador.setIsbn(new StringBuilder(isbn.getText()));
        validador.setTitulo(new StringBuilder(titulo.getText()));
        validador.setGenero(new StringBuilder(genero.getText()));
        validador.setAño(new StringBuilder(año.getText()));
        return new InfoLibro(validador.obtenerLibro(), estado.getValue());
    }

    private void limpiar() {
        autor.clear();
        isbn.clear();
        titulo.clear();
        genero.clear();
        año.clear();
    }
    
}
