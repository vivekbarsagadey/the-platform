import { Component, OnInit } from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {FormControl, FormGroup} from '@angular/forms';
import {PlatformApiService} from '../service/platform-api.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  formdata: FormGroup;
  dataSend: any = {'name': 'Ghanshyam',
    'sname': 'GhanshyamMra'};
  constructor( private modalService: NgbModal, private platformService: PlatformApiService) { }

  ngOnInit() {
    this.formdata = new FormGroup({
      emailId: new FormControl(),
      password: new FormControl()
    });
  }
  onClickViewLogin(content) {
    // this.productService.getMenuItem(id).then(res => {
    //   this.viewData = res;
    // });
    this.modalService.open(content, {size: 'sm', ariaLabelledBy: 'view-product'}).result.then((data) => {
      console.log('login data', data);
      this.useralogin(data);
    }, (reason) => {
      // this.viewData = '';
    });
  }
  useralogin(data) {
    this.platformService.loginUser(data).then(res => {
      // this.doctorsInfo = res;
      // alert('user register successfuly');
    });
  }
}
