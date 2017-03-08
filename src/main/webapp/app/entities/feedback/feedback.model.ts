export class Feedback {
    constructor(
        public id?: number,
        public comment?: string,
        public visible?: boolean,
        public rank?: number,
        public bookingId?: number,
        public userId?: number,
        public reporterId?: number,
    ) {
        this.visible = false; 
    }
}
