/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.travels.rest.api.modelos.enums;

/**
 *
 * @author edu
 */
public class Pais {
    private String nombre;
    private int id;

    public Pais(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public Pais() {
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }
    
    
}
