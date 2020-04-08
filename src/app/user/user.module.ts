import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { WorkInProgressComponent } from './work-in-progress/work-in-progress.component';
import { UserRoutingModule } from './user-routing.module';
import { FormsModule } from '@angular/forms';
import { AccountComponent } from './account/account.component';
import { ConversionlogComponent } from './conversionlog/conversionlog.component';
import { ChangeslogComponent } from './changeslog/changeslog.component';

@NgModule({
  declarations: [UserDashboardComponent, WorkInProgressComponent, AccountComponent, ConversionlogComponent, ChangeslogComponent],
  imports: [
    CommonModule,
    UserRoutingModule,
    FormsModule
  ]
})
export class UserModule { }
