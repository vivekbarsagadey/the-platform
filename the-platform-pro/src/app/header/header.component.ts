import { Component, OnInit } from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor( private modalService: NgbModal) { }

  ngOnInit() {
  }
  onClickViewProduct(content) {
    // this.productService.getMenuItem(id).then(res => {
    //   this.viewData = res;
    // });
    this.modalService.open(content, {size: 'sm', ariaLabelledBy: 'view-product'}).result.then((result) => {
    }, (reason) => {
      // this.viewData = '';
    });
  }
}
