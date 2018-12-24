import { Component } from '@angular/core';
import {Location} from '@angular/common';
import {PlatformApiService} from '../service/platform-api.service';


@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.scss'],
})
export class UploadFileComponent  {
  selectedFile: File = null;
  constructor(private location: Location, private apiService: PlatformApiService) { }
  selectFile(event) {
    this.selectedFile = <File>event.target.files[0];
  }

  upload() {
    const fiormData = new FormData();
    fiormData.append('file', this.selectedFile);
    this.apiService.uploadData(fiormData).then(
      success => {
        if(success.status === 200){
          alert('File Uploaded Successfuly');
        }
      }
      // Handle the success response object
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
