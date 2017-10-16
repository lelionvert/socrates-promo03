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
        public void CountLateCheckin_Should_Return_Zero_When_Zero_Checkin()
        {
            // SETUP
            var checkinProvider = new CheckinProvider();

            // RUN
            var countLateCheckin = checkinProvider.CountLateCheckin();

            // ASSERT
            Check.That(countLateCheckin).IsZero();
        }

        [Test]
        public void CountLateCheckin_Should_Return_Zero_When_1_Checkin_Before_21()
        {
            // SETUP
            var participantCheckinDate = new DateTime(2017, 10, 27, 16, 30, 00);
            var participantCheckin = new Checkin(Email.Of("Ismail@lcdlv.fr"), participantCheckinDate);
            var checkinProvider = new CheckinProvider(participantCheckin);

            // RUN
            var countLateCheckin = checkinProvider.CountLateCheckin();

            // ASSERT
            Check.That(countLateCheckin).IsZero();
        }

        [Test]
        public void CountLateCheckin_Should_Return_1_When_1_Checkin_After_21h()
        {
            // SETUP
            var participantCheckinDate = new DateTime(2017, 10, 27, 21, 30, 00);
            var participantCheckin = new Checkin(Email.Of("Ismail@lcdlv.fr"), participantCheckinDate);
            var checkinProvider = new CheckinProvider(participantCheckin);

            // RUN
            var countLateCheckin = checkinProvider.CountLateCheckin();

            // ASSERT
            Check.That(countLateCheckin).IsEqualTo(1);
        }
    }
}
