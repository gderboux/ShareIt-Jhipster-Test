import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShareItV2SharedModule } from '../../shared';

import {
    AddressService,
    AddressPopupService,
    AddressComponent,
    AddressDetailComponent,
    AddressDialogComponent,
    AddressPopupComponent,
    AddressDeletePopupComponent,
    AddressDeleteDialogComponent,
    addressRoute,
    addressPopupRoute,
} from './';

let ENTITY_STATES = [
    ...addressRoute,
    ...addressPopupRoute,
];

@NgModule({
    imports: [
        ShareItV2SharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AddressComponent,
        AddressDetailComponent,
        AddressDialogComponent,
        AddressDeleteDialogComponent,
        AddressPopupComponent,
        AddressDeletePopupComponent,
    ],
    entryComponents: [
        AddressComponent,
        AddressDialogComponent,
        AddressPopupComponent,
        AddressDeleteDialogComponent,
        AddressDeletePopupComponent,
    ],
    providers: [
        AddressService,
        AddressPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ShareItV2AddressModule {}
