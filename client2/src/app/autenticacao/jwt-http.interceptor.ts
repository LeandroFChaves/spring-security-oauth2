import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class JwtHttpInterceptor implements HttpInterceptor {
  constructor() {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiIFVTRVJfQURNSU5fUkVTT1VSQ0UiLCJVU0VSX0NMSUVOVF9SRVNPVVJDRSJdLCJ1c2VyX25hbWUiOiJ1c2VyIiwic2NvcGUiOlsicm9sZV9hZG1pbiIsInJvbGVfdXNlciJdLCJyb2xlcyI6W3siaWRSb2xlIjoyLCJuYW1lIjoicm9sZV91c2VyIiwicGVybWlzc2lvbnMiOlt7ImlkUGVybWlzc2lvbiI6MywibmFtZSI6ImNhbl9yZWFkX3VzZXIifV19XSwiZXhwIjoxNTU0OTMzMDIzLCJhdXRob3JpdGllcyI6WyJjYW5fcmVhZF91c2VyIiwicm9sZV91c2VyIl0sImp0aSI6IjE4MThkZGY0LTgyM2EtNDQ3Mi1hNzc3LTBhMTliNjNlZTJiZiIsImVtYWlsIjoidXNlckBwYWRyYW8uY29tLmJyIiwiY2xpZW50X2lkIjoiY2xpZW50X2FwcF8xIiwidXNlcm5hbWUiOiJ1c2VyIn0.qx07rP8H0zi_V77tIT_Jsv8a7jJEnwQGKnPwxIz97KA'
      
      if (token && request.url.indexOf('/oauth/token') === -1) {
        request = request.clone({
          setHeaders: {
            Accept: `application/json`,
            'Content-Type': `application/json`,
            Authorization: `Bearer ${token}`
          }
        });

      } 
      return next.handle(request);
  }
}