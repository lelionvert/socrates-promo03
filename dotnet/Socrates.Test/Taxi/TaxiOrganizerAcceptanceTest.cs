using NFluent;
using NUnit.Framework;
using Socrates.Meals;
using Socrates.Taxi;

namespace Socrates.Test.Taxi
{
    public class TaxiOrganizerAcceptanceTest
    {
        [Test]
        public void Should_have_no_taxi_without_checkin()
        {
            //GIVEN
            ITaxiProvider taxiProvider = new InMemoryTaxiProvider();
            ICheckinProvider inMemoryCheckInProvider = new CheckinProvider();

            TaxiOrganizer taxiOrganizer = new TaxiOrganizer(taxiProvider, inMemoryCheckInProvider);

            //WHEN
            TaxiBookingsResult taxiBookingsResult = taxiOrganizer.GetBookings();

            //THEN
            Check.That(taxiBookingsResult).IsEqualTo(TaxiBookingsResult.NotFound());
        }

        [Test]
        public void Should_book_one_regular_taxi_for_period_with_three_checkins()
        {
            //GIVEN
            ITaxiProvider taxiProvider = new InMemoryTaxiProvider();
            ICheckinProvider inMemoryCheckInProvider = new CheckinProvider();

            TaxiOrganizer taxiOrganizer = new TaxiOrganizer(taxiProvider, inMemoryCheckInProvider);

            //WHEN
            TaxiBookingsResult taxiBookingsResult = taxiOrganizer.GetBookings();

            //THEN
            /*Taxi taxi = Taxi.asRegular();
            Collection<TaxiBooking> taxiBookings = Arrays.asList(new TaxiBooking());
            assertThat(taxiBookingsResult).isEqualTo(TaxiBookingsResult.found());*/
        }
    }
}
