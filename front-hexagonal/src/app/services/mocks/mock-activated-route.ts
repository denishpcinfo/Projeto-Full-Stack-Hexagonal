import { BehaviorSubject } from 'rxjs';
import { convertToParamMap, ParamMap, Params } from '@angular/router';

export class MockActivatedRoute {
  private paramMapSubject = new BehaviorSubject<ParamMap>(convertToParamMap({}));
  private paramsSubject = new BehaviorSubject<Params>({});

  paramMap = this.paramMapSubject.asObservable();
  params = this.paramsSubject.asObservable();

  snapshot = {
    paramMap: convertToParamMap({}),
    params: {}
  };

  constructor(initialParams: Params = {}) {
    this.setParamMap(initialParams);
  }

  setParamMap(params: Params) {
    const paramMap = convertToParamMap(params);
    this.paramMapSubject.next(paramMap);
    this.paramsSubject.next(params);
    this.snapshot.paramMap = paramMap;
    this.snapshot.params = params;
  }
}
