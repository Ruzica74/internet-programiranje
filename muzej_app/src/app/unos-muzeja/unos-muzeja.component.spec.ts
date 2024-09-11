import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnosMuzejaComponent } from './unos-muzeja.component';

describe('UnosMuzejaComponent', () => {
  let component: UnosMuzejaComponent;
  let fixture: ComponentFixture<UnosMuzejaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnosMuzejaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UnosMuzejaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
