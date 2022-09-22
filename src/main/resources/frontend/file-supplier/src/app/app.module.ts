import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {FormsModule} from "@angular/forms";
import {ProgressComponent} from "./progress/progress.component";
import {DndDirective} from "./dnd.directive";
import { NgModule } from '@angular/core';

@NgModule({
  imports: [BrowserModule, FormsModule],
  declarations: [AppComponent, DndDirective, ProgressComponent],
  bootstrap: [AppComponent]
})
export class AppModule {
}
