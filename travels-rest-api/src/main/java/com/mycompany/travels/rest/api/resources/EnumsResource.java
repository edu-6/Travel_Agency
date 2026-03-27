/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.travels.rest.api.resources;

import com.google.gson.Gson;
import com.mycompany.travels.rest.api.db.EnumsDB;
import com.mycompany.travels.rest.api.exceptions.ExceptionGenerica;
import com.mycompany.travels.rest.api.modelos.ErrorRequest;
import com.mycompany.travels.rest.api.modelos.enums.MetodoPago;
import com.mycompany.travels.rest.api.modelos.enums.Nacionalidad;
import com.mycompany.travels.rest.api.modelos.enums.Pais;
import com.mycompany.travels.rest.api.modelos.enums.TipoServicio;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
@WebServlet(name = "EnumsResource", urlPatterns = {"/enums/*"})
public class EnumsResource extends HttpServlet {

    private Gson gson = new Gson();
    private final EnumsDB db = new EnumsDB();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String tipoEnum = obtenerTipoEnum(req);
        if (tipoEnum == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        try {
            switch (tipoEnum) {
                case "paises":
                    ArrayList<Pais> paises = db.obtenerPaises();
                    resp.setStatus(HttpServletResponse.SC_OK);
                    escribirJson(resp, paises);
                    break;
                case "nacionalidades":
                    ArrayList<Nacionalidad> nacionalidaes = db.obtenerNacionalidades();
                    resp.setStatus(HttpServletResponse.SC_OK);
                    escribirJson(resp, nacionalidaes);
                    break;
                case "tipos-servicio":
                    ArrayList<TipoServicio> tiposServicio = db.obtenerTiposServicio();
                    resp.setStatus(HttpServletResponse.SC_OK);
                    escribirJson(resp, tiposServicio);
                    break;
                case "metodos-pago":
                    ArrayList<MetodoPago> metodosPago = db.obtenerMetodosPago();
                    resp.setStatus(HttpServletResponse.SC_OK);
                    escribirJson(resp, metodosPago);
                    break;
            }

        } catch (ExceptionGenerica e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ErrorRequest error = new ErrorRequest(e.getMessage(), e.getMessage());
            escribirJson(resp, error);
        }
    }

    private String obtenerTipoEnum(HttpServletRequest req) {
        String ruta = req.getPathInfo();
        if (ruta == null) {
            return ruta;
        }
        return ruta.substring(1);
    }

    private void escribirJson(HttpServletResponse res, Object data) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(gson.toJson(data));
    }
   
    
    
}
