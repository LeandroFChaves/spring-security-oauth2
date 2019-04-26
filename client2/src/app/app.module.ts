import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeModule } from './home/home.module';
import { JwtHttpInterceptor } from './autenticacao/jwt-http.interceptor';
import { AutenticacaoModule } from './autenticacao/autenticacao.module';
import { AutenticacaoService } from './autenticacao/autenticacao.service';

@NgModule({
  declarations: [
    AppComponent
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,

    AutenticacaoModule,
    HomeModule
  ],

  providers: [
    AutenticacaoService,

    { provide: HTTP_INTERCEPTORS, useClass: JwtHttpInterceptor, multi: true },
  ],

  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }