import { Component, OnInit } from '@angular/core';
import {PlatformApiService} from '../service/platform-api.service';




@Component({
  selector: 'app-find-hospital',
  templateUrl: './find-hospital.component.html',
  styleUrls: ['./find-hospital.component.scss']
})
export class FindHospitalComponent implements OnInit {
  hospitalInfo;
  constructor(private platformService: PlatformApiService) { }

  ngOnInit() {
    this.getAllHospitals();
  }
getAllHospitals() {
  this.platformService.getAllHospitalInfo().then(res => {
    this.hospitalInfo = res;
  });
  }
}
