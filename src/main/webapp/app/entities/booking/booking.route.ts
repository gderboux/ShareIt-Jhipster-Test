import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { BookingComponent } from './booking.component';
import { BookingDetailComponent } from './booking-detail.component';
import { BookingPopupComponent } from './booking-dialog.component';
import { BookingDeletePopupComponent } from './booking-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class BookingResolvePagingParams implements Resolve<any> {

  constructor(private paginationUtil: PaginationUtil) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
      let page = route.queryParams['page'] ? route.queryParams['page'] : '1';
      let sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
      return {
          page: this.paginationUtil.parsePage(page),
          predicate: this.paginationUtil.parsePredicate(sort),
          ascending: this.paginationUtil.parseAscending(sort)
    };
  }
}

export const bookingRoute: Routes = [
  {
    path: 'booking',
    component: BookingComponent,
    resolve: {
      'pagingParams': BookingResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Bookings'
    }
  }, {
    path: 'booking/:id',
    component: BookingDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Bookings'
    }
  }
];

export const bookingPopupRoute: Routes = [
  {
    path: 'booking-new',
    component: BookingPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Bookings'
    },
    outlet: 'popup'
  },
  {
    path: 'booking/:id/edit',
    component: BookingPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Bookings'
    },
    outlet: 'popup'
  },
  {
    path: 'booking/:id/delete',
    component: BookingDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Bookings'
    },
    outlet: 'popup'
  }
];
