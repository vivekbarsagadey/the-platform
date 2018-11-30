import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { RegistrationComponent } from './registration/registration.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './home/about/about.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomePageComponent } from './home/home-page/home-page.component';
import { ApplyComponent } from './apply/apply.component';
import { ApplyBulkComponent } from './apply-bulk/apply-bulk.component';
import { ApplyRouteComponent } from './apply-route/apply-route.component';
import { PredictionComponent } from './prediction/prediction.component';


const appRoutes: Routes = [
  {path: 'home', component: HomeComponent,
  children: [{path: 'home_page', component: HomePageComponent},
    {path: 'about', component: AboutComponent},
    {path: '', redirectTo: 'home_page', pathMatch: 'full'}]
  },
  {path: 'apply', component: ApplyRouteComponent,
    children: [
      {path: 'check-diabetes', component: ApplyComponent},
      {path: 'apply-bulk', component: ApplyBulkComponent},
      {path: 'prediction', component: PredictionComponent},
      // {path: 'upload-file', component: UploadFileComponent},
      {path: '', redirectTo: 'check-diabetes',
        pathMatch: 'full'
      }
    ]},
  {path: 'register', component: RegistrationComponent},
  {path: '', redirectTo: 'home', pathMatch: 'full'}
];


@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    HomeComponent,
    AboutComponent,
    HeaderComponent,
    FooterComponent,
    HomePageComponent,
    ApplyComponent,
    ApplyBulkComponent,
    ApplyRouteComponent,
    PredictionComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes),
    NgbModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
