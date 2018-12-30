import { Component, OnInit } from '@angular/core';
import {PlatformApiService} from '../service/platform-api.service';


@Component({
  selector: 'app-find-doctor',
  templateUrl: './find-doctor.component.html',
  styleUrls: ['./find-doctor.component.scss']
})
export class FindDoctorComponent implements OnInit {
  doctorsInfo;
  constructor(private platformService: PlatformApiService) { }

  ngOnInit() {
    this.getAllDoctors();
  }
  getAllDoctors() {
    this.platformService.getAllDoctorInfo().then(res => {
      this.doctorsInfo = res;
    });
  }
}
