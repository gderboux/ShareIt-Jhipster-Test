import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { Booking } from './booking.model';
import { BookingService } from './booking.service';
@Injectable()
export class BookingPopupService {
    private isOpen = false;
    constructor (
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private bookingService: BookingService

    ) {}

    open (component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.bookingService.find(id).subscribe(booking => {
                booking.startDate = this.datePipe.transform(booking.startDate, 'yyyy-MM-ddThh:mm');
                booking.endDate = this.datePipe.transform(booking.endDate, 'yyyy-MM-ddThh:mm');
                this.bookingModalRef(component, booking);
            });
        } else {
            return this.bookingModalRef(component, new Booking());
        }
    }

    bookingModalRef(component: Component, booking: Booking): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.booking = booking;
        modalRef.result.then(result => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}
