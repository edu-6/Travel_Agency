import { CanActivateFn, Router } from "@angular/router";
import { AutenticacionServicio } from "../services/login/autenficacion-service";
import { inject } from "@angular/core";


export const authGuard: CanActivateFn = () => {
  const authService = inject(AutenticacionServicio);
  const router = inject(Router);

  if (authService.isAuthenticated()) {
    return true;
  }

  // No está autenticado: redirigir al login
  return router.createUrlTree(['/login']);
};