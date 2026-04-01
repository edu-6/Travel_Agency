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

@Injectable({
  providedIn: 'root'
})


export class PaquetesService {
  constantesRest = new ConstantesRest();

  constructor(private httpCliente: HttpClient) {
    
  }
  public crear(nuevo: PaqueteGeneral): Observable<void> {
    return this.httpCliente.post<void>(this.constantesRest.getApiURL() + 'paquetes', nuevo);
  }

  public editarPaquete(edicion: PaqueteGeneral): Observable<void> {
    return this.httpCliente.put<void>(this.constantesRest.getApiURL() + 'paquetes', edicion);
  }


  public buscarParaEditar(nombre: string): Observable<PaqueteGeneral> {
    return this.httpCliente.get<PaqueteGeneral>(this.constantesRest.getApiURL() + 'paquetes' + '/' + nombre);
  }


  public buscarPorDestino(idDestino: string): Observable<PaqueteResponse[]> {
    return this.httpCliente.get<PaqueteResponse []>(`${this.constantesRest.getApiURL()}paquetes/${idDestino}`);
  }


  public eliminarServicioEnPaquete(idServicio: string): Observable<void> {
    return this.httpCliente.delete<void>(`${this.constantesRest.getApiURL()}paquetes/${idServicio}`);
  }



}