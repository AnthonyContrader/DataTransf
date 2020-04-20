import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { LoginModule } from './login/login.module';
import { HttpClientModule } from '@angular/common/http';
import { LayoutModule } from './layout/layout.module';
import { AdminModule } from './admin/admin.module';
import { AccountComponent } from './account/account.component';
import { FormsModule } from '@angular/forms';
import { ConversionLogComponent } from './conversion-log/conversion-log.component';
import { ChangesLogComponent } from './changes-log/changes-log.component';
import { UserModule } from './user/user.module';
import { NewChangesComponent } from './new-changes/new-changes.component';
import { NewConversionComponent } from './new-conversion/new-conversion.component';
import { UsersComponent } from './admin/users/users.component';

@NgModule({
  declarations: [
    AppComponent,
    AccountComponent,
    ConversionLogComponent,
    ChangesLogComponent,
    NewChangesComponent,
    NewConversionComponent,
    UsersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LoginModule,
    HttpClientModule,
    LayoutModule,
    AdminModule,
    UserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
