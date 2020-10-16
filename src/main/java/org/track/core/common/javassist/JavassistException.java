package org.track.core.common.javassist;

/**
 * @author blindarcheology@hotmail.com
 * @version 1.0
 * @className JavassistException
 * @description javassist exception
 * @date 2020/10/16 9:38 PM
 **/
public class JavassistException extends RuntimeException {
    private static final long serialVersionUID = 566559500853488031L;

    public JavassistException(String message) {
        super(message);
    }
}
