import { Component, OnInit, signal } from '@angular/core';
import { Header } from "../../../shared/header/header";
import { EnumsService } from '../../../services/login/enums-service';
import { Rol } from '../../../modelos/enums/rol-enum';
import { RouterLink } from '@angular/router';
import { EmpleadoResponse } from '../../../modelos/Empleados/empleado-response';
import { EmpleadosService } from '../../../services/login/empleados-service';
import { ModalGenerico } from "../../../shared/modal/modal-generico/modal-generico";
import { EmpleadoCard } from "../../../components/empleados/empleado-card/empleado-card";
import { ErrorBackend } from '../../../modelos/ErrorBackend';

@Component({
  selector: 'app-empleados-page',
  imports: [Header, RouterLink, ModalGenerico, EmpleadoCard],
  templateUrl: './empleados-page.html',
  styleUrl: './empleados-page.css',
})
export class EmpleadosPage implements OnInit {


  mensajeEliminacio !: string;
  mensajeError !: string;

  roles = signal<Rol[]>([]);

  empleados = signal<EmpleadoResponse[]>([]);
  buscandoPorRol = signal<boolean>(false);
  buscandoUno = signal<boolean>(false);

  hayError = signal<boolean>(false);

  empleadoSeleccionado !: EmpleadoResponse;
  empleadoEncontrado !: EmpleadoResponse;


  constructor(private enumsService: EnumsService, private empleadosServicio: EmpleadosService) {

  }

  ngOnInit(): void {
    this.cargarRoles();
    this.buscarPorRol('1');
  }


  buscarPorRol(idRol: string) {
    this.buscandoPorRol.set(true);
    this.empleadosServicio.buscarPorRol(idRol).subscribe({
      next: (empleados: EmpleadoResponse[]) => {
        this.empleados.set(empleados);
      },
      error: (httpError: any) => {
      }
    });
  }

  cargarRoles() {
    this.enumsService.getRoles().subscribe({
      next: (roles: Rol[]) => {
        this.roles.set(roles);
      },
      error: (httpError: any) => {
        this.registrarError(httpError);
      }
    });
  }


  private registrarError(httpError: any) {
      this.hayError.set(true);
      const errorData: ErrorBackend = httpError.error;
      this.mensajeError = errorData.detalles;
    }


    guardarDestinoSeleccionado(empleado: EmpleadoResponse): void {
      console.log("guarando el destino");
      this.empleadoSeleccionado = empleado;
      this.mensajeEliminacio = " Se eliminará a " + empleado.nombre + "  desea continuar?";
    }
  
  
    eliminarRegistro(busquedaActual: string ) {

      this.empleadosServicio.eliminarEmpleado(this.empleadoSeleccionado.id.toString()).subscribe({
        next: () => {
          this.buscarPorRol(busquedaActual);
        },
        error: (httpError: any) => {
          this.registrarError(httpError);
        }
      })
    }

}
