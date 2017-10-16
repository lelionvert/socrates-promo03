package com.lacombe.promo3.registration;

import com.lacombe.promo3.EmailSender;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class EmailSenderWithLogger implements EmailSender{

    public String printLog() {
        throw new NotImplementedException();
    }

    @Override
    public boolean send() {
        return false;
    }
}
