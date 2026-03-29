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
import com.mycompany.travels.rest.api.interfaces.EliminacionEntidad;
import com.mycompany.travels.rest.api.interfaces.ExisteEntidad;
import com.mycompany.travels.rest.api.interfaces.ExtraerEntidad;
import com.mycompany.travels.rest.api.modelos.Destino;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class DestinosDB implements CreacionEntidad<Destino>, EdicionEntidad<Destino>,
        ExisteEntidad, BusquedaUnitariaString<Destino>,
        ExtraerEntidad<Destino>, BuscarTodos<Destino>,EliminacionEntidad {

    
    private static final String CREAR = "INSERT INTO destino (destino_nombre,destino_descripcion,destino_mejor_epoca, destino_url_imagen, destino_id_pais)"
            + "VALUES (?,?,?,?,?) ";
    
    private static final String EDITAR = "UPDATE destino SET destino_nombre = ?, destino_descripcion = ?, destino_mejor_epoca = ?, destino_url_imagen = ?,"
            + " destino_id_pais = ? WHERE destino_id = ? ";
    
    private static final String EXISTE = "select destino_nombre FROM destino where destino_nombre = ?";
    
    private static final String BUSCAR_UNO = "select destino.*, pais_nombre FROM destino"
            + " JOIN  pais ON pais_id = destino_id_pais"
            + " WHERE destino_nombre = ?";
    
    private static final String BUSCAR_TODOS = "select destino.*, pais_nombre FROM destino"
            + " JOIN  pais ON pais_id = destino_id_pais";
    
    private static final String ELIMINAR = "DELETE FROM  destino WHERE destino_id = ?";
    
    @Override
    public void crear(Destino entidad) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(CREAR)){
            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getDescripcion());
            ps.setString(3, entidad.getMejorEpoca());
            ps.setString(4, entidad.getUrlImagen());
            ps.setInt(5, entidad.getId_pais());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionGenerica("falló al registrar destino");
        }
    }

    @Override
    public void editar(Destino entidad) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(EDITAR)){
            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getDescripcion());
            ps.setString(3, entidad.getMejorEpoca());
            ps.setString(4, entidad.getUrlImagen());
            ps.setInt(5, entidad.getId_pais());
            ps.setInt(6, entidad.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionGenerica("falló al actualizar destino "+ e.getMessage() );
        }
    }

    @Override
    public boolean existeEntidad(String nombre) throws ExceptionGenerica {
         try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(EXISTE)){
            ps.setString(1, nombre);
            ResultSet rs =  ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new ExceptionGenerica("Falló al buscar destino "+ e.getMessage());
        }
    }


    @Override
    public Destino buscar(String nombre) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(BUSCAR_UNO)){
            ps.setString(1, nombre);
            ResultSet rs =  ps.executeQuery();
            if(rs.next()){
                return extraer(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new ExceptionGenerica("Falló al buscar el destino "+ e.getMessage());
        }
    }

    @Override
    public Destino extraer(ResultSet rs) throws SQLException {
        return new Destino(
                rs.getString("destino_nombre"),
                rs.getString("destino_descripcion"),
                rs.getString("pais_nombre"),
                rs.getString("destino_mejor_epoca"),
                rs.getString("destino_url_imagen"),
                rs.getInt("destino_id_pais"),
                rs.getInt("destino_id")
        );
    }

    @Override
    public ArrayList<Destino> buscarTodos() throws ExceptionGenerica {
        ArrayList<Destino> lista = new ArrayList();
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(BUSCAR_TODOS)){
            ResultSet rs =  ps.executeQuery();
            while(rs.next()){
                lista.add(extraer(rs));
            }
        } catch (SQLException e) {
            throw new ExceptionGenerica("Falló al buscar destinos "+e.getMessage());
        }
        return lista;
     }

    @Override
    public void eliminar(int id) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(ELIMINAR)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionGenerica("Falló al eliminar el destino "+ e.getMessage());
        }
    }
    
}
