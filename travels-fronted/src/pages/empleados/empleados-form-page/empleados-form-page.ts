import { Component } from '@angular/core';
import { Header } from "../../../shared/header/header";
import { EmpleadoForm } from "../../../components/empleados/empleado-form/empleado-form";

@Component({
  selector: 'app-empleados-form-page',
  imports: [Header, EmpleadoForm],
  templateUrl: './empleados-form-page.html',
  styleUrl: './empleados-form-page.css',
})
export class EmpleadosFormPage {

  
}
