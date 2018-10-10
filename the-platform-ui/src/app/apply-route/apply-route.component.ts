import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-apply-route',
  templateUrl: './apply-route.component.html',
  styleUrls: ['./apply-route.component.css']
})
export class ApplyRouteComponent implements OnInit {
  checkDiabetes: boolean;
  prediction: boolean;
  uploadFile: boolean;
  constructor() {
    this.checkDiabetes = true;
    this.prediction = false;
    this.uploadFile = false;
  }

  ngOnInit() {
  }

}
