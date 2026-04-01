export interface PaqueteServicio{
    id : number;
    descripcion: string;
    id_proveedor: number;
    precio: number;
    nombreProveedor ?: string;
}