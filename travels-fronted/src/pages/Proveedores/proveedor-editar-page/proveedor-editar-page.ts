import { Component, OnInit, signal } from '@angular/core';
import { ProveedoresService } from '../../../services/login/proveedores-service';
import { ActivatedRoute } from '@angular/router';
import { ProveedorRequest } from '../../../modelos/proveedores/ProveedorRequest';
import { Header } from "../../../shared/header/header";
import { ProveedorForm } from "../../../components/proveedores/proveedor-form/proveedor-form";

@Component({
  selector: 'app-proveedor-editar-page',
  imports: [Header, ProveedorForm],
  templateUrl: './proveedor-editar-page.html',
  styleUrl: './proveedor-editar-page.css',
})
export class ProveedorEditarPage implements OnInit {

  nombreProveedor !: string;
  proveedor !: ProveedorRequest;

  proveedorEncontrado = signal<boolean>(false);

  constructor(private proveedoresServicio: ProveedoresService, private router: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.nombreProveedor = this.router.snapshot.params['nombre'];
    this.proveedoresServicio.buscarParaEditar(this.nombreProveedor).subscribe({
      next: (prov: ProveedorRequest) => {
        this.proveedor = prov;
        this.proveedorEncontrado.set(true);
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }
}
