import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserLayoutComponent } from '../layout/user-layout/user-layout.component';
import { UserHomeComponent } from './user-home/user-home.component';
import { AccountComponent } from '../account/account.component';
import { ConversionLogComponent } from '../conversion-log/conversion-log.component';
import { ChangesLogComponent } from '../changes-log/changes-log.component';
import { NewChangesComponent } from '../new-changes/new-changes.component';
import { NewConversionComponent } from '../new-conversion/new-conversion.component';

const routes: Routes = [
  {path: 'user-dashboard', component: UserLayoutComponent, children: [
    {path: '', component: UserHomeComponent},
    {path: 'account', component: AccountComponent},
    {path: 'conversionlog', component: ConversionLogComponent},
    {path: 'changeslog', component: ChangesLogComponent},
    {path: 'newchanges', component: NewChangesComponent},
    {path: 'newconversion', component: NewConversionComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
