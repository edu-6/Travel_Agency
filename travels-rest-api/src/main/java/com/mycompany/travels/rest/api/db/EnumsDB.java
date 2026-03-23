/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.travels.rest.api.db;

import com.mycompany.travels.rest.api.exceptions.ExceptionGenerica;
import com.mycompany.travels.rest.api.modelos.enums.MetodoPago;
import com.mycompany.travels.rest.api.modelos.enums.Nacionalidad;
import com.mycompany.travels.rest.api.modelos.enums.Pais;
import com.mycompany.travels.rest.api.modelos.enums.TipoServicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author edu
 */

public class EnumsDB {

    private final String BUSCAR_PAISES = "SELECT * FROM pais";
    private final String BUSCAR_METODOS_PAGO = "SELECT * FROM metodo_pago";
    private final String BUSCAR_NACIONALIDADES = "SELECT * FROM nacionalidad";
    private final String BUSCAR_TIPOS_SERVICIO = "SELECT * FROM tipo_servicio";

    private Connection conexion;

    public EnumsDB(Connection conexion) {
        this.conexion = conexion;
    }

    public ArrayList<MetodoPago> obtenerMetodosPago() throws ExceptionGenerica {
        ArrayList<MetodoPago> lista = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(BUSCAR_METODOS_PAGO);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new MetodoPago(rs.getString("metodo_pago_nombre"), rs.getInt("metodo_pago_id")));
            }
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al cargar metodos de pago ");
        }
        return lista;
    }

    public ArrayList<Pais> obtenerPaises() throws ExceptionGenerica {
        ArrayList<Pais> lista = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(BUSCAR_PAISES);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Pais(rs.getString("pais_nombre"), rs.getInt("pais_id")));
            }
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al cargar paises");
        }
        return lista;
    }

    public ArrayList<Nacionalidad> obtenerNacionalidades() throws ExceptionGenerica {
        ArrayList<Nacionalidad> lista = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(BUSCAR_NACIONALIDADES);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Nacionalidad(rs.getString("nacionalidad_nombre"), rs.getInt("nacionalidad_id")));
            }
        } catch (SQLException e) {
           throw new ExceptionGenerica("Error al cargar nacionalidades");
        }
        return lista;
    }

    public ArrayList<TipoServicio> obtenerTiposServicio() throws ExceptionGenerica {
        ArrayList<TipoServicio> lista = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(BUSCAR_TIPOS_SERVICIO);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new TipoServicio(rs.getString("tipo_servicio_nombre"), rs.getInt("tipo_servicio_id")));
            }
        } catch (SQLException e) {
            throw new ExceptionGenerica("Error al cargar tipos de servicio");
        }
        return lista;
    }
}
