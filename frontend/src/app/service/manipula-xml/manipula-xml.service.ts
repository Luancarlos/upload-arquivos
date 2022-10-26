import { Injectable } from '@angular/core';
import { FileItem } from 'ng2-file-upload';

@Injectable({
  providedIn: 'root'
})
export class ManipulaXmlService {

  constructor() { }

  async removeDadosConfidenciais(files: FileItem[]): Promise<File[]> {
    const promisses: Promise<File>[] = [];

    files.forEach(item => {
      promisses.push(this.convert(item._file));
    })

    return await Promise.all(promisses);
  }

  private convert(file: File): Promise<File> {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsText(file);
      reader.onload = () => {
          const xmlString = this.cleanValuePrecoMedio(reader.result.toString());
          const xmlFile = this.convertStringToFile(xmlString, file.name, file.type);
          resolve(xmlFile)
      };
    })
  }

  private convertStringToFile(content: string, name: string, type: string): File {
    const blob = new Blob([content], { type});
    return new File([blob], name, { type });
  }

  private cleanValuePrecoMedio(content: string): string {
    const parser = new DOMParser();
    const xmlDoc = parser.parseFromString(content, "text/xml");

    const precosMedio = xmlDoc.getElementsByTagName("precoMedio")

    for (let i = 0; i < precosMedio.length; i++) {
      const precoMedio = precosMedio[i];
      precoMedio.childNodes.forEach(item => {
        item.textContent = "";
      })
    }

    const serializer = new XMLSerializer();
    return serializer.serializeToString(xmlDoc);
  }
}
