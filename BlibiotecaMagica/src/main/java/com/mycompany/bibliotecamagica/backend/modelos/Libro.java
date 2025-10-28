/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.modelos;

/**
 *
 * @author rafael-cayax
 */
public class Libro {
    private final String Isbn;
    private String titulo;
    private String genero;
    private String autor;
    private int año;

    public Libro(String Isbn) {
        this.Isbn = Isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getIsbn() {
        return Isbn;
    }
    
    public String getSinGuiones(){
        StringBuilder st = new StringBuilder();
        for(int i = 0; i < Isbn.length(); i++){
            if(st.charAt(i) != '-'){
                st.append(st.charAt(i));
            }
        }
        return st.toString();
    }

    @Override
    public String toString() {
        return getSinGuiones();
    }
    
}