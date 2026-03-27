import { Component } from '@angular/core';
import { Header } from "../../../shared/header/header";
import { ProveedorForm } from "../../../components/proveedores/proveedor-form/proveedor-form";

@Component({
  selector: 'app-proveedor-form-page',
  imports: [Header, ProveedorForm],
  templateUrl: './proveedor-form-page.html',
  styleUrl: './proveedor-form-page.css',
})
export class ProveedorFormPage {
  
}
