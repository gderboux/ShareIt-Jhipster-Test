
const enum Color {
    'BLACK',
    'RED',
    'WHITE',
    'BLUE',
    'GREY',
    'GREEN',
    'YELLOW',
    'ORANGE'

};
export class Car {
    constructor(
        public id?: number,
        public vin?: string,
        public description?: string,
        public color?: Color,
        public numberOfSeat?: number,
        public carPicture?: any,
    ) {
    }
}
