import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginComponent } from './login/login.component';
import { HomeComponent } from '../home/home/home.component';

@NgModule({
  declarations: [
    LoginComponent,
    HomeComponent
  ],

  imports: [
    CommonModule
  ]
})
export class AutenticacaoModule { }
