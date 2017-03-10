import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataUtils } from 'ng-jhipster';
import { AppUser } from './app-user.model';
import { AppUserService } from './app-user.service';

@Component({
    selector: 'jhi-app-user-detail',
    templateUrl: './app-user-detail.component.html'
})
export class AppUserDetailComponent implements OnInit, OnDestroy {

    appUser: AppUser;
    private subscription: any;

    constructor(
        private dataUtils: DataUtils,
        private appUserService: AppUserService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe(params => {
            this.load(params['id']);
        });
    }

    load (id) {
        this.appUserService.find(id).subscribe(appUser => {
            this.appUser = appUser;
        });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

}
