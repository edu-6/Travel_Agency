import { Component, OnInit, signal } from '@angular/core';
import { Header } from "../../../shared/header/header";
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { ProveedorResponse } from '../../../modelos/proveedores/ProveedorResponse';
import { ProveedoresService } from '../../../services/login/proveedores-service';
import { ErrorBackend } from '../../../modelos/ErrorBackend';
import { ProveedorCard } from "../../../components/proveedores/proveedor-card/proveedor-card";

@Component({
  selector: 'app-proveedores-page',
  imports: [Header, FormsModule, ReactiveFormsModule, RouterLink, ProveedorCard],
  templateUrl: './proveedores-page.html',
  styleUrl: './proveedores-page.css',
})
export class ProveedoresPage  implements OnInit{
   public barraBusqueda !: FormGroup;

   mensajeError !: string;

   // boleanos
   hayError: boolean = false;
   buscandoTodos = signal<boolean>(false);
   buscandoUno = signal <boolean>(false);

   public proveedorBuscado !: ProveedorResponse;

   constructor(private formBuilder: FormBuilder, private proveedoresService: ProveedoresService){

   }
   
   proveedores !: ProveedorResponse [];

  ngOnInit(): void {
    this.buscarTodos();

    this.barraBusqueda = this.formBuilder.group(
      {
        busqueda : ["", [Validators.required]]
      }
    );
  }

  public buscarTodos(){
    this.proveedoresService.obtenerTodos().subscribe({
      next: (todos: ProveedorResponse []) =>{
        this.proveedores = todos;
        this.buscandoUno.set(false);
        this.buscandoTodos.set(true);
      },
      error: (errorHtttp: any)=>{
        this.hayError = true;
        const errorData: ErrorBackend = errorHtttp.error;
        this.mensajeError = errorData.detalles;
      }

    });
  }

  public buscarProveedor(){
    if(!this.barraBusqueda.valid) return;
    this.buscandoTodos.set(false);
    this.proveedoresService.buscarParaMostrar(this.barraBusqueda.get("busqueda")?.value).subscribe({
      next: (prov: ProveedorResponse ) =>{
        this.proveedorBuscado = prov;
        this.buscandoUno.set(true);
      },
      error: (errorHtttp: any)=>{
        this.hayError = true;
        const errorData: ErrorBackend = errorHtttp.error;
        this.mensajeError = errorData.detalles;
      }
    });
  }

}
