/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.travels.rest.api.resources;

import com.google.gson.Gson;
import com.mycompany.travels.rest.api.modelos.ErrorRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author edu
 */
public class EscritoriJson {
    private final Gson gson = new Gson();
    
    public void escribirJson(HttpServletResponse res, Object data) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(gson.toJson(data));
    }

    public void escribirError(String mesaje, HttpServletResponse reponse) throws IOException {
        ErrorRequest error = new ErrorRequest(mesaje, mesaje);
        this.escribirJson(reponse, error);
    }
}
