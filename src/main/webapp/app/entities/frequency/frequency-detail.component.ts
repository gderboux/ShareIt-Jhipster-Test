import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Frequency } from './frequency.model';
import { FrequencyService } from './frequency.service';

@Component({
    selector: 'jhi-frequency-detail',
    templateUrl: './frequency-detail.component.html'
})
export class FrequencyDetailComponent implements OnInit, OnDestroy {

    frequency: Frequency;
    private subscription: any;

    constructor(
        private frequencyService: FrequencyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe(params => {
            this.load(params['id']);
        });
    }

    load (id) {
        this.frequencyService.find(id).subscribe(frequency => {
            this.frequency = frequency;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

}
