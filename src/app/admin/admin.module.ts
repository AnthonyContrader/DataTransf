import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AllConversionLogComponent } from './all-conversion-log/all-conversion-log.component';
import { AllChangesLogComponent } from './all-changes-log/all-changes-log.component';



@NgModule({
  declarations: [AdminHomeComponent, AllConversionLogComponent, AllChangesLogComponent],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
