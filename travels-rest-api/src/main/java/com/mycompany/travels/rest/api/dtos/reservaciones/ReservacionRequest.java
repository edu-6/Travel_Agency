/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.travels.rest.api.dtos.reservaciones;

import com.mycompany.travels.rest.api.dtos.pasajeros.PasajeroRequest;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class ReservacionRequest {
    
    private String idTitular;
    private int idPaquete;
    private int idAgenteCreador;
    private LocalDate fechaCreacion;
    private LocalDate fechaViaje;
    private ArrayList<PasajeroRequest> pasajeros;

    public ReservacionRequest() {
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public int getIdAgenteCreador() {
        return idAgenteCreador;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDate getFechaViaje() {
        return fechaViaje;
    }

    public ArrayList<PasajeroRequest> getPasajeros() {
        return pasajeros;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public void setIdAgenteCreador(int idAgenteCreador) {
        this.idAgenteCreador = idAgenteCreador;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaViaje(LocalDate fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public void setPasajeros(ArrayList<PasajeroRequest> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public String getIdTitular() {
        return idTitular;
    }
    
    
    
    
}
