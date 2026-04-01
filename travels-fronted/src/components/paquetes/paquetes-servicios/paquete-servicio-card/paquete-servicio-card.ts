import { Component, EventEmitter, Input, input, Output, signal } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { required } from '@angular/forms/signals';
import { PaqueteServicio } from '../../../../modelos/proveedor-servicio/paquete-servicio';

@Component({
  selector: 'app-paquete-servicio-card',
  imports: [ReactiveFormsModule],
  templateUrl: './paquete-servicio-card.html',
  styleUrl: './paquete-servicio-card.css',
})
export class PaqueteServicioCard {

  @Input({required : true})
  paqueteServicio !: PaqueteServicio;

  @Output()
  eliminarSeleccionado = new EventEmitter<PaqueteServicio>();


  @Output()
  editarSeleccinado = new EventEmitter<PaqueteServicio>();

  eliminar(){
    this.eliminarSeleccionado.emit(this.paqueteServicio);
  }

  editar(){
     this.editarSeleccinado.emit(this.paqueteServicio);
  }

 
}
