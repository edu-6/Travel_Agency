import { Component, OnInit, signal } from '@angular/core';
import { Header } from "../../../shared/header/header";
import { PaqueteServicio } from '../../../modelos/proveedor-servicio/paquete-servicio';
import { PaquetesService } from '../../../services/login/paquetes-service';
import { PaqueteGeneral } from '../../../modelos/paquetes/paquete-full';
import { ActivatedRoute, Router } from '@angular/router';
import { ErrorBackend } from '../../../modelos/ErrorBackend';
import { PaqueteForm } from "../../../components/paquetes/paquete-form/paquete-form";

@Component({
  selector: 'app-paquetes-editar-page',
  imports: [Header, PaqueteForm],
  templateUrl: './paquetes-editar-page.html',
  styleUrl: './paquetes-editar-page.css',
})
export class PaquetesEditarPage implements OnInit {

  hayError = signal<boolean>(false);
  paqueteEncontrado = signal<boolean>(false);

  nombrePaquete !: string;
  mensajeError !: string;

  paqueteFull = signal<PaqueteGeneral>({} as PaqueteGeneral);

  constructor(private paquetesService: PaquetesService, private router: ActivatedRoute) {

  }


  ngOnInit(): void {
    this.buscarPaquete();
  }

  registrarError(httpError: any) {
    this.hayError.set(true);
    const errorData: ErrorBackend = httpError.error;
    this.mensajeError = errorData.detalles;
  }



  buscarPaquete() {
    this.nombrePaquete = this.router.snapshot.params['nombre'];
    this.paquetesService.buscarParaEditar(this.nombrePaquete).subscribe({
      next: (paqueteFull: PaqueteGeneral) => {
        this.paqueteFull.set(paqueteFull);
        this.paqueteEncontrado.set(true);
      },
      error: (error: any) => {
        this.registrarError(error);
      }
    });
  }

}
