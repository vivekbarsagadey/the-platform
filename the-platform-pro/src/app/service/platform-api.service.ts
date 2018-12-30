import { Injectable } from '@angular/core';
import ApiUtil from '../../util/api-util';

@Injectable({
  providedIn: 'root'
})
export class PlatformApiService {

  constructor() { }


  uploadAllData(data) {
    return ApiUtil.postData('api/diabetes/all', data);
  }

  uploadData(data) {
    return ApiUtil.postData('api/diabetes/dataset/upload', data);
  }
  getAllHospitalInfo() {
    return ApiUtil.getJavaData('the-platform/api/file/hospital')
      .then(response => response.json());
  }
  getAllDoctorInfo() {
    return ApiUtil.getJavaData('the-platform/api/file/doctor')
      .then(response => response.json());
  }
}
