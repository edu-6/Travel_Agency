/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.travels.rest.api.servicios;

import com.mycompany.travels.rest.api.exceptions.CamposVaciosException;
import com.mycompany.travels.rest.api.exceptions.DatosMuyLargosException;
import com.mycompany.travels.rest.api.modelos.Entidad;

/**
 *
 * @author edu
 */
public abstract  class CrudService {
    
    protected void revisarDatosCorrectos(Entidad entidad) throws CamposVaciosException, DatosMuyLargosException{
        if(!entidad.datosCompletos()){
            throw new CamposVaciosException();
        }
        if(!entidad.datosTamañoCorrecto()){
            throw new DatosMuyLargosException();
        }
    }
}
