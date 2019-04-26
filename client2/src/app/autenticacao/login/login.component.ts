import { Component, OnInit } from '@angular/core';

import { AutenticacaoService } from '../autenticacao.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public isLoggedIn = false;
  public loginData = { username: "", password: "" };

  constructor(private _service : AutenticacaoService) {

  }

  ngOnInit() {

  }

  public login() {
    this._service.login(this.loginData);
  }

}
