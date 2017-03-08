
const enum Gender {
    'HOMME',
    'FEMME'

};
export class AppUser {
    constructor(
        public id?: number,
        public gender?: Gender,
        public phoneNumber?: string,
        public avatar?: any,
        public licence?: any,
        public isAuthorizedDriver?: boolean,
        public carId?: number,
        public addressId?: number,
        public jhiUserId?: number,
    ) {
        this.isAuthorizedDriver = false; 
    }
}
