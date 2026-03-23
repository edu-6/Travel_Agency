/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.travels.rest.api.servicios;

import com.mycompany.travels.rest.api.db.PaqueteServicioDB;
import com.mycompany.travels.rest.api.db.PaquetesDB;
import com.mycompany.travels.rest.api.exceptions.ExceptionGenerica;
import com.mycompany.travels.rest.api.interfaces.BuscarVariosInt;
import com.mycompany.travels.rest.api.interfaces.BusquedaUnitariaString;
import com.mycompany.travels.rest.api.interfaces.CreacionEntidad;
import com.mycompany.travels.rest.api.interfaces.EdicionEntidad;
import com.mycompany.travels.rest.api.modelos.Paquete_servicio;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class PaqueteServicioCService extends CrudService implements CreacionEntidad<Paquete_servicio>, EdicionEntidad<Paquete_servicio>,
        BusquedaUnitariaString<Paquete_servicio>, BuscarVariosInt<Paquete_servicio> {

    private final PaquetesDB paquetesDB = new PaquetesDB();
    private final PaqueteServicioDB db = new PaqueteServicioDB();

    @Override
    public void crear(Paquete_servicio entidad) throws ExceptionGenerica {

        this.revisarDatosCorrectos(entidad);
        this.asegurarGanancia(entidad);
        db.crear(entidad);
    }

    /**
     * Asegura que haya ganancia con el precio del servicio
     * @param entidad
     * @throws ExceptionGenerica
     */
    private void asegurarGanancia(Paquete_servicio entidad) throws ExceptionGenerica {
        double precio = entidad.getPrecio();
        double gastosPaquete = paquetesDB.sumarGastosPaquete(entidad.getId_paquete());
        double precioPaquete = paquetesDB.obtenerPrecioPaquete(entidad.getId_paquete());
        double ganaciaPaquete = precioPaquete - gastosPaquete;

        double gananciaActualizada = ganaciaPaquete - precio;
        if (gananciaActualizada <= 0) {
            throw new ExceptionGenerica("El servicio no deja ganancias, eleve el precio del paquete GANANCIA" + gananciaActualizada);
        }

        paquetesDB.actualizarGanancia(entidad.getId_paquete(), gananciaActualizada);

    }

    @Override
    public void editar(Paquete_servicio entidad) throws ExceptionGenerica {
        this.revisarDatosCorrectos(entidad);
        this.asegurarGanancia(entidad);
        db.editar(entidad);

    }

    @Override
    public Paquete_servicio buscar(String nombre) throws ExceptionGenerica {
        if (nombre == null || nombre.isBlank()) {
            throw new ExceptionGenerica("Busqueda vacia");
        }
        return db.buscar(nombre);

    }

    @Override
    public ArrayList<Paquete_servicio> buscarVariosInt(int param) throws ExceptionGenerica {
        return db.buscarVariosInt(param);
    }

}
