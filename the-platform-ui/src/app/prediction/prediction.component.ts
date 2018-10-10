import { Component, Input, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-prediction',
  templateUrl: './prediction.component.html',
  styleUrls: ['./prediction.component.css']
})
export class PredictionComponent implements OnInit {
  predictionResult: any;
  constructor(private router: Router, private route: ActivatedRoute) {
    this.route.params.subscribe(params => {
      // console.log('params', params);
      // console.log('params', JSON.parse(params['predictionResult']));
      this.predictionResult = JSON.parse(params['predictionResult']);
    });
  }
  ngOnInit() {
  }

}
