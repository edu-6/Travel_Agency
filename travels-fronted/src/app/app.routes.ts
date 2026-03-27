import { Routes } from '@angular/router';
import { LoginForm } from '../components/login/login-form/login-form';
import { Home } from '../pages/home/home';
import { ProveedoresPage } from '../pages/Proveedores/proveedores-page/proveedores-page';
import { ProveedorFormPage } from '../pages/Proveedores/proveedor-form-page/proveedor-form-page';
import { ProveedorEditarPage } from '../pages/Proveedores/proveedor-editar-page/proveedor-editar-page';

export const routes: Routes = [

    {path:"", component: LoginForm},
    {path:"home", component: Home},
    {path: "proveedores", component: ProveedoresPage},
    {path: "proveedores/form-page", component:  ProveedorFormPage},
    {path: "proveedores/editar-page/:nombre", component:  ProveedorEditarPage},

    
];
