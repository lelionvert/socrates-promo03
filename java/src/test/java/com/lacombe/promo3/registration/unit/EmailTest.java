package com.lacombe.promo3.registration.unit;

import com.lacombe.promo3.shared.model.Email;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class EmailTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_thrown_exception_when_creating_an_empty_email() throws Exception {
        Email.of("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_thrown_exception_when_creating_a_null_email() throws Exception {
        Email.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_thrown_exception_when_creating_a_not_valid_email() throws Exception {
        Email.of("emailNotValid.ok");
    }

    @Test
    public void should_create_email_when_email_valid() {
        Email valentinEmail = Email.of("valentin@lcdlv.fr");
        Assertions.assertThat(valentinEmail).isNotNull();
    }


}
