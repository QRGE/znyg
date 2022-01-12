package zhku.graduation.basic.exception;

/**
 * @author qr
 * @date 2022/1/11 23:14
 */
public class NoJwtTokenException extends RuntimeException{

    public NoJwtTokenException(String message) {
        super(message);
    }

}
