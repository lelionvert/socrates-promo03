package com.lacombe.promo3.communication;

import com.lacombe.promo3.registration.model.Email;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DefaultLogger implements Logger{

    public String print() {
        throw new NotImplementedException();
    }

    @Override
    public void log(Email emailAddress) {

    }
}
