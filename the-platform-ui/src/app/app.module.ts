import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule, } from '@angular/forms';
import { AppComponent } from './app.component';
import { ApplyComponent } from './apply/apply.component';
import {HttpClientModule} from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FeaturesComponent } from './features/features.component';
import { CustomerSaysComponent } from './customer-says/customer-says.component';
import { ApplyBulkComponent } from './apply-bulk/apply-bulk.component';
import { ApplyRouteComponent } from './apply-route/apply-route.component';
import { PredictionComponent } from './prediction/prediction.component';
import { UploadFileComponent } from './upload-file/upload-file.component';
import { NewsComponent } from './news/news.component';
import { DiabetesComponent } from './guide/diabetes/diabetes.component';
import { ToolComponent } from './search/tool/tool.component';
import { FindDoctorComponent } from './search/find-doctor/find-doctor.component';
import { FindHospitalComponent } from './search/find-hospital/find-hospital.component';



const appRoutes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'find-doctor', component: FindDoctorComponent },
  {path: 'find-hospital', component: FindHospitalComponent},
  {path: 'apply', component: ApplyRouteComponent,
  children: [
    {path: 'check-diabetes', component: ApplyComponent},
    {path: 'apply-bulk', component: ApplyBulkComponent},
    {path: 'prediction', component: PredictionComponent},
    {path: 'upload-file', component: UploadFileComponent},
    {
      path: '',
      redirectTo: 'check-diabetes',
      pathMatch: 'full'
    }
  ]},
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  {path: '**', component: AppComponent}
];


@NgModule({
  declarations: [
    AppComponent,
    ApplyComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    FeaturesComponent,
    CustomerSaysComponent,
    ApplyBulkComponent,
    ApplyRouteComponent,
    PredictionComponent,
    UploadFileComponent,
    NewsComponent,
    DiabetesComponent,
    ToolComponent,
    FindDoctorComponent,
    FindHospitalComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
