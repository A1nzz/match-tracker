import { Component } from '@angular/core';
import { RouterOutlet, RouterModule } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterModule, NavbarComponent],
  templateUrl: './app.component.html',
})
export class AppComponent {
  title = 'match-tracker-angular';
}
