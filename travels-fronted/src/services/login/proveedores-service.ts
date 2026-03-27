import { HttpClient } from "@angular/common/http";
import { ConstantesRest } from "./restConstantes";
import { UsuarioLoginRequest } from "../../modelos/login/LoginRequest";
import { UsuarioLoginResponse } from "../../modelos/login/LoginResponse";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { ProveedorRequest } from "../../modelos/proveedores/ProveedorRequest";
import { ProveedorResponse } from "../../modelos/proveedores/ProveedorResponse";

@Injectable({
  providedIn: 'root'
})


export class ProveedoresService {
  constantesRest = new ConstantesRest();

  constructor(private httpCliente: HttpClient) {

  }
  public crear(proveedor: ProveedorRequest): Observable<void> {
    return this.httpCliente.post<void>(this.constantesRest.getApiURL() + 'proveedores', proveedor);
  }

  public obtenerTodos(): Observable<ProveedorResponse[]> {
    return this.httpCliente.get<ProveedorResponse[]>(this.constantesRest.getApiURL() + 'proveedores');
  }


  public buscarParaEditar(nombre: string): Observable<ProveedorRequest> {
    return this.httpCliente.get<ProveedorRequest>(this.constantesRest.getApiURL() + 'proveedores' + '/' + nombre);
  }


  public buscarParaMostrar(nombre: string): Observable<ProveedorResponse> {
    return this.httpCliente.get<ProveedorResponse>( `${this.constantesRest.getApiURL()}proveedores/${nombre}`);
  }

  public editarProvedor( proveedor: ProveedorRequest): Observable<void>{
    return this.httpCliente.put<void>(this.constantesRest.getApiURL()+'proveedores',proveedor);
  }



}