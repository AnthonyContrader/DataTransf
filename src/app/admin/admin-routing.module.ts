import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminLayoutComponent } from '../layout/admin-layout/admin-layout.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AccoutComponent } from '../accout/accout.component';
import { UsersComponent } from './users/users.component';
import { ChangesLogComponent } from '../changes-log/changes-log.component';
import { NewChangesComponent } from '../new-changes/new-changes.component';
import { AllChangesLogComponent } from './all-changes-log/all-changes-log.component';
import { NewConversionComponent } from '../new-conversion/new-conversion.component';
import { ConversionLogComponent } from '../conversion-log/conversion-log.component';
import { AllConversionLogComponent } from './all-conversion-log/all-conversion-log.component';

const routes: Routes = [
  {
    path: 'admin-dashboard', component: AdminLayoutComponent, children: [
      { path: '', component: AdminHomeComponent },
      { path: 'account', component: AccoutComponent },
      { path: 'users', component: UsersComponent },
      { path: 'changeslog', component: ChangesLogComponent },
      { path: 'changes', component: NewChangesComponent },
      { path: 'allchangeslog', component: AllChangesLogComponent },
      { path: 'newconversion', component: NewConversionComponent },
      { path: 'conversionlog', component: ConversionLogComponent },
      { path: 'allconversionlog', component: AllConversionLogComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
