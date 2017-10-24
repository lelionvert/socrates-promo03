package com.lacombe.promo3.taxi;

public abstract class TaxiBookingsResult {

    public static TaxiBookingsResult notFound() {
        return new NotFound();
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
