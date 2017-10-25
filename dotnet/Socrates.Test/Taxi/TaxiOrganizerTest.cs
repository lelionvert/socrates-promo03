using NFluent;
using NSubstitute;
using NUnit.Framework;
using Socrates.Meals;
using Socrates.Taxi;
using System;

namespace Socrates.Test.Taxi
{
    class TaxiOrganizerTest
    {
        private ITaxiProvider TaxiProvider;
        private ICheckinProvider CheckinProvider;

        [SetUp]
        public void InitTest()
        {
            TaxiProvider = Substitute.For<ITaxiProvider>();
            CheckinProvider = Substitute.For<ICheckinProvider>();
        }

        [Test]
        public void GetBookings_should_call_getRegistrationBook_once()
        {
            // GIVEN
            TaxiOrganizer taxiOrganizer = new TaxiOrganizer(TaxiProvider, CheckinProvider);
            Period bookingPeriod = new Period(new DateTime(2017, 10, 27, 12, 00, 00), new DateTime(2017, 10, 27, 12, 30, 00));

            // WHEN
            taxiOrganizer.GetBookings();

            // THEN
            Check.That(CheckinProvider.Received(1).GetCheckins());
        }

        [Test]
        public void GetBookings_should_not_found_bookings_when_no_checkin()
        {
            // GIVEN
            TaxiOrganizer taxiOrganizer = new TaxiOrganizer(TaxiProvider, CheckinProvider);
            Period bookingPeriod = new Period(new DateTime(2017, 10, 27, 12, 00, 00), new DateTime(2017, 10, 27, 12, 30, 00));

            //WHEN
            TaxiBookingsResult taxiBookingsResult = taxiOrganizer.GetBookings();

            //THEN
            Check.That(taxiBookingsResult).IsEqualTo(TaxiBookingsResult.NotFound());
        }
    }
}

