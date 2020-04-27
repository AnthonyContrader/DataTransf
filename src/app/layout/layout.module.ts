import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserLayoutComponent } from './user-layout/user-layout.component';
import { AdminLayoutComponent } from './admin-layout/admin-layout.component';
import { RouterModule } from '@angular/router';
import { AdminModule } from '../admin/admin.module';
import { UserModule } from '../user/user.module';

@NgModule({
  declarations: [UserLayoutComponent, AdminLayoutComponent],
  imports: [
    CommonModule,
    RouterModule,
    AdminModule,
    UserModule
  ]
})
export class LayoutModule { }
