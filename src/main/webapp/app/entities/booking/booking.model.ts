export class Booking {
    constructor(
        public id?: number,
        public startDate?: any,
        public endDate?: any,
        public costPerKilometer?: number,
        public driverId?: number,
        public ownerId?: number,
        public startAddressId?: number,
        public endAddressId?: number,
        public bookingId?: number,
        public passengerBookingsId?: number,
    ) {
    }
}
