export class Address {
    constructor(
        public id?: number,
        public streetNumber?: string,
        public streetName?: string,
        public city?: string,
        public zipCode?: string,
        public country?: string,
        public latitude?: number,
        public longitude?: number,
    ) {
    }
}
