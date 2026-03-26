export class ConstantesRest {
    public readonly API_URL = 'http://localhost:8080/travels-rest-api/';

    public getApiURL (): string {
        return this.API_URL;
    }
}