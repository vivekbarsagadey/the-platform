import { Component } from '@angular/core';
import {Location} from '@angular/common';


@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.scss'],
})
export class UploadFileComponent  {
  selectedFile: File = null;
  constructor(private location: Location) { }
  selectFile(event) {
    this.selectedFile = <File>event.target.files[0];
  }

  upload() {
    const fiormData = new FormData();
    fiormData.append('file', this.selectedFile);
    fetch('http://192.168.1.126:5000/api/diabetes/dataset/upload', {
      method: 'post',
      headers: {
        // 'Accept': 'application/pdf',
        // 'Content-Type': 'multipart/form-data',
        // 'Access-Control-Allow-Origin': 'null'
      },
      body : fiormData,
    }).then(
      success => console.log(success) // Handle the success response object
    ).catch(
      error => console.log(error) // Handle the error response object
    );
  }
  closeApplicationPage()
  {
    this.location.back();
    //  this.router.navigate(['home']);
  }
}
