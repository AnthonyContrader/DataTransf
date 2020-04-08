import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.css']
})
export class UserMenuComponent implements OnInit {

  isClientCollapsed = false;
  isAccountCollapsed = false;

  constructor(private router: Router) { }

  ngOnInit() {
  }

  logout() {
    localStorage.clear();
    this.router.navigateByUrl('');
  }

  accountcollapse() {
    if(this.isAccountCollapsed){
      this.isAccountCollapsed = false
    }else {
      this.isAccountCollapsed = true
    }
  }

}
