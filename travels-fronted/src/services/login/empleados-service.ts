import { HttpClient } from "@angular/common/http";
import { ConstantesRest } from "./restConstantes";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { ProveedorRequest } from "../../modelos/proveedores/ProveedorRequest";
import { ProveedorResponse } from "../../modelos/proveedores/ProveedorResponse";
import { DestinoRequest } from "../../modelos/destinos/destino-request";
import { DestinoResponse } from "../../modelos/destinos/destino-reponse";
import { PaqueteGeneral } from "../../modelos/paquetes/paquete-full";
import { PaqueteResponse } from "../../modelos/paquetes/paquete-response";
import { EmpleadoRequest } from "../../modelos/Empleados/empleado-request";
import { EmpleadoResponse } from "../../modelos/Empleados/empleado-response";

@Injectable({
  providedIn: 'root'
})


export class EmpleadosService {
  constantesRest = new ConstantesRest();

  constructor(private httpCliente: HttpClient) {
    
  }
  public crear(nuevo: EmpleadoRequest): Observable<void> {
    return this.httpCliente.post<void>(this.constantesRest.getApiURL() + 'api/empleados', nuevo);
  }

  public editarEmpleado(edicion: EmpleadoRequest): Observable<void> {
    return this.httpCliente.put<void>(this.constantesRest.getApiURL() + 'api/empleados', edicion);
  }


  public buscarParaEditar(nombre: string): Observable<EmpleadoRequest> {
    return this.httpCliente.get<EmpleadoRequest>(this.constantesRest.getApiURL() + 'api/empleados' + '/' + nombre);
  }


  public buscarPorRol(idRol: string): Observable<EmpleadoResponse[]> {
    return this.httpCliente.get<EmpleadoResponse []>(`${this.constantesRest.getApiURL()}api/empleados/${idRol}`);
  }


  public eliminarEmpleado(nombre: string): Observable<void> {
    return this.httpCliente.delete<void>(`${this.constantesRest.getApiURL()}api/empleados/${nombre}`);
  }



}