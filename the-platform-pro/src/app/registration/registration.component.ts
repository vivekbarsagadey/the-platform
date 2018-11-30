import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router, Params} from '@angular/router';
import {Location} from '@angular/common';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  closeResult: string;
  constructor(private route: ActivatedRoute, private location: Location,
              private router: Router, private modalService: NgbModal) { }

  ngOnInit() {
  }

  closeRegistrationPage() {
    this.location.back();
    //  this.router.navigate(['home']);
  }
  onClickViewProduct(content) {
    // this.productService.getMenuItem(id).then(res => {
    //   this.viewData = res;
    // });
    this.modalService.open(content, {size: 'lg', ariaLabelledBy: 'view-product'}).result.then((result) => {
    }, (reason) => {
      // this.viewData = '';
    });
  }

}
