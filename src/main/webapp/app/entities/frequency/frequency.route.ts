import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { FrequencyComponent } from './frequency.component';
import { FrequencyDetailComponent } from './frequency-detail.component';
import { FrequencyPopupComponent } from './frequency-dialog.component';
import { FrequencyDeletePopupComponent } from './frequency-delete-dialog.component';

import { Principal } from '../../shared';


export const frequencyRoute: Routes = [
  {
    path: 'frequency',
    component: FrequencyComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Frequencies'
    }
  }, {
    path: 'frequency/:id',
    component: FrequencyDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Frequencies'
    }
  }
];

export const frequencyPopupRoute: Routes = [
  {
    path: 'frequency-new',
    component: FrequencyPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Frequencies'
    },
    outlet: 'popup'
  },
  {
    path: 'frequency/:id/edit',
    component: FrequencyPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Frequencies'
    },
    outlet: 'popup'
  },
  {
    path: 'frequency/:id/delete',
    component: FrequencyDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Frequencies'
    },
    outlet: 'popup'
  }
];
