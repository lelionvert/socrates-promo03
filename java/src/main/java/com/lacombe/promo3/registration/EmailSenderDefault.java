package com.lacombe.promo3.registration;

import com.lacombe.promo3.EmailSender;
import com.lacombe.promo3.registration.model.Candidate;
import com.lacombe.promo3.registration.model.Email;
import com.lacombe.promo3.registration.repository.DefaultCandidateRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class EmailSenderDefault implements EmailSender {

    @Override
    public boolean send() {
        return false;
    }
}
