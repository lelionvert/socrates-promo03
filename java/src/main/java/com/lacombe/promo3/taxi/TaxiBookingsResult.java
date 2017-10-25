package com.lacombe.promo3.taxi;

public abstract class TaxiBookingsResult {

    private String taxiName;
    private boolean horaire;
    private boolean passager;

    public static TaxiBookingsResult notFound() {
        return new NotFound();
    }

    public String getTaxiName() {
        return taxiName;
    }

    public boolean getHoraire() {
        return horaire;
    }

    public boolean getPassager() {
        return passager;
    }

    private static class NotFound extends TaxiBookingsResult{
        public NotFound() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return true;
        }

        @Override
        public int hashCode() {
            return NotFound.class.hashCode();
        }
    }


}
