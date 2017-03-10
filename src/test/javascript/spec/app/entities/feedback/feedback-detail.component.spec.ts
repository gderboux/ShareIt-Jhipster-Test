import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { MockBackend } from '@angular/http/testing';
import { Http, BaseRequestOptions } from '@angular/http';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils } from 'ng-jhipster';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { FeedbackDetailComponent } from '../../../../../../main/webapp/app/entities/feedback/feedback-detail.component';
import { FeedbackService } from '../../../../../../main/webapp/app/entities/feedback/feedback.service';
import { Feedback } from '../../../../../../main/webapp/app/entities/feedback/feedback.model';

describe('Component Tests', () => {

    describe('Feedback Management Detail Component', () => {
        let comp: FeedbackDetailComponent;
        let fixture: ComponentFixture<FeedbackDetailComponent>;
        let service: FeedbackService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                declarations: [FeedbackDetailComponent],
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
                    FeedbackService
                ]
            }).overrideComponent(FeedbackDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FeedbackDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FeedbackService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Feedback(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.feedback).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
