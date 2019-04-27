import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AutenticacaoService } from '../autenticacao.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public isLoggedIn = false;
  public loginData = { username: "", password: "" };

  constructor(
    private _service : AutenticacaoService,
    private _router : Router
  ) {

  }

  ngOnInit() {
    this.isLoggedIn = this._service.checkCredentials();

    if (this.isLoggedIn) {
      this._router.navigate(['/home']);
    }
  }

  public login() {
    this._service.login(this.loginData);
  }

}
