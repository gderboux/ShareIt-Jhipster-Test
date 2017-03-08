import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Feedback } from './feedback.model';
import { FeedbackPopupService } from './feedback-popup.service';
import { FeedbackService } from './feedback.service';
import { Booking, BookingService } from '../booking';
import { AppUser, AppUserService } from '../app-user';
@Component({
    selector: 'jhi-feedback-dialog',
    templateUrl: './feedback-dialog.component.html'
})
export class FeedbackDialogComponent implements OnInit {

    feedback: Feedback;
    authorities: any[];
    isSaving: boolean;

    bookings: Booking[];

    users: AppUser[];

    reporters: AppUser[];
    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private feedbackService: FeedbackService,
        private bookingService: BookingService,
        private appUserService: AppUserService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.bookingService.query({filter: 'feedback-is-null'}).subscribe((res: Response) => {
            if (!this.feedback.bookingId) {
                this.bookings = res.json();
            } else {
                this.bookingService.find(this.feedback.bookingId).subscribe((subRes: Booking) => {
                    this.bookings = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
        this.appUserService.query({filter: 'feedback-is-null'}).subscribe((res: Response) => {
            if (!this.feedback.userId) {
                this.users = res.json();
            } else {
                this.appUserService.find(this.feedback.userId).subscribe((subRes: AppUser) => {
                    this.users = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
        this.appUserService.query({filter: 'feedback-is-null'}).subscribe((res: Response) => {
            if (!this.feedback.reporterId) {
                this.reporters = res.json();
            } else {
                this.appUserService.find(this.feedback.reporterId).subscribe((subRes: AppUser) => {
                    this.reporters = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
    }
    clear () {
        this.activeModal.dismiss('cancel');
    }

    save () {
        this.isSaving = true;
        if (this.feedback.id !== undefined) {
            this.feedbackService.update(this.feedback)
                .subscribe((res: Feedback) => this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        } else {
            this.feedbackService.create(this.feedback)
                .subscribe((res: Feedback) => this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        }
    }

    private onSaveSuccess (result: Feedback) {
        this.eventManager.broadcast({ name: 'feedbackListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError (error) {
        this.isSaving = false;
        this.onError(error);
    }

    private onError (error) {
        this.alertService.error(error.message, null, null);
    }

    trackBookingById(index: number, item: Booking) {
        return item.id;
    }

    trackAppUserById(index: number, item: AppUser) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-feedback-popup',
    template: ''
})
export class FeedbackPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private feedbackPopupService: FeedbackPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            if ( params['id'] ) {
                this.modalRef = this.feedbackPopupService
                    .open(FeedbackDialogComponent, params['id']);
            } else {
                this.modalRef = this.feedbackPopupService
                    .open(FeedbackDialogComponent);
            }

        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
