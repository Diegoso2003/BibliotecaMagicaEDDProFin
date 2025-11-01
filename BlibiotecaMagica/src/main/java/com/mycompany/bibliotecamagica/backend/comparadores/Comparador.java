/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecamagica.backend.comparadores;

import java.text.Collator;
import java.util.Locale;

/**
 *
 * @author rafael-cayax
 */
public abstract class Comparador {

    private static final Collator COLLATOR_ESPAÑOL;

    static {
        COLLATOR_ESPAÑOL = Collator.getInstance(Locale.of("es", "ES"));
        COLLATOR_ESPAÑOL.setStrength(Collator.PRIMARY);
    }

    protected int compararEspañol(String str1, String str2) {
        return COLLATOR_ESPAÑOL.compare(str1, str2);
    }
}
