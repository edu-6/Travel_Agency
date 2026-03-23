/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.travels.rest.api.modelos.enums;

/**
 *
 * @author edu
 */
public class TipoServicio {
    private String nombre;
    private int id;

    public TipoServicio(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public TipoServicio() {
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }
    
    
}
