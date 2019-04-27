import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Cookie } from 'ng2-cookies';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AutenticacaoService {
  
  private oauthTokenUrl: string = 'http://localhost:8081/oauth-server/oauth/token';

  constructor(
    private _http: HttpClient,
    private _router: Router) { }

  public login(loginData){

    let params = new URLSearchParams();
    params.append('username', loginData.username);
    params.append('password', loginData.password);
    params.append('grant_type', 'password');
    params.append('client_id', 'client_app_2');
    
    const headers = new HttpHeaders()
      .append('Content-Type',  'application/x-www-form-urlencoded; charset=utf-8')
      .append('Authorization', 'Basic ' + btoa("client_app_2" + ':' + "client_app_2"));
     
    this._http.post(this.oauthTokenUrl, params.toString(), { headers, withCredentials: true })
      .subscribe(
        data => this.saveToken(data),
        err => alert('Invalid Credentials'));
  }
 
  public saveToken(token) {
    var expireDate = new Date().getTime() + (1000 * token.expires_in);
    Cookie.set("access_token", token.access_token, expireDate);

    this._router.navigate(['/home']);
  }

  public checkCredentials(){
    return Cookie.check('access_token');
  } 
 
}