import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { AppUser } from './app-user.model';
import { AppUserPopupService } from './app-user-popup.service';
import { AppUserService } from './app-user.service';

@Component({
    selector: 'jhi-app-user-delete-dialog',
    templateUrl: './app-user-delete-dialog.component.html'
})
export class AppUserDeleteDialogComponent {

    appUser: AppUser;

    constructor(
        private appUserService: AppUserService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear () {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete (id: number) {
        this.appUserService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'appUserListModification',
                content: 'Deleted an appUser'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-app-user-delete-popup',
    template: ''
})
export class AppUserDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private appUserPopupService: AppUserPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            this.modalRef = this.appUserPopupService
                .open(AppUserDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
