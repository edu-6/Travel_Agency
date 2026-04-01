import { Component } from '@angular/core';
import { Header } from "../../../shared/header/header";
import { PaqueteForm } from "../../../components/paquetes/paquete-form/paquete-form";

@Component({
  selector: 'app-paquetes-form-page',
  imports: [Header, PaqueteForm],
  templateUrl: './paquetes-form-page.html',
  styleUrl: './paquetes-form-page.css',
})
export class PaquetesFormPage {
  
}
