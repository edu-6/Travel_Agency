import { Component, signal } from '@angular/core';
import { EmpleadoRequest } from '../../../modelos/Empleados/empleado-request';
import { EmpleadosService } from '../../../services/login/empleados-service';
import { ActivatedRoute } from '@angular/router';
import { Header } from "../../../shared/header/header";
import { EmpleadoForm } from "../../../components/empleados/empleado-form/empleado-form";

@Component({
  selector: 'app-empleados-editar-page',
  imports: [Header, EmpleadoForm],
  templateUrl: './empleados-editar-page.html',
  styleUrl: './empleados-editar-page.css',
})
export class EmpleadosEditarPage {

  nombreEmpleado !: string;
  empleado !: EmpleadoRequest;

  empeadoEncontrado = signal<boolean>(false);


  constructor(private empleadosService: EmpleadosService, private router: ActivatedRoute){
  }

  ngOnInit(): void {
    this.nombreEmpleado = this.router.snapshot.params['nombre'];
    this.empleadosService.buscarParaEditar(this.nombreEmpleado).subscribe({
      next: (empleado: EmpleadoRequest ) => {
        this.empleado = empleado;
        this.empeadoEncontrado.set(true);
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }



}
