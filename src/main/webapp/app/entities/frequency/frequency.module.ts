import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShareItV2SharedModule } from '../../shared';

import {
    FrequencyService,
    FrequencyPopupService,
    FrequencyComponent,
    FrequencyDetailComponent,
    FrequencyDialogComponent,
    FrequencyPopupComponent,
    FrequencyDeletePopupComponent,
    FrequencyDeleteDialogComponent,
    frequencyRoute,
    frequencyPopupRoute,
} from './';

let ENTITY_STATES = [
    ...frequencyRoute,
    ...frequencyPopupRoute,
];

@NgModule({
    imports: [
        ShareItV2SharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        FrequencyComponent,
        FrequencyDetailComponent,
        FrequencyDialogComponent,
        FrequencyDeleteDialogComponent,
        FrequencyPopupComponent,
        FrequencyDeletePopupComponent,
    ],
    entryComponents: [
        FrequencyComponent,
        FrequencyDialogComponent,
        FrequencyPopupComponent,
        FrequencyDeleteDialogComponent,
        FrequencyDeletePopupComponent,
    ],
    providers: [
        FrequencyService,
        FrequencyPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ShareItV2FrequencyModule {}
