import { PaqueteServicio } from "../proveedor-servicio/paquete-servicio";
import { PaqueteRequest } from "./paquete-request";

export interface PaqueteGeneral {
  paquete : PaqueteRequest;
  servicios: PaqueteServicio[];
  nuevosServicios ?: PaqueteServicio[];
}