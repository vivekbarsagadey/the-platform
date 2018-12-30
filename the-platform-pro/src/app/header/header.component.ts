import { Component, OnInit } from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {FormControl, FormGroup} from '@angular/forms';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  formdata: FormGroup;
  constructor( private modalService: NgbModal) { }

  ngOnInit() {
    this.formdata = new FormGroup({
      email: new FormControl(),
      password: new FormControl()
    });
  }
  onClickViewLogin(content) {
    // this.productService.getMenuItem(id).then(res => {
    //   this.viewData = res;
    // });
    this.modalService.open(content, {size: 'sm', ariaLabelledBy: 'view-product', backdrop: 'static'}).result.then((data) => {
      console.log('login data', data);
    }, (reason) => {
      // this.viewData = '';
    });
  }
}
