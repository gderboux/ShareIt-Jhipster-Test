import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { Frequency } from './frequency.model';
import { FrequencyPopupService } from './frequency-popup.service';
import { FrequencyService } from './frequency.service';

@Component({
    selector: 'jhi-frequency-delete-dialog',
    templateUrl: './frequency-delete-dialog.component.html'
})
export class FrequencyDeleteDialogComponent {

    frequency: Frequency;

    constructor(
        private frequencyService: FrequencyService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear () {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete (id: number) {
        this.frequencyService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'frequencyListModification',
                content: 'Deleted an frequency'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-frequency-delete-popup',
    template: ''
})
export class FrequencyDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private frequencyPopupService: FrequencyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            this.modalRef = this.frequencyPopupService
                .open(FrequencyDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
