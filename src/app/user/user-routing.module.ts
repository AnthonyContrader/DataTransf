import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserLayoutComponent } from '../layout/user-layout/user-layout.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { WorkInProgressComponent } from './work-in-progress/work-in-progress.component';
import { AccountComponent } from './account/account.component';
import { ConversionlogComponent } from './conversionlog/conversionlog.component';
import { ChangeslogComponent } from './changeslog/changeslog.component';
import { NewconversionComponent } from './newconversion/newconversion.component';
import { NewchangesComponent } from './newchanges/newchanges.component';

const routes: Routes = [
  { path: 'user-dashboard', component: UserLayoutComponent, children:[
    { path: '', component: UserDashboardComponent },
    { path: 'account', component: AccountComponent },
    { path: 'conversionlog', component: ConversionlogComponent },
    { path: 'work-in-progress', component: WorkInProgressComponent },
    { path: 'changeslog', component: ChangeslogComponent },
    { path: 'newconversion', component: NewconversionComponent },
    { path: 'newchanges', component: NewchangesComponent }
]}
];

@NgModule({
  exports: [RouterModule],
  imports: [
    RouterModule.forChild(routes)
  ]
})
export class UserRoutingModule { }
