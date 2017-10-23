using NFluent;
using NUnit.Framework;
using Socrates.CandidateRegistration;
using Socrates.Meals;
using System;
using System.Collections.Generic;

namespace Socrates.Test.TaxisOrganization
{
    public class TaxisOrganizerAcceptancesTest
    {
        [Test]
        public void GetNeededTaxisBy_Should_Return_No_Taxis_When_No_Checkin()
        {
            // GIVEN 
            var taxisOrganizer = new TaxisOrganizer(new CheckinProvider(),new TaxiProvider());
            DateTime beginDate = new DateTime(2017, 10, 27, 12, 00, 00);
            DateTime endDate = new DateTime(2017, 10, 27, 12, 30, 00);
            var taxiPeriod = new Period(beginDate, endDate);

            // WHEN
            Taxis availableTaxis = taxisOrganizer.GetNeededTaxisBy(taxiPeriod);

            // THEN
            Check.That(availableTaxis.IsEmpty()).IsTrue();
        }

        [Test]
        public void GetNeededTaxisBy_Should_Return_1_Taxi_With_4_Seats_When_3_Checkins()
        {
            // GIVEN
            DateTime beginDate = new DateTime(2017, 10, 27, 12, 00, 00);
            DateTime endDate = new DateTime(2017, 10, 27, 12, 30, 00);
            var taxiPeriod = new Period(beginDate, endDate);

            var firstCheckin = new Checkin(Email.Of("regis.dubois@socrates.fr"), new DateTime(2017, 10, 27, 12, 05, 00));
            var secondCheckin = new Checkin(Email.Of("fanny.dubois@socrates.fr"), new DateTime(2017, 10, 27, 12, 25, 00));
            var thirdCheckin = new Checkin(Email.Of("thomas.dubois@socrates.fr"), new DateTime(2017, 10, 27, 12, 15, 00));

            var checkinProvider = new CheckinProvider(firstCheckin, secondCheckin, thirdCheckin);

            var taxiWith4Seat = Taxi.WithSeat(4);
            var taxiProvider = new TaxiProvider(taxiWith4Seat);

            var taxisOrganizer = new TaxisOrganizer(checkinProvider, taxiProvider);

            // WHEN
            Taxis availableTaxis = taxisOrganizer.GetNeededTaxisBy(taxiPeriod);
            
            // THEN
            Check.That(availableTaxis.Contains(Taxi.WithSeat(4))).IsTrue();
        }


        [Test]
        public void GetNeededTaxisBy_Should_Return_1_Taxis_With_6_Seats_When_5_Checkin()
        {
            // GIVEN
            DateTime beginDate = new DateTime(2017, 10, 27, 12, 00, 00);
            DateTime endDate = new DateTime(2017, 10, 27, 12, 30, 00);
            var taxiPeriod = new Period(beginDate, endDate);

            var firstCheckin = new Checkin(Email.Of("regis.dubois@socrates.fr"), new DateTime(2017, 10, 27, 12, 05, 00));
            var secondCheckin = new Checkin(Email.Of("fanny.dubois@socrates.fr"), new DateTime(2017, 10, 27, 12, 25, 00));
            var thirdCheckin = new Checkin(Email.Of("thomas.dubois@socrates.fr"), new DateTime(2017, 10, 27, 12, 15, 00));
            var fourthCheckin = new Checkin(Email.Of("mathieu.dubois@socrates.fr"), new DateTime(2017, 10, 27, 12, 15, 00));
            var fifthCheckin = new Checkin(Email.Of("denis.dubois@socrates.fr"), new DateTime(2017, 10, 27, 12, 10, 00));

            var checkinProvider = new CheckinProvider(firstCheckin, secondCheckin, thirdCheckin,fourthCheckin,fifthCheckin);

            var taxisOrganizer = new TaxisOrganizer(checkinProvider,new TaxiProvider(Taxi.WithSeat(6)));
            // WHEN
            Taxis availableTaxis = taxisOrganizer.GetNeededTaxisBy(taxiPeriod);

            // THEN
            Check.That(availableTaxis.Contains(Taxi.WithSeat(6))).IsTrue();

        }
    }
}
