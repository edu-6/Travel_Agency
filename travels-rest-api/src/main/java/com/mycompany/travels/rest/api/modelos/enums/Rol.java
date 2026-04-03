/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.travels.rest.api.modelos.enums;

/**
 *
 * @author edu
 */
public class Rol {
    
    private String nombre;
    private int idRol;

    public Rol(String nombre, int idRol) {
        this.nombre = nombre;
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    
    
    
}
