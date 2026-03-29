import { Component, EventEmitter, Input, Output } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ProveedorResponse } from '../../../modelos/proveedores/ProveedorResponse';

@Component({
  selector: 'app-proveedor-card',
  imports: [RouterLink],
  templateUrl: './proveedor-card.html',
  styleUrl: './proveedor-card.css',
})
export class ProveedorCard {

  @Input({required: true})
  proveedor !: ProveedorResponse;




}
