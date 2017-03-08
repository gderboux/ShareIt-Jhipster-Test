import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShareItV2SharedModule } from '../../shared';
import { ShareItV2AdminModule } from '../../admin/admin.module';

import {
    AppUserService,
    AppUserPopupService,
    AppUserComponent,
    AppUserDetailComponent,
    AppUserDialogComponent,
    AppUserPopupComponent,
    AppUserDeletePopupComponent,
    AppUserDeleteDialogComponent,
    appUserRoute,
    appUserPopupRoute,
} from './';

let ENTITY_STATES = [
    ...appUserRoute,
    ...appUserPopupRoute,
];

@NgModule({
    imports: [
        ShareItV2SharedModule,
        ShareItV2AdminModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AppUserComponent,
        AppUserDetailComponent,
        AppUserDialogComponent,
        AppUserDeleteDialogComponent,
        AppUserPopupComponent,
        AppUserDeletePopupComponent,
    ],
    entryComponents: [
        AppUserComponent,
        AppUserDialogComponent,
        AppUserPopupComponent,
        AppUserDeleteDialogComponent,
        AppUserDeletePopupComponent,
    ],
    providers: [
        AppUserService,
        AppUserPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ShareItV2AppUserModule {}
