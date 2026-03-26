/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.travels.rest.api.db;

import com.mycompany.travels.rest.api.dtos.login.UsuarioLoginResponse;
import com.mycompany.travels.rest.api.dtos.login.UsuarioLoginRequest;
import com.mycompany.travels.rest.api.exceptions.EmpleadoNotFoundException;
import com.mycompany.travels.rest.api.exceptions.ExceptionGenerica;
import com.mycompany.travels.rest.api.interfaces.ExtraerEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class LoginDB implements ExtraerEntidad<UsuarioLoginResponse> {

    private static final String ENCONTRAR_EMPLEADO = "select empleado.*, rol_nombre from empleado"
            + " JOIN rol ON empleado_id_rol = rol_id where empleado_nombre = ? and empleado_contraseña = ?";

    public UsuarioLoginResponse loguear(UsuarioLoginRequest request) throws ExceptionGenerica {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(ENCONTRAR_EMPLEADO)) {
            ps.setString(1, request.getNombre());
            ps.setString(2, request.getContraseña());

            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return extraer(rs);
                }
            }
            throw new EmpleadoNotFoundException();
        } catch (SQLException e) {
            throw new ExceptionGenerica("error al buscar el registro" + e.getMessage());
        }
    }

    @Override
    public UsuarioLoginResponse extraer(ResultSet rs) throws SQLException {
        return new UsuarioLoginResponse(
                rs.getString("empleado_nombre"),
                rs.getInt("empleado_id"),
                rs.getString("rol_nombre")
        );
    }

}
