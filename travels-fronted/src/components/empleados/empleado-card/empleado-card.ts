import { Component, EventEmitter, Input, Output } from '@angular/core';
import { EmpleadoResponse } from '../../../modelos/Empleados/empleado-response';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-empleado-card',
  imports: [RouterLink],
  templateUrl: './empleado-card.html',
  styleUrl: './empleado-card.css',
})
export class EmpleadoCard {
  
  @Input()
  empleado !: EmpleadoResponse;

    @Output()
    empleadoFueSeleccionado = new EventEmitter<EmpleadoResponse>();

  public eliminarAccion(){
    this.empleadoFueSeleccionado.emit(this.empleado);
  }


  public esElMismo(): boolean{
    return localStorage.getItem('id') === this.empleado.id.toString();
  }

}
