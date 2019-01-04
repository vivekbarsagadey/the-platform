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
import { UploadFileComponent } from './upload-file/upload-file.component';
import { ContactComponent } from './contact/contact.component';
import {PlatformApiService} from './service/platform-api.service';
import { WhyChooseUsComponent } from './why-choose-us/why-choose-us.component';
import { FaqsComponent } from './faqs/faqs.component';
import { BlogComponent } from './blog/blog.component';
import { FindDoctorComponent } from './find-doctor/find-doctor.component';
import { FindHospitalComponent } from './find-hospital/find-hospital.component';
import { CountUpModule } from 'countup.js-angular2';
import { UserProfileComponent } from './user-profile/user-profile.component';

const appRoutes: Routes = [
  {path: 'platform', component: HomeComponent,
  children: [
    {path: 'home_page', component: HomePageComponent},
    {path: 'whychoosus', component: WhyChooseUsComponent},
    {path: 'faqs', component: FaqsComponent},
    {path: 'blog', component: BlogComponent},
    {path: 'contact', component: ContactComponent},
    {path: 'findDoctor', component: FindDoctorComponent },
    {path: 'findHospital', component: FindHospitalComponent},
    {path: '', redirectTo: 'platform/home_page', pathMatch: 'full'}
  ]
  },
  {path: 'apply', component: ApplyRouteComponent,
    children: [
      {path: 'check-diabetes', component: ApplyComponent},
      {path: 'apply-bulk', component: ApplyBulkComponent},
      {path: 'prediction', component: PredictionComponent},
      {path: 'upload-file', component: UploadFileComponent},
      {path: '', redirectTo: 'check-diabetes', pathMatch: 'full'}
    ]},
  {path: 'register', component: RegistrationComponent},
  {path: 'login', component: UserProfileComponent},
  {path: '', redirectTo: 'platform/home_page', pathMatch: 'full'}
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
    UploadFileComponent,
    ContactComponent,
    WhyChooseUsComponent,
    FaqsComponent,
    BlogComponent,
    FindDoctorComponent,
    FindHospitalComponent,
    UserProfileComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes),
    NgbModule,
    CountUpModule
  ],
  providers: [
    PlatformApiService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
