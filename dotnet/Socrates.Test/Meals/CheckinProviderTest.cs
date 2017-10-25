using NFluent;
using NUnit.Framework;
using Socrates.CandidateRegistration;
using Socrates.Meals;
using System;

namespace Socrates.Test.Meals
{
    internal class CheckinProviderTest
    {
        [Test]
        public void GetCheckins_Should_Return_Zero_When_Zero_Checkin()
        {
            // SETUP
            var checkinProvider = new CheckinProvider();

            // RUN
            var checkins = checkinProvider.GetCheckins();

            // ASSERT
            Check.That(checkins.CountCheckins()).IsZero();
        }

        [Test]
        public void GetCheckins_Should_Return_Zero_When_1_Checkin_Already_Exists()
        {
            // SETUP
            var participantCheckinDate = new DateTime(2017, 10, 27, 16, 30, 00);
            var participantCheckin = new Checkin(Email.Of("Ismail@lcdlv.fr"), participantCheckinDate);
            var checkinProvider = new CheckinProvider(participantCheckin);

            // RUN
            Checkins countLateCheckin = checkinProvider.GetCheckins();
            
            // ASSERT
            Check.That(countLateCheckin.CountCheckins()).IsEqualTo(1);
        }
    }
}
