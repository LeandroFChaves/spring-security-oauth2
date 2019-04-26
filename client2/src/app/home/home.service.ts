import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  private readonly API = 'api/api-1/common-1';

  constructor(
    private http: HttpClient
  ) { }

  public list() {
    return this.http.get(this.API)
    .pipe(
      tap(console.log)
    );
  }
}