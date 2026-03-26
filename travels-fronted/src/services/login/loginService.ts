import { HttpClient } from "@angular/common/http";
import { ConstantesRest } from "./restConstantes";
import { UsuarioLoginRequest } from "../../modelos/login/LoginRequest";
import { UsuarioLoginResponse } from "../../modelos/login/LoginResponse";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root'
})


export class LoginServicio{
    constantesRest =  new ConstantesRest();

    constructor(private httpCliente: HttpClient){

    }
    public loguearUsuario(usuarioLogin: UsuarioLoginRequest):  Observable<UsuarioLoginResponse>{
      return this.httpCliente.post<UsuarioLoginResponse>(this.constantesRest.getApiURL() + 'login',usuarioLogin);
    }

}