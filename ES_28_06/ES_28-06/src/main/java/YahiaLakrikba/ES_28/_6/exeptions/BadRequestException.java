package YahiaLakrikba.ES_28._6.exeptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException() {}
    public BadRequestException(String message) {
        super(message);
    }
}
