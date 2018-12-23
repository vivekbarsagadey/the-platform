import { Injectable } from '@angular/core';
import ApiUtil from '../../util/api-util';

@Injectable({
  providedIn: 'root'
})
export class PlatformApiService {

  constructor() { }


  uploadAllData(data) {
    return ApiUtil.postData('all', data)
  }

  uploadData(data) {
    return ApiUtil.postData('dataset/upload', data);
  }

}
