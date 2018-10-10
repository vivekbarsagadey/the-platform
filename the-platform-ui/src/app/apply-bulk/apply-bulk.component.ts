import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-apply-bulk',
  templateUrl: './apply-bulk.component.html',
  styleUrls: ['./apply-bulk.component.css']
})
export class ApplyBulkComponent implements OnInit {
  checkDiabetes: boolean;
  prediction: boolean;
  uploadFile: boolean;
  sendToServer:  any;
  registerForm: FormGroup;
  submitted = false;
  predictionResult: any;
  user: any = {};
  constructor(private formBuilder: FormBuilder, private router: Router) {
    this.checkDiabetes = true;
    this.prediction = false;
  }
  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
     applyBulk: ['', Validators.required]
    });
  }
  get f() { return this.registerForm.controls; }
  onSubmit() {
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
    }
  }
  applyForm() {
    this.checkDiabetes = false;
    this.uploadFile = false;
    this.prediction = true;
  }
  openClickSubmit() {
    const data = this.user.applyBulk.split(',');
    this.sendToServer = {
      firstName : this.user.firstName,
      lastName : this.user.lastName,
      pregnancy : Number(data[0]),
      glucose : Number(data[1]),
      bloodpressure: Number(data[2]),
      skinThickness : Number(data[3]),
      insulin: Number(data[4]),
      bmi : Number(data[5]),
      diabetesPedigreeFunction : Number(data[6]),
      age : Number(data[7])
    };
    console.log('this.sendToServer', this.sendToServer);
    fetch('http://localhost:5000/api/diabetes/all', {
      method: 'POST', // or 'PUT'
      body: JSON.stringify(this.sendToServer), // data can be `string` or {object}!
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      }
    }).then(res => res.json())
      .catch(error => console.error('Error:', error))
      .then(response => {
        this.predictionResult = JSON.stringify(response);
        this.router.navigate(['apply/prediction', {predictionResult: this.predictionResult}]);
        console.log('Success:', response);
      });
   this.applyForm();
  }
}
