import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RadSaKorisnicimaComponent } from './rad-sa-korisnicima.component';

describe('RadSaKorisnicimaComponent', () => {
  let component: RadSaKorisnicimaComponent;
  let fixture: ComponentFixture<RadSaKorisnicimaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RadSaKorisnicimaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RadSaKorisnicimaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
