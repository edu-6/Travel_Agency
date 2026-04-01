import { HttpInterceptorFn } from "@angular/common/http";
import { AutenticacionServicio} from "../services/login/autenficacion-service";
import { inject } from "@angular/core";

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AutenticacionServicio);
  const token = authService.getToken();

  if (!token) {
    return next(req);
  }

  const authReq = req.clone({
    setHeaders: { 
      Authorization: `Bearer ${token}`,
    },
  });

  return next(authReq);
};