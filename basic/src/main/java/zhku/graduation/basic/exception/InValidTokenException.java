package zhku.graduation.basic.exception;

/**
 * @author qr
 * @date 2022/1/11 23:14
 */
public class InValidTokenException extends RuntimeException{

    public InValidTokenException() {
    }

    public InValidTokenException(String message) {
        super(message);
    }

}
