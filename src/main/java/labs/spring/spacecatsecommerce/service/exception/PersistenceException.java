package labs.spring.spacecatsecommerce.service.exception;

public class PersistenceException extends RuntimeException{

    public PersistenceException(Throwable ex){
        super(ex); // кастомізоване повідомлення
    }

}
