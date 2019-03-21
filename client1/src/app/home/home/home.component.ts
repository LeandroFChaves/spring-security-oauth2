import { Component, OnInit } from '@angular/core';
import { empty } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { HomeService } from '../home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.sass']
})
export class HomeComponent implements OnInit {

  constructor(
    private homeService: HomeService
  ) { }

  ngOnInit() {
  }

  public testeProxyApi() {
    this.homeService.list()
     .pipe(
       catchError(error => empty())
     )
    .subscribe(
       dados => {
         console.log(dados);
       }
       ,error => console.error(error),
        () => console.log('Obserservable completo!')
    );
  }

}