import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { UsuarioLoginRequest } from '../../../modelos/login/LoginRequest';
import { Router } from '@angular/router';
import { RouterLink } from '@angular/router';
import { UsuarioLoginResponse } from '../../../modelos/login/LoginResponse';
import { LoginServicio } from '../../../services/login/loginService';
import { ErrorBackend } from '../../../modelos/ErrorBackend';
@Component({
  selector: 'app-login-form',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './login-form.html',
  styleUrl: './login-form.css',
})
export class LoginForm implements OnInit {
  formulario !: FormGroup;


  empleadoLoginRequest !: UsuarioLoginRequest;

  btnPresionado: boolean = false;
  loginFallido: boolean = false;

  constructor(private formBuilder: FormBuilder, private LoginServicio: LoginServicio, private router: Router) {

  }

  ngOnInit(): void {
    this.formulario = this.formBuilder.group({
      nombre: ['', [Validators.required, Validators.maxLength(100)]],
      contraseña: ['', [Validators.required, Validators.maxLength(100)]]
    });
  }

  private resetearFormulario(): void {
    this.formulario.reset();
  }


  public enviar(): void {
    this.btnPresionado = true;

    if (this.formulario.valid) {
      this.empleadoLoginRequest = this.formulario.value as UsuarioLoginRequest;
      this.LoginServicio.loguearUsuario(this.empleadoLoginRequest).subscribe({
        next: (empleado: UsuarioLoginResponse) => {
          localStorage.setItem('nombre', empleado.nombre);
          localStorage.setItem('rol', empleado.rol);
          localStorage.setItem('id', empleado.id.toString());
          console.log("login correcto!");
          this.router.navigate(["/inicio"]);

        },
        error: (error: any) => {
          if (error.error) {
            console.log('Eror encontrado:', error.error);
          }
          this.resetearFormulario();
          this.loginFallido = true;
        }
      });
    }

  }


  public enviarFormulario(): void {
    this.btnPresionado = true;

    if (this.formulario.valid) {
      this.empleadoLoginRequest = this.formulario.value as UsuarioLoginRequest;
      this.LoginServicio.loguearUsuario(this.empleadoLoginRequest).subscribe({
        next: (empleado: UsuarioLoginResponse) => {
          localStorage.setItem('nombre', empleado.nombre);
          localStorage.setItem('rol', empleado.rol);
          localStorage.setItem('id', empleado.id.toString());
          console.log("login correcto!");
          this.router.navigate(["/home"]);

        },
        error: (errorHttp: any) => {
          const errorData: ErrorBackend = errorHttp.error;
          alert(errorData.detalles);
          console.error('Tipo de error:', errorData.motivo);
          console.error('Mensaje del servidor:', errorData.detalles);
          this.loginFallido = true;
          this.resetearFormulario();
        }
      });
    }
  }

}
