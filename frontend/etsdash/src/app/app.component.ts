import {Component, OnInit, Output} from '@angular/core';
import {Observable, timer} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {repeat, retry, switchMap} from "rxjs/operators";
import {TelemetryData} from "./entity/TelemetryData";
import {TelemetryDataResponse} from "./entity/TelemetryDataResponse";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent implements OnInit {

  @Output()
  data: TelemetryData = new class implements TelemetryData {
    blinkerLeftActive: boolean;
    blinkerRightActive: boolean;
    parkBrake: boolean;
    wipers: boolean;
    engineRpm: number;
    formattedGameTime: String;
    truckBrand: String;
    truckName: String;
  };

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    timer(100, 250).pipe(
      switchMap(tick => {
        //console.log(tick);
        this.http.get<TelemetryDataResponse>("http://192.168.1.155:4567/api/data").subscribe(res => {
         // console.log(res);
          this.data = res.data;
        })
        return new Observable(s => s.next(tick));
      }),
      retry(3), // retry 3 times before bubbling an error
      repeat()
    ).subscribe(res => "lala")

  }

}
