import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminLayoutComponent } from '../layout/admin-layout/admin-layout.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { UsersComponent } from './users/users.component';
import { WorkInProgressComponent } from './work-in-progress/work-in-progress.component';
import { AccountComponent } from './account/account.component';
import { NewconversionComponent } from '../user/newconversion/newconversion.component';
import { ConversionlogComponent } from '../user/conversionlog/conversionlog.component';
import { ChangeslogComponent } from '../user/changeslog/changeslog.component';
import { GeneralinfoComponent } from './generalinfo/generalinfo.component';

/**
 * Modulo di routing dell'admin. Qui ci sono i percorsi che un admin può seguire:
 * appena fa il login viene caricato nel <router-outlet> di app-component il layout e nel 
 * <router-outlet> del layout (come percorsi "children") vengono visualizzati gli altri 
 * (qui sotto sono indentati).
 * 
 * @author Vittorio Valent
 * 
 * @see AdminLayoutComponent
 * 
 * @see layout
 */
const routes: Routes = [
    { path: 'admin-dashboard', component: AdminLayoutComponent, children:[
    { path: '', component: AdminDashboardComponent },
    { path: 'users', component: UsersComponent },
    { path: 'work-in-progress', component: WorkInProgressComponent },
    { path: 'account', component: AccountComponent },
    { path: 'newconversion', component: NewconversionComponent },
    { path: 'conversionlog', component: ConversionlogComponent },
    { path: 'changeslog', component: ChangeslogComponent },
    { path: 'generalinfo', component: GeneralinfoComponent }
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }