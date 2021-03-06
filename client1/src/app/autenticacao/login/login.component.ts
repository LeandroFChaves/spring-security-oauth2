import { Component, OnInit } from '@angular/core';

import { AutenticacaoService } from '../autenticacao.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public isLoggedIn = false;

  constructor(private _service : AutenticacaoService) {

  }

  ngOnInit() {
    this.isLoggedIn = this._service.checkCredentials();    
    let i = window.location.href.indexOf('code');

    if(!this.isLoggedIn && i != -1){
        this._service.retrieveToken(window.location.href.substring(i + 5));
    }
  }

  public loginAuthorizationCode() {
    window.location.href = 'http://localhost:8081/oauth-server/oauth/authorize?response_type=code&client_id=' + this._service.clientId + '&redirect_uri='+ this._service.redirectUri;
  }

  public logout() {
    this._service.logout();
  }

}
