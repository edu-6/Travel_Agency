import { Routes } from '@angular/router';
import { LoginForm } from '../components/login/login-form/login-form';
import { Home } from '../pages/home/home';
import { ProveedoresPage } from '../pages/Proveedores/proveedores-page/proveedores-page';
import { ProveedorFormPage } from '../pages/Proveedores/proveedor-form-page/proveedor-form-page';
import { ProveedorEditarPage } from '../pages/Proveedores/proveedor-editar-page/proveedor-editar-page';
import { DestinosPage } from '../pages/destinos/destinos-page/destinos-page';
import { DestinosFormPage } from '../pages/destinos/destinos-form-page/destinos-form-page';
import { DestinosEditarPage } from '../pages/destinos/destinos-editar-page/destinos-editar-page';

export const routes: Routes = [

    {path:"", component: LoginForm},
    {path:"home", component: Home},
    {path: "proveedores", component: ProveedoresPage},
    {path: "proveedores/form-page", component:  ProveedorFormPage},
    {path: "proveedores/editar-page/:nombre", component:  ProveedorEditarPage},
    
    {path: "destinos", component:  DestinosPage},
    {path: "destinos/form-page", component:  DestinosFormPage},
    {path: "destinos/editar-page/:nombre", component:  DestinosEditarPage},
];
