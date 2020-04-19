import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminLayoutComponent } from './admin-layout/admin-layout.component';
import { RouterModule } from '@angular/router';
import { UserLayoutComponent } from './user-layout/user-layout.component';

@NgModule({
  declarations: [AdminLayoutComponent, UserLayoutComponent],
  imports: [
    CommonModule,
    RouterModule
  ]
})
export class LayoutModule { }
