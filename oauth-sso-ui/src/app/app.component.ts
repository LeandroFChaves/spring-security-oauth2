import { Component } from '@angular/core';

import { AutenticacaoService } from './autenticacao/autenticacao.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  public title = 'oauth-sso-ui';
  public mostrarMenu : boolean = false;

  constructor(private _authService : AutenticacaoService) { }

  ngOnInit() {
    this._authService.mostrarMenuEmitter.subscribe(
      mostrar => {
        setTimeout(() => this.mostrarMenu = mostrar, 0)
      }
    );
  }

  public logout(){
    this._authService.logout();
  }
}
