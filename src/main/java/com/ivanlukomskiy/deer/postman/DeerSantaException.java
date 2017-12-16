package com.ivanlukomskiy.deer.postman;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 16.12.2017.
 */
public class DeerSantaException extends RuntimeException {

    public DeerSantaException(String key) {
        super(key);
    }
}
