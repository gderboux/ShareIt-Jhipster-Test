import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { MockBackend } from '@angular/http/testing';
import { Http, BaseRequestOptions } from '@angular/http';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils } from 'ng-jhipster';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { FrequencyDetailComponent } from '../../../../../../main/webapp/app/entities/frequency/frequency-detail.component';
import { FrequencyService } from '../../../../../../main/webapp/app/entities/frequency/frequency.service';
import { Frequency } from '../../../../../../main/webapp/app/entities/frequency/frequency.model';

describe('Component Tests', () => {

    describe('Frequency Management Detail Component', () => {
        let comp: FrequencyDetailComponent;
        let fixture: ComponentFixture<FrequencyDetailComponent>;
        let service: FrequencyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                declarations: [FrequencyDetailComponent],
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
                    FrequencyService
                ]
            }).overrideComponent(FrequencyDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FrequencyDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FrequencyService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Frequency(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.frequency).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
