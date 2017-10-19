package com.lacombe.promo3.communication;

import com.lacombe.promo3.communication.repository.DefaultLogger;
import com.lacombe.promo3.registration.model.Email;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class LoggerTest {

    private static final Email CYRIL_EMAIL_ADDRESS = Email.of("cyril@lcdlv.fr");
    private static final Email ISMAEL_EMAIL_ADDRESS = Email.of("ismael@lcdlv.fr");

    @Test
    public void should_find_one_message_sent_in_the_log() {
        //GIVEN
        DefaultLogger defaultLogger = new DefaultLogger();

        //WHEN
        defaultLogger.log(CYRIL_EMAIL_ADDRESS);

        //THEN
        Assertions.assertThat(defaultLogger.print()).isEqualTo("An email was sent to Email{email='cyril@lcdlv.fr'}");
    }

    @Test
    public void should_find_two_messages_sent_in_the_log() throws Exception {
        //GIVEN
        DefaultLogger defaultLogger = new DefaultLogger();

        //WHEN
        defaultLogger.log(CYRIL_EMAIL_ADDRESS);
        defaultLogger.log(ISMAEL_EMAIL_ADDRESS);

        //THEN
        Assertions.assertThat(defaultLogger.print())
            .contains("An email was sent to Email{email='cyril@lcdlv.fr'}")
            .contains("An email was sent to Email{email='ismael@lcdlv.fr'}");
    }

    @Test
    public void should_find_two_messages_well_formatted() throws Exception {
        //GIVEN
        final DefaultLogger defaultLogger = new DefaultLogger();

        //WHEN
        defaultLogger.log(CYRIL_EMAIL_ADDRESS);
        defaultLogger.log(ISMAEL_EMAIL_ADDRESS);

        //THEN
        Assertions.assertThat(defaultLogger.print())
            .isEqualTo("An email was sent to Email{email='cyril@lcdlv.fr'}\n" +
                "An email was sent to Email{email='ismael@lcdlv.fr'}");

    }
}
