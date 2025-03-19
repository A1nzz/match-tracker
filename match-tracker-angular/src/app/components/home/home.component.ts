import { Component, OnInit } from '@angular/core';
import { HomeService } from '../../services/home.service';
import { RouterModule } from '@angular/router';
import { HomePageDTO } from '../../models/models';

@Component({
  selector: 'app-home',
  imports: [RouterModule],
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {
  homePageData: HomePageDTO | null = null;


  constructor(private readonly homeService: HomeService) {}

  ngOnInit() {
    this.homeService.getHomePageData().subscribe((data) => {
      this.homePageData = data;
    });
  }
}