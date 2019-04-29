import { Injectable, EventEmitter } from '@angular/core';
import { Cookie } from 'ng2-cookies';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AutenticacaoService {
  
  public clientId = 'client_app_1';
  public redirectUri = 'http://localhost:4200/';
  public mostrarMenuEmitter = new EventEmitter<boolean>();

 constructor (
   private _http: HttpClient,
   private router : Router) { }

 public retrieveToken(code){
    let params = new URLSearchParams();   
    params.append('grant_type','authorization_code');
    params.append('client_id', this.clientId);
    params.append('redirect_uri', this.redirectUri);
    params.append('code', code);

    const headers = new HttpHeaders()
    .append('Content-Type',  'application/x-www-form-urlencoded; charset=utf-8')
    .append('Authorization', 'Basic ' + btoa("client_app_1" + ':' + "client_app_1"));

      this._http.post('api/oauth/token', params.toString(), { headers: headers })
      .subscribe(
        data => this.saveToken(data),
        err => alert('Invalid Credentials')
      ); 
  }

  public saveToken(token){
    var expireDate = new Date().getTime() + (1000 * token.expires_in);
    Cookie.set("access_token", token.access_token, expireDate);
    this.mostrarMenuEmitter.emit(true);

    this.router.navigate(['/home']);
  }

  public getResource(resourceUrl) : Observable<any>{
    var headers = new HttpHeaders({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8', 'Authorization': 'Bearer '+Cookie.get('access_token')});
    return this._http.get(resourceUrl, { headers: headers }).pipe(
                    catchError((error:any) => Observable.throw(error.json().error || 'Server error')));
  }

  public checkCredentials() {
    let tokenValido : boolean = Cookie.check('access_token');

    if (tokenValido) {
      this.mostrarMenuEmitter.emit(true);
      this.router.navigate(['/home']);
    }

    return tokenValido;
  }

  public logout() {
    Cookie.delete('access_token');
    window.location.reload();
  }
 
}