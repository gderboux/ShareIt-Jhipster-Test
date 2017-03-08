import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShareItV2SharedModule } from '../../shared';

import {
    BookingService,
    BookingPopupService,
    BookingComponent,
    BookingDetailComponent,
    BookingDialogComponent,
    BookingPopupComponent,
    BookingDeletePopupComponent,
    BookingDeleteDialogComponent,
    bookingRoute,
    bookingPopupRoute,
    BookingResolvePagingParams,
} from './';

let ENTITY_STATES = [
    ...bookingRoute,
    ...bookingPopupRoute,
];

@NgModule({
    imports: [
        ShareItV2SharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        BookingComponent,
        BookingDetailComponent,
        BookingDialogComponent,
        BookingDeleteDialogComponent,
        BookingPopupComponent,
        BookingDeletePopupComponent,
    ],
    entryComponents: [
        BookingComponent,
        BookingDialogComponent,
        BookingPopupComponent,
        BookingDeleteDialogComponent,
        BookingDeletePopupComponent,
    ],
    providers: [
        BookingService,
        BookingPopupService,
        BookingResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ShareItV2BookingModule {}
