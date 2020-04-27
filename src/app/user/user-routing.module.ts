import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserHomeComponent } from './user-home/user-home.component';
import { UserLayoutComponent } from '../layout/user-layout/user-layout.component';
import { AccoutComponent } from '../accout/accout.component';
import { ChangesLogComponent } from '../changes-log/changes-log.component';
import { NewConversionComponent } from '../new-conversion/new-conversion.component';
import { ConversionLogComponent } from '../conversion-log/conversion-log.component';

const routes: Routes = [
  {
    path: 'user-dashboard', component: UserLayoutComponent, children: [
      { path: '', component: UserHomeComponent },
      { path: 'account', component: AccoutComponent },
      { path: 'changeslog', component: ChangesLogComponent },
      { path: 'newconversion', component: NewConversionComponent },
      { path: 'conversionlog', component: ConversionLogComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
