import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { MockBackend } from '@angular/http/testing';
import { Http, BaseRequestOptions } from '@angular/http';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils } from 'ng-jhipster';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AppUserDetailComponent } from '../../../../../../main/webapp/app/entities/app-user/app-user-detail.component';
import { AppUserService } from '../../../../../../main/webapp/app/entities/app-user/app-user.service';
import { AppUser } from '../../../../../../main/webapp/app/entities/app-user/app-user.model';

describe('Component Tests', () => {

    describe('AppUser Management Detail Component', () => {
        let comp: AppUserDetailComponent;
        let fixture: ComponentFixture<AppUserDetailComponent>;
        let service: AppUserService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                declarations: [AppUserDetailComponent],
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
                    AppUserService
                ]
            }).overrideComponent(AppUserDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AppUserDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AppUserService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new AppUser(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.appUser).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
