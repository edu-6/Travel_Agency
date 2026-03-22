/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.travels.rest.api.db;

import com.mycompany.travels.rest.api.exceptions.ExceptionGenerica;
import com.mycompany.travels.rest.api.exceptions.NotFoundException;
import com.mycompany.travels.rest.api.interfaces.BuscarTodos;
import com.mycompany.travels.rest.api.interfaces.BusquedaUnitariaString;
import com.mycompany.travels.rest.api.interfaces.CreacionEntidad;
import com.mycompany.travels.rest.api.interfaces.EdicionEntidad;
import com.mycompany.travels.rest.api.interfaces.ExisteEntidad;
import com.mycompany.travels.rest.api.interfaces.ExtraerEntidad;
import com.mycompany.travels.rest.api.modelos.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class ProveedorDB implements CreacionEntidad<Proveedor>, EdicionEntidad<Proveedor>,
        ExisteEntidad, BusquedaUnitariaString<Proveedor>, ExtraerEntidad<Proveedor>, BuscarTodos<Proveedor>{


    private static final String CREAR = "INSERT INTO proveedor  (proveedor_nombre,proveedor_id_tipo,proveedor_contactos,proveedor_id_pais) "
            + "VALUES (?,?,?,?)";
    private static final String EDITAR = "UPDATE proveedor SET proveedor_nombre = ?, proveedor_id_tipo = ?,"
            + "proveedor_contactos = ?, proveedor_id_pais = ?  WHERE proveedor_id = ?";
    
    private static final String EXISTE = "select proveedor_id FROM proveedor where proveedor_nombre = ?";
    
    private static final String BUSCAR_UNO = "select proveedor.*, pais_nombre, tipo_servicio_nombre FROM proveedor"
            + " JOIN  pais ON pais_id = proveedor_id_pais JOIN tipo_servicio ON  tipo_servicio_id = proveedor_id_tipo"
            + " WHERE proveedor_nombre = ?";
    
    private static final String BUSCAR_TODOS = "select proveedor.*, pais_nombre, tipo_servicio_nombre FROM proveedor"
            + " JOIN  pais ON pais_id = proveedor_id_pais JOIN tipo_servicio ON  tipo_servicio_id = proveedor_id_tipo ";
    
    @Override
    public void crear(Proveedor entidad) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(CREAR)){
            ps.setString(1, entidad.getNombre());
            ps.setInt(2, entidad.getId_tipo_servicio());
            ps.setString(3, entidad.getContactos());
            ps.setInt(4, entidad.getId_pais());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionGenerica("falló al registrar proveedor");
        }
    }

    @Override
    public void editar(Proveedor entidad) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(EDITAR)){
            ps.setString(1, entidad.getNombre());
            ps.setInt(2, entidad.getId_tipo_servicio());
            ps.setString(3, entidad.getContactos());
            ps.setInt(4, entidad.getId_pais());
            ps.setInt(5, entidad.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionGenerica("falló al actualizar proveedor");
        }
    }

    @Override
    public boolean existeEntidad(String nombre) throws ExceptionGenerica {
         try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(EXISTE)){
            ps.setString(1, nombre);
            ResultSet rs =  ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new ExceptionGenerica("Falló al buscar proveedor");
        }
    }


    @Override
    public Proveedor buscar(String nombre) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(BUSCAR_UNO)){
            ps.setString(1, nombre);
            ResultSet rs =  ps.executeQuery();
            if(rs.next()){
                return extraer(rs);
            }
            throw new NotFoundException("no se encontró el proveedor");
        } catch (SQLException e) {
            throw new ExceptionGenerica("Falló al buscar proveedor");
        }
    }

    @Override
    public Proveedor extraer(ResultSet rs) throws SQLException {
        return new Proveedor(
                rs.getString("proveedor_nombre"),
                rs.getString("tipo_servicio_nombre"),
                rs.getString("pais_nombre"),
                rs.getString("proveedor_contactos"),
                rs.getInt("proveedor_id"),
                rs.getInt("proveedor_id_pais"),
                rs.getInt("proveedor_id_tipo")
        );
    }

    @Override
    public ArrayList<Proveedor> buscarTodos() throws ExceptionGenerica {
        ArrayList<Proveedor> lista = new ArrayList();
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(BUSCAR_TODOS)){
            ResultSet rs =  ps.executeQuery();
            while(rs.next()){
                lista.add(extraer(rs));
            }
        } catch (SQLException e) {
            throw new ExceptionGenerica("Falló al buscar proveedores");
        }
        return lista;
     }
    
}
