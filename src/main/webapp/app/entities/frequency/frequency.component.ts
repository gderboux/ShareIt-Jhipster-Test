import { Component, OnInit, OnDestroy } from '@angular/core';
import { Response } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager, ParseLinks, PaginationUtil, AlertService } from 'ng-jhipster';

import { Frequency } from './frequency.model';
import { FrequencyService } from './frequency.service';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-frequency',
    templateUrl: './frequency.component.html'
})
export class FrequencyComponent implements OnInit, OnDestroy {
frequencies: Frequency[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private frequencyService: FrequencyService,
        private alertService: AlertService,
        private eventManager: EventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.frequencyService.query().subscribe(
            (res: Response) => {
                this.frequencies = res.json();
            },
            (res: Response) => this.onError(res.json())
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInFrequencies();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId (index: number, item: Frequency) {
        return item.id;
    }



    registerChangeInFrequencies() {
        this.eventSubscriber = this.eventManager.subscribe('frequencyListModification', (response) => this.loadAll());
    }


    private onError (error) {
        this.alertService.error(error.message, null, null);
    }
}
