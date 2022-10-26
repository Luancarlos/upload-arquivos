import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UploadComponent } from './upload/upload.component';
import { MatCardModule } from '@angular/material/card';
import { FileUploadModule } from 'ng2-file-upload';
import { MatIconModule } from '@angular/material/icon';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { LoaderComponent } from './loader/loader.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule } from '@angular/material/snack-bar';

@NgModule({
  declarations: [UploadComponent, LoaderComponent],
  imports: [
    CommonModule,
    MatCardModule,
    MatIconModule,
    MatGridListModule,
    MatButtonModule,
    MatProgressBarModule,
    FileUploadModule,
    MatDialogModule,
    MatProgressSpinnerModule,
    MatSnackBarModule
  ],
  exports: [
    UploadComponent
  ]
})
export class ComponentsModule { }
