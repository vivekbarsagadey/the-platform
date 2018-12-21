import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Location} from '@angular/common';
import {FormControl, FormsModule} from '@angular/forms';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-apply',
  templateUrl: './apply.component.html',
  styleUrls: ['./apply.component.scss']
})
export class ApplyComponent implements OnInit {
  checkDiabetes: boolean;
  prediction: boolean;
  registerForm: FormGroup;
  submitted = false;
  sendToServer: any;
  predictionResult: any;
  user: any = {};
  constructor(private location: Location, private formBuilder: FormBuilder, private router: Router) {
    this.checkDiabetes = true;
    this.prediction = false;
  }
  ngOnInit() {
    // this.registerForm = this.formBuilder.group({
    //   firstName: ['', Validators.required],
    //   lastName: ['', Validators.required],
    //   pregnancy: ['', Validators.required], glucose: ['', Validators.required],
    //   bloodpressure: ['', Validators.required],
    //   skinThickness: ['', Validators.required],
    //   insulin: ['', Validators.required],
    //   bmi: ['', Validators.required],
    //   diabetesPedigreeFunction: ['', Validators.required],
    //   age: ['', Validators.required]
    // });
    this.registerForm = new FormGroup({
      firstName: new FormControl('', [Validators.required]),
      lastName:  new FormControl('', [Validators.required]),
      pregnancy:  new FormControl('', [Validators.required]),
      glucose:  new FormControl('', [Validators.required]),
      bloodpressure:  new FormControl('', [Validators.required]),
      skinThickness:  new FormControl('', [Validators.required]),
      insulin:  new FormControl('', [Validators.required]),
      bmi: new FormControl('', [Validators.required]),
      diabetesPedigreeFunction:  new FormControl('', [Validators.required]),
      age: new FormControl('', [Validators.required])
    });
  }
  get f() { return this.registerForm.controls; }
  onSubmit() {
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
    }
    // console.log(JSON.stringify(this.registerForm.value));
    fetch('http://192.168.1.126:5000/api/diabetes/all', {
      method: 'POST', // or 'PUT'
      body: JSON.stringify(this.registerForm.value), // data can be `string` or {object}!
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
  }
  applyForm() {
    this.checkDiabetes = false;
    this.prediction = true;
    this.onSubmit();
    // this.openClickSubmit();
  }
  // openClickSubmit() {
  //    this.sendToServer = {
  //      'firstName' : this.user.firstName,
  //      'lastName' : this.user.lastName,
  //      'pregnancy' : this.user.pregnancy,
  //      'glucose' : this.user.glucose,
  //      'bloodpressure' : this.user.bloodpressure,
  //      'skinThickness' : this.user.skinThickness,
  //      'insulin' : this.user.insulin,
  //      'bmi' : this.user.bmi,
  //      'diabetesPedigreeFunction' : this.user.diabetesPedigreeFunction,
  //      'age' : this.user.age
  //    };
  //    fetch('http://localhost:8080/api/applications/', {
  //      method: 'POST', // or 'PUT'
  //      body: JSON.stringify(this.sendToServer), // data can be `string` or {object}!
  //      headers: {
  //        'Accept': 'application/json',
  //        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
  //      }
  //    }).then(res => res.json())
  //      .catch(error => console.error('Error:', error))
  //      .then(response => console.log('Success:', response));
  //  }
  closeApplicationPage()
  {
    this.location.back();
    //  this.router.navigate(['home']);
  }
}
