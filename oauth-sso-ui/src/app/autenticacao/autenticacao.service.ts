import { Injectable } from '@angular/core';
import { Cookie } from 'ng2-cookies';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AutenticacaoService {
  
  public clientId = 'client_app_1';
  public redirectUri = 'http://localhost:4200/';

 constructor(
   private _http: HttpClient){}

 retrieveToken(code){
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

 saveToken(token){
   var expireDate = new Date().getTime() + (1000 * token.expires_in);
   Cookie.set("access_token", token.access_token, expireDate);
   console.log('Obtained Access token');
   window.location.href = 'http://localhost:4200';
 }

 getResource(resourceUrl) : Observable<any>{
   var headers = new HttpHeaders({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8', 'Authorization': 'Bearer '+Cookie.get('access_token')});
   return this._http.get(resourceUrl, { headers: headers }).pipe(
                  catchError((error:any) => Observable.throw(error.json().error || 'Server error')));
 }

 checkCredentials(){
   return Cookie.check('access_token');
 } 

 logout() {
   Cookie.delete('access_token');
   window.location.reload();
 }
 
}