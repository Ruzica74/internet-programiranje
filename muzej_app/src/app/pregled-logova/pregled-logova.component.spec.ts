import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PregledLogovaComponent } from './pregled-logova.component';

describe('PregledLogovaComponent', () => {
  let component: PregledLogovaComponent;
  let fixture: ComponentFixture<PregledLogovaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PregledLogovaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PregledLogovaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
