/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.comparadores;

import java.util.Comparator;

/**
 *
 * @author rafael-cayax
 */
public class ComparadorClaves extends Comparador implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        return compararEspañol(o1, o2);
    }
    
}
