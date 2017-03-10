import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Booking } from './booking.model';
import { BookingPopupService } from './booking-popup.service';
import { BookingService } from './booking.service';
import { AppUser, AppUserService } from '../app-user';
import { Address, AddressService } from '../address';
@Component({
    selector: 'jhi-booking-dialog',
    templateUrl: './booking-dialog.component.html'
})
export class BookingDialogComponent implements OnInit {

    booking: Booking;
    authorities: any[];
    isSaving: boolean;

    drivers: AppUser[];

    owners: AppUser[];

    startaddresses: Address[];

    endaddresses: Address[];

    bookings: Booking[];
    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private bookingService: BookingService,
        private appUserService: AppUserService,
        private addressService: AddressService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.appUserService.query({filter: 'booking-is-null'}).subscribe((res: Response) => {
            if (!this.booking.driverId) {
                this.drivers = res.json();
            } else {
                this.appUserService.find(this.booking.driverId).subscribe((subRes: AppUser) => {
                    this.drivers = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
        this.appUserService.query({filter: 'booking-is-null'}).subscribe((res: Response) => {
            if (!this.booking.ownerId) {
                this.owners = res.json();
            } else {
                this.appUserService.find(this.booking.ownerId).subscribe((subRes: AppUser) => {
                    this.owners = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
        this.addressService.query({filter: 'booking-is-null'}).subscribe((res: Response) => {
            if (!this.booking.startAddressId) {
                this.startaddresses = res.json();
            } else {
                this.addressService.find(this.booking.startAddressId).subscribe((subRes: Address) => {
                    this.startaddresses = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
        this.addressService.query({filter: 'booking-is-null'}).subscribe((res: Response) => {
            if (!this.booking.endAddressId) {
                this.endaddresses = res.json();
            } else {
                this.addressService.find(this.booking.endAddressId).subscribe((subRes: Address) => {
                    this.endaddresses = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
        this.bookingService.query().subscribe(
            (res: Response) => { this.bookings = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear () {
        this.activeModal.dismiss('cancel');
    }

    save () {
        this.isSaving = true;
        if (this.booking.id !== undefined) {
            this.bookingService.update(this.booking)
                .subscribe((res: Booking) => this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        } else {
            this.bookingService.create(this.booking)
                .subscribe((res: Booking) => this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        }
    }

    private onSaveSuccess (result: Booking) {
        this.eventManager.broadcast({ name: 'bookingListModification', content: 'OK'});
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

    trackAppUserById(index: number, item: AppUser) {
        return item.id;
    }

    trackAddressById(index: number, item: Address) {
        return item.id;
    }

    trackBookingById(index: number, item: Booking) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-booking-popup',
    template: ''
})
export class BookingPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private bookingPopupService: BookingPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            if ( params['id'] ) {
                this.modalRef = this.bookingPopupService
                    .open(BookingDialogComponent, params['id']);
            } else {
                this.modalRef = this.bookingPopupService
                    .open(BookingDialogComponent);
            }

        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
