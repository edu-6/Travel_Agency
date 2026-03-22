/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.travels.rest.api.modelos;

/**
 *
 * @author edu
 */
public class Destino extends Entidad {
    private String nombre;
    private String descripcion;
    private String pais;
    private String mejorEpoca;
    private String urlImagen;
    
    private int id_pais;
    private int id;

    //para crear 
    public Destino(String nombre, String descripcion, String mejorEpoca, String urlImgen, int id_pais) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.mejorEpoca = mejorEpoca;
        this.urlImagen = urlImgen;
        this.id_pais = id_pais;
    }
    
    // para mostrar y editar
    public Destino(String nombre, String descripcion, String pais, String mejorEpoca, String urlImgen, int id_pais, int id) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.pais = pais;
        this.mejorEpoca = mejorEpoca;
        this.urlImagen = urlImgen;
        this.id_pais = id_pais;
        this.id = id;
    }

    
    @Override
    public boolean datosCompletos() {
        return nombre != null && !nombre.isBlank()
                && descripcion != null && !descripcion.isBlank()
                && urlImagen != null && !urlImagen.isBlank()
                && mejorEpoca != null && !mejorEpoca.isBlank();
    }

    @Override
    public boolean datosTamañoCorrecto() {
        return
        nombre.length()<=40 &&
        descripcion.length()<= 300 &&
        mejorEpoca.length()<= 100 &&
        urlImagen.length()<= 400 ;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPais() {
        return pais;
    }

    public String getMejorEpoca() {
        return mejorEpoca;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public int getId_pais() {
        return id_pais;
    }

    public int getId() {
        return id;
    }
    
    
    
    

}
