import {Component, Input, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TelemetryData} from "../../entity/TelemetryData";

@Component({
  selector: 'app-truck-info',
  templateUrl: './truck-info.component.html',
  styleUrls: ['./truck-info.component.less']
})
export class TruckInfoComponent implements OnInit {

  @Input()
  data: TelemetryData;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  wipers(): void {
    this.http.get("http://192.168.1.155:4567/api/wipers").subscribe(res => {
      // console.log(res);
    })
  }

}
