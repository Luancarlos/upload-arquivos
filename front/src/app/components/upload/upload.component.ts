import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FileUploader } from 'ng2-file-upload';
import { ManipulaXmlService } from '../../service/manipula-xml/manipula-xml.service';
import { UploadService } from '../../service/upload/upload.service';
import { LoaderComponent } from '../loader/loader.component';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.sass']
})
export class UploadComponent implements OnInit {

  uploader: FileUploader = new FileUploader({
    isHTML5: true,
    allowedMimeType: ['text/xml']
  });

  hasBaseDropZoneOver: boolean;

  constructor(
    private manipulaXmlService: ManipulaXmlService,
    private uploadService: UploadService,
    public dialog: MatDialog,
    private snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  fileOverBase(e:any):void {
    this.hasBaseDropZoneOver = e;
  }

  cancelar() {
   this.uploader.clearQueue();
  }

  async enviar() {
    let erro = false;
    this.openDialog();
    const files = await this.manipulaXmlService.removeDadosConfidenciais(this.uploader.queue);

    for (let file of files) {
      try {
        await this.handlerUpload(file);
      } catch(erro) {
        erro = true;
        console.log(erro);
        this.closeDialog();
        this.openSnackBar('Ocorreu um erro ao enviar o arquivo ' + file.name);
      }
    }

    if (!erro) {
      this.closeDialog();
      this.uploader.clearQueue();
      this.openSnackBar('Arquivo(s) enviado(s) com sucesso');
    }
  }

  handlerUpload(file: File): Promise<any> {
    return new Promise((resolve, reject) => {
      this.uploadService.upload(file).subscribe({
        next: (res) => resolve(res),
        error: (error) => reject(error)
      })
    })
  }

  openDialog(): void {
    this.dialog.open(LoaderComponent, {
      disableClose: true
    });
  }

  closeDialog() {
    this.dialog.closeAll();
  }

  openSnackBar(msg) {
    this.snackBar.open(msg, '', {
      horizontalPosition: 'right',
      verticalPosition: 'top',
      duration: 7000
    });
  }



}
