import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminLayoutComponent } from 'src/app/layout/admin-layout/admin-layout.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AccountComponent } from '../account/account.component';
import { ConversionLogComponent } from '../conversion-log/conversion-log.component';
import { AllConversionLogComponent } from './all-conversion-log/all-conversion-log.component';
import { ChangesLogComponent } from '../changes-log/changes-log.component';
import { AllChangesLogComponent } from './all-changes-log/all-changes-log.component';
import { NewChangesComponent } from '../new-changes/new-changes.component';
import { NewConversionComponent } from '../new-conversion/new-conversion.component';

const routes: Routes = [
  {path: 'admin-dashboard', component: AdminLayoutComponent, children: [
    {path: '', component: AdminHomeComponent},
    {path: 'account', component: AccountComponent},
    {path: 'conversionlog', component: ConversionLogComponent},
    {path: 'allconversionlog', component: AllConversionLogComponent},
    {path: 'changeslog', component: ChangesLogComponent},
    {path: 'allchangeslog', component: AllChangesLogComponent},
    {path: 'newchanges', component: NewChangesComponent},
    {path: 'newconversion', component: NewConversionComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
