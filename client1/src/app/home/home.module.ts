import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeService } from './home.service';
import { HomeComponent } from './home/home.component';

@NgModule({
  declarations: [
    HomeComponent
  ],

  imports: [
    CommonModule
  ],

  providers: [
    HomeService
  ]
})
export class HomeModule { }
