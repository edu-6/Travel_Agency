import { Routes } from '@angular/router';
import { LoginForm } from '../components/login/login-form/login-form';
import { Home } from '../pages/home/home';

export const routes: Routes = [

    {path:"", component: LoginForm},
    {path:"home", component: Home},
    
];
