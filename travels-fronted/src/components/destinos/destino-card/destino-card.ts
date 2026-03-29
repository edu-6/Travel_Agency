import { Component, EventEmitter, Input, Output } from '@angular/core';
import { DestinoResponse } from '../../../modelos/destinos/destino-reponse';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-destino-card',
  imports: [RouterLink],
  templateUrl: './destino-card.html',
  styleUrl: './destino-card.css',
})
export class DestinoCard {


  @Input()
  destino !: DestinoResponse;

    @Output()
  destinoFueSeleccionado = new EventEmitter<DestinoResponse>();

  public eliminarAccion(){
    this.destinoFueSeleccionado.emit(this.destino);
    console.log("fnciona el boton");
  }

}
