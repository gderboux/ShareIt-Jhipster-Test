import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { AppUserComponent } from './app-user.component';
import { AppUserDetailComponent } from './app-user-detail.component';
import { AppUserPopupComponent } from './app-user-dialog.component';
import { AppUserDeletePopupComponent } from './app-user-delete-dialog.component';

import { Principal } from '../../shared';


export const appUserRoute: Routes = [
  {
    path: 'app-user',
    component: AppUserComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'AppUsers'
    }
  }, {
    path: 'app-user/:id',
    component: AppUserDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'AppUsers'
    }
  }
];

export const appUserPopupRoute: Routes = [
  {
    path: 'app-user-new',
    component: AppUserPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'AppUsers'
    },
    outlet: 'popup'
  },
  {
    path: 'app-user/:id/edit',
    component: AppUserPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'AppUsers'
    },
    outlet: 'popup'
  },
  {
    path: 'app-user/:id/delete',
    component: AppUserDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'AppUsers'
    },
    outlet: 'popup'
  }
];
