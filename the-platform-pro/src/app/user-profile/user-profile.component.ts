import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  data;
  constructor( private route: ActivatedRoute) {
    this.route.params.subscribe((data) => this.data = data);
  }

  ngOnInit() {
  }

}
