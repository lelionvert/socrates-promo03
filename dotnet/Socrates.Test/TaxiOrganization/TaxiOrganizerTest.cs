using NFluent;
using NUnit.Framework;
using Socrates.TaxiOrganization;
using System.Collections.Generic;

namespace Socrates.Test.TaxiOrganization
{
    class TaxiOrganizerTest
    {
        [Test]
        public void Should_book_one_taxi_for_one_arrival_on_12h()
        {
            //GIVEN
            var arrivals = new Arrivals();
            arrivals.Add(new Arrival(new ArrivalHour(12), new Participant("Thierry de Pauw")));

            //WHEN
            var booking = new TaxiOrganizer(arrivals).GetBookings();

            //THEN
            var expectedPassengers = new List<Passenger>
            {
                new Passenger("Thierry de Pauw")
            };

            Check.That(booking).IsEqualTo(new TaxiBooking(new Taxi("Taxi_12h"), "12", expectedPassengers));
        }

        [Test]
        public void Should_book_one_taxi_for_two_arrival_on_15h()
        {
            //GIVEN
            var arrivalHour = new ArrivalHour(15);
            var arrivals = new Arrivals();
            arrivals.Add(new Arrival(arrivalHour, new Participant("Thierry de Pauw")));
            arrivals.Add(new Arrival(arrivalHour, new Participant("Houssam")));

            //WHEN
            var booking = new TaxiOrganizer(arrivals).GetBookings();

            //THEN
            var passengersExpected = new List<Passenger>()
            {
                new Passenger("Thierry de Pauw"),
                new Passenger("Houssam")
            };

            Check.That(booking).IsEqualTo(new TaxiBooking(new Taxi("Taxi_15h"), "15", passengersExpected));
        }
    }
}

