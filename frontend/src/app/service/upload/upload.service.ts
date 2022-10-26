import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  private url = environment.api;

  constructor(private http: HttpClient) { }

  upload(file: File) {
    const formData = new FormData();
    formData.append('file', file);

    const headers = new HttpHeaders();
    headers.set('Accept', "multipart/form-data");

    return this.http.post(`${this.url}/upload`, formData, {headers})
  }
}
