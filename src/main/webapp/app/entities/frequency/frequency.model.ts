export class Frequency {
    constructor(
        public id?: number,
        public monday?: boolean,
        public tuesday?: boolean,
        public wednesday?: boolean,
        public thursday?: boolean,
        public friday?: boolean,
        public saturday?: boolean,
        public sunday?: boolean,
        public bookingId?: number,
    ) {
        this.monday = false; 
        this.tuesday = false; 
        this.wednesday = false; 
        this.thursday = false; 
        this.friday = false; 
        this.saturday = false; 
        this.sunday = false; 
    }
}
