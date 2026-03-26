/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.travels.rest.api.resources;

import com.google.gson.Gson;
import com.mycompany.travels.rest.api.db.LoginDB;
import com.mycompany.travels.rest.api.dtos.login.UsuarioLoginRequest;
import com.mycompany.travels.rest.api.dtos.login.UsuarioLoginResponse;
import com.mycompany.travels.rest.api.exceptions.ExceptionGenerica;
import com.mycompany.travels.rest.api.modelos.ErrorRequest;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author edu
 */
@WebServlet(name = "LoginResource", urlPatterns = {"/login"})
public class LoginResource extends HttpServlet {
    
    private final Gson gson = new Gson();   
    private final LoginDB db = new LoginDB();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        this.configurarCabecerasRespuesta(resp);
        
        UsuarioLoginRequest loginRequest= gson.fromJson(req.getReader(), UsuarioLoginRequest.class);
        
        try {
            UsuarioLoginResponse loginReponse = db.loguear(loginRequest);
            
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(gson.toJson(loginReponse));
            
        } catch (ExceptionGenerica ex) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            
            ErrorRequest error = new ErrorRequest("error login",  ex.getMessage());
            resp.getWriter().write(gson.toJson(error));
        }
    }
    
    
    
    
    
    private void configurarCabecerasRespuesta(HttpServletResponse resp){
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }

}
