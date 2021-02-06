import {Component, Input, OnInit} from '@angular/core';
import {TelemetryData} from "../../entity/TelemetryData";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-buttons',
  templateUrl: './buttons.component.html',
  styleUrls: ['./buttons.component.less']
})
export class ButtonsComponent implements OnInit {

  @Input()
  data: TelemetryData;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

}
