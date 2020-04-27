import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginModule } from './login/login.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { LayoutModule } from './layout/layout.module';
import { AccoutComponent } from './accout/accout.component';
import { FormsModule } from '@angular/forms';
import { ChangesLogComponent } from './changes-log/changes-log.component';
import { NewChangesComponent } from './new-changes/new-changes.component';
import { NewConversionComponent } from './new-conversion/new-conversion.component';
import { ConversionLogComponent } from './conversion-log/conversion-log.component';
import {MatSelectModule} from '@angular/material/select'

@NgModule({
  declarations: [
    AppComponent,
    AccoutComponent,
    ChangesLogComponent,
    NewChangesComponent,
    NewConversionComponent,
    ConversionLogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LoginModule,
    BrowserAnimationsModule,
    HttpClientModule,
    LayoutModule,
    FormsModule,
    MatSelectModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
