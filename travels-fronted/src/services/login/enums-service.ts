import { HttpClient } from "@angular/common/http";
import { ConstantesRest } from "./restConstantes";
import { UsuarioLoginRequest } from "../../modelos/login/LoginRequest";
import { UsuarioLoginResponse } from "../../modelos/login/LoginResponse";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { Pais } from "../../modelos/enums/pais-enum";
import { Nacionalidad } from "../../modelos/enums/nacionalidad-enum";
import { MetodoPago } from "../../modelos/enums/metod-pago-enum";
import { TipoServicio } from "../../modelos/enums/tipo-servicio-enum";

@Injectable({
  providedIn: 'root'
})


export class EnumsService {
  constantesRest = new ConstantesRest();

  constructor(private httpCliente: HttpClient) {
  }

  public getPaises(): Observable<Pais[]> {
    return this.httpCliente.get<Pais[]>(this.constantesRest.getApiURL() + 'api/enums/paises');
  }

  public getNacionalidades(): Observable<Nacionalidad[]> {
    return this.httpCliente.get<Nacionalidad[]>(this.constantesRest.getApiURL() + 'api/enums/nacionalidades');
  }

  public getMetodosPago(): Observable<MetodoPago[]> {
    return this.httpCliente.get<MetodoPago[]>(this.constantesRest.getApiURL() + 'api/enums/metodos-pago');
  }

  public getTiposServicio(): Observable<TipoServicio[]> {
    return this.httpCliente.get<TipoServicio[]>(this.constantesRest.getApiURL() + 'api/enums/tipos-servicio');
  }


}