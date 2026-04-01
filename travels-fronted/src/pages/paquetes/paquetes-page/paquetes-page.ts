import { Component, OnInit, signal } from '@angular/core';
import { Header } from "../../../shared/header/header";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-paquetes-page',
  imports: [Header, ReactiveFormsModule, RouterLink],
  templateUrl: './paquetes-page.html',
  styleUrl: './paquetes-page.css',
})
export class PaquetesPage implements OnInit{



  public barraBusqueda !: FormGroup;
   mensajeError !: string;


   hayError: boolean = false;
   buscandoUno = signal <boolean>(false);

   //public proveedorBuscado !: ProveedorResponse;

   constructor(private formBuilder: FormBuilder){

   }
   

  ngOnInit(): void {
    this.barraBusqueda = this.formBuilder.group(
      {
        busqueda : ["", [Validators.required]]
      }
    );
  }


  buscarPaquete(){

  }

  buscarTodos(){

  }
}
