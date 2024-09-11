import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KreiranjePosjetaComponent } from './kreiranje-posjeta.component';

describe('KreiranjePosjetaComponent', () => {
  let component: KreiranjePosjetaComponent;
  let fixture: ComponentFixture<KreiranjePosjetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KreiranjePosjetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KreiranjePosjetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
