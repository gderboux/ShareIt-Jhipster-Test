import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Frequency } from './frequency.model';
import { FrequencyPopupService } from './frequency-popup.service';
import { FrequencyService } from './frequency.service';
import { Booking, BookingService } from '../booking';
@Component({
    selector: 'jhi-frequency-dialog',
    templateUrl: './frequency-dialog.component.html'
})
export class FrequencyDialogComponent implements OnInit {

    frequency: Frequency;
    authorities: any[];
    isSaving: boolean;

    bookings: Booking[];
    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private frequencyService: FrequencyService,
        private bookingService: BookingService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.bookingService.query({filter: 'frequency-is-null'}).subscribe((res: Response) => {
            if (!this.frequency.bookingId) {
                this.bookings = res.json();
            } else {
                this.bookingService.find(this.frequency.bookingId).subscribe((subRes: Booking) => {
                    this.bookings = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
    }
    clear () {
        this.activeModal.dismiss('cancel');
    }

    save () {
        this.isSaving = true;
        if (this.frequency.id !== undefined) {
            this.frequencyService.update(this.frequency)
                .subscribe((res: Frequency) => this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        } else {
            this.frequencyService.create(this.frequency)
                .subscribe((res: Frequency) => this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        }
    }

    private onSaveSuccess (result: Frequency) {
        this.eventManager.broadcast({ name: 'frequencyListModification', content: 'OK'});
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
}

@Component({
    selector: 'jhi-frequency-popup',
    template: ''
})
export class FrequencyPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private frequencyPopupService: FrequencyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            if ( params['id'] ) {
                this.modalRef = this.frequencyPopupService
                    .open(FrequencyDialogComponent, params['id']);
            } else {
                this.modalRef = this.frequencyPopupService
                    .open(FrequencyDialogComponent);
            }

        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
