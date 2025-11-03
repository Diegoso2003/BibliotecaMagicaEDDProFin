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
public class ComparadorClaves implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        o1 = o1.toLowerCase();
        o2 = o2.toLowerCase();
        return o1.compareTo(o2);
    }
    
}
