import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';

describe('AppComponent', () => {
  let fixture: ComponentFixture<AppComponent>;
  let component: AppComponent;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppComponent] // standalone
    }).compileComponents();

    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('ao criar o aplicativo', () => {
    expect(component).toBeTruthy();
  });

  it('deveria ter o título "front-hexagonal"', () => {
    expect(component.title).toBe('front-hexagonal');
  });

});
