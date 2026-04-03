import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { AutenticacionServicio } from '../../services/login/autenficacion-service';

@Component({
  selector: 'app-header',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header {
  constructor(private router: Router, private autenticacionSerivice: AutenticacionServicio){

  }
  
  public operador: boolean = true;
  public administrativo: boolean = true;
  public superAdmin: boolean = false;


  public logout(): void {
    this.autenticacionSerivice.logout();
  }


}



