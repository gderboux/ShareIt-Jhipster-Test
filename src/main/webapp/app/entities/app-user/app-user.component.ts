import { Component, OnInit, OnDestroy } from '@angular/core';
import { Response } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager, ParseLinks, PaginationUtil, AlertService, DataUtils } from 'ng-jhipster';

import { AppUser } from './app-user.model';
import { AppUserService } from './app-user.service';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-app-user',
    templateUrl: './app-user.component.html'
})
export class AppUserComponent implements OnInit, OnDestroy {
appUsers: AppUser[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private appUserService: AppUserService,
        private alertService: AlertService,
        private dataUtils: DataUtils,
        private eventManager: EventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.appUserService.query().subscribe(
            (res: Response) => {
                this.appUsers = res.json();
            },
            (res: Response) => this.onError(res.json())
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInAppUsers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId (index: number, item: AppUser) {
        return item.id;
    }



    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInAppUsers() {
        this.eventSubscriber = this.eventManager.subscribe('appUserListModification', (response) => this.loadAll());
    }


    private onError (error) {
        this.alertService.error(error.message, null, null);
    }
}
