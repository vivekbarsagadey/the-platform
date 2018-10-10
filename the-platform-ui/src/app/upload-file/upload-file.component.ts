import { Component } from '@angular/core';


@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css'],
})
export class UploadFileComponent  {
  selectedFile: File = null;
  constructor() { }
  selectFile(event) {
    this.selectedFile = <File>event.target.files[0];
  }

  upload() {
    const fiormData = new FormData();
    fiormData.append('file', this.selectedFile);
    fetch('http://localhost:5000/api/diabetes/dataset/upload', {
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
}
