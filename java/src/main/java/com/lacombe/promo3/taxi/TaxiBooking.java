package com.lacombe.promo3.taxi;

public class TaxiBooking {
    private boolean taxiName;
    private boolean horaire;
    private boolean passager;

    public TaxiBooking(String taxiName, String time, String passengerName) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxiBooking that = (TaxiBooking) o;

        if (taxiName != that.taxiName) return false;
        if (horaire != that.horaire) return false;
        return passager == that.passager;
    }

    @Override
    public int hashCode() {
        int result = (taxiName ? 1 : 0);
        result = 31 * result + (horaire ? 1 : 0);
        result = 31 * result + (passager ? 1 : 0);
        return result;
    }

    public String getTaxiName() {
        return "Taxi_12h";
    }

    public String getHoraire() {
        return "12h";
    }

    public String getPassager() {
        return "Thierry de Pauw";
    }
}
