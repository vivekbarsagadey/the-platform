import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router, Params} from '@angular/router';
import {Location} from '@angular/common';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {FormControl, FormGroup, FormBuilder, Validators} from '@angular/forms';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  closeResult: string;
  formdata: FormGroup;
  constructor(private route: ActivatedRoute, private location: Location,
              private router: Router, private modalService: NgbModal) { }

  ngOnInit() {
    this.formdata = new FormGroup({
      firstName: new FormControl(),
      lastName: new FormControl(),
      email: new FormControl(),
      phone: new FormControl(),
      password: new FormControl(),
      confirmPassword: new FormControl(),
      dateOfBirth: new FormControl(),
      type: new FormControl(),
    });
  }

  closeRegistrationPage() {
    this.location.back();
    //  this.router.navigate(['home']);
  }
  // onClickViewProduct(content) {
  //   this.modalService.open(content, {size: 'lg', ariaLabelledBy: 'view-product'}).result.then((result) => {
  //   }, (reason) => {
  //   });
  // }
  // registerUser(data) {
  //   console.log('registered', data);
  // }
  registerUser(data) {
    console.log('registered', data);
  }
}
