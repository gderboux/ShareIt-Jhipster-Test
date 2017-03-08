import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, DataUtils } from 'ng-jhipster';

import { AppUser } from './app-user.model';
import { AppUserPopupService } from './app-user-popup.service';
import { AppUserService } from './app-user.service';
import { Car, CarService } from '../car';
import { Address, AddressService } from '../address';
import { User, UserService } from '../../shared';
@Component({
    selector: 'jhi-app-user-dialog',
    templateUrl: './app-user-dialog.component.html'
})
export class AppUserDialogComponent implements OnInit {

    appUser: AppUser;
    authorities: any[];
    isSaving: boolean;

    cars: Car[];

    addresses: Address[];

    users: User[];
    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: DataUtils,
        private alertService: AlertService,
        private appUserService: AppUserService,
        private carService: CarService,
        private addressService: AddressService,
        private userService: UserService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.carService.query({filter: 'appuser-is-null'}).subscribe((res: Response) => {
            if (!this.appUser.carId) {
                this.cars = res.json();
            } else {
                this.carService.find(this.appUser.carId).subscribe((subRes: Car) => {
                    this.cars = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
        this.addressService.query({filter: 'appuser-is-null'}).subscribe((res: Response) => {
            if (!this.appUser.addressId) {
                this.addresses = res.json();
            } else {
                this.addressService.find(this.appUser.addressId).subscribe((subRes: Address) => {
                    this.addresses = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
        this.userService.query().subscribe(
            (res: Response) => { this.users = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData($event, appUser, field, isImage) {
        if ($event.target.files && $event.target.files[0]) {
            let $file = $event.target.files[0];
            if (isImage && !/^image\//.test($file.type)) {
                return;
            }
            this.dataUtils.toBase64($file, (base64Data) => {
                appUser[field] = base64Data;
                appUser[`${field}ContentType`] = $file.type;
            });
        }
    }
    clear () {
        this.activeModal.dismiss('cancel');
    }

    save () {
        this.isSaving = true;
        if (this.appUser.id !== undefined) {
            this.appUserService.update(this.appUser)
                .subscribe((res: AppUser) => this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        } else {
            this.appUserService.create(this.appUser)
                .subscribe((res: AppUser) => this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        }
    }

    private onSaveSuccess (result: AppUser) {
        this.eventManager.broadcast({ name: 'appUserListModification', content: 'OK'});
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

    trackCarById(index: number, item: Car) {
        return item.id;
    }

    trackAddressById(index: number, item: Address) {
        return item.id;
    }

    trackUserById(index: number, item: User) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-app-user-popup',
    template: ''
})
export class AppUserPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private appUserPopupService: AppUserPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            if ( params['id'] ) {
                this.modalRef = this.appUserPopupService
                    .open(AppUserDialogComponent, params['id']);
            } else {
                this.modalRef = this.appUserPopupService
                    .open(AppUserDialogComponent);
            }

        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
