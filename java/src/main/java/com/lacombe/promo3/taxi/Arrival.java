package com.lacombe.promo3.taxi;

public class Arrival {
    private final ArrivalHour hour;
    private final Participant participant;

    public Arrival(ArrivalHour hour, Participant participant) {
        this.hour = hour;
        this.participant = participant;
    }

    public ArrivalHour getHour() {
        return hour;
    }


    public String getParticipantName() {
        return participant.getName();
    }
}
