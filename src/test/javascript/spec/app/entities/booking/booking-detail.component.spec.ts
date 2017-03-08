import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { MockBackend } from '@angular/http/testing';
import { Http, BaseRequestOptions } from '@angular/http';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils } from 'ng-jhipster';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { BookingDetailComponent } from '../../../../../../main/webapp/app/entities/booking/booking-detail.component';
import { BookingService } from '../../../../../../main/webapp/app/entities/booking/booking.service';
import { Booking } from '../../../../../../main/webapp/app/entities/booking/booking.model';

describe('Component Tests', () => {

    describe('Booking Management Detail Component', () => {
        let comp: BookingDetailComponent;
        let fixture: ComponentFixture<BookingDetailComponent>;
        let service: BookingService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                declarations: [BookingDetailComponent],
                providers: [
                    MockBackend,
                    BaseRequestOptions,
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    {
                        provide: Http,
                        useFactory: (backendInstance: MockBackend, defaultOptions: BaseRequestOptions) => {
                            return new Http(backendInstance, defaultOptions);
                        },
                        deps: [MockBackend, BaseRequestOptions]
                    },
                    BookingService
                ]
            }).overrideComponent(BookingDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BookingDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BookingService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Booking(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.booking).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
