import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ShareItV2AddressModule } from './address/address.module';
import { ShareItV2AppUserModule } from './app-user/app-user.module';
import { ShareItV2BookingModule } from './booking/booking.module';
import { ShareItV2CarModule } from './car/car.module';
import { ShareItV2FeedbackModule } from './feedback/feedback.module';
import { ShareItV2FrequencyModule } from './frequency/frequency.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        ShareItV2AddressModule,
        ShareItV2AppUserModule,
        ShareItV2BookingModule,
        ShareItV2CarModule,
        ShareItV2FeedbackModule,
        ShareItV2FrequencyModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ShareItV2EntityModule {}
