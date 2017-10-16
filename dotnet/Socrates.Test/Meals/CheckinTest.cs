using NFluent;
using NUnit.Framework;
using Socrates.CandidateRegistration;
using Socrates.Meals;
using System;

namespace Socrates.Test.Meals
{
    internal class CheckinTest
    {
        [Test]
        public void IsLate_Should_Return_True_When_Checkin_Date_Is_After_Tuesday_21h()
        {
            // SETUP
            var participantCheckinDate = new DateTime(2017, 10, 27, 21, 30, 00);
            var participantCheckin = new Checkin(Email.Of("regis.dubois@socrates.com"), participantCheckinDate);

            // RUN
            var isLate = participantCheckin.IsLate();

            // ASSERT
            Check.That(isLate).IsTrue();
        }

        [Test]
        public void IsLate_Should_Return_True_When_Checkin_Date_Is_Before_Tuesday_21h()
        {
            // SETUP
            var participantCheckinDate = new DateTime(2017, 10, 27, 20, 30, 00);
            var participantCheckin = new Checkin(Email.Of("regis.dubois@socrates.com"), participantCheckinDate);

            // RUN
            var isLate = participantCheckin.IsLate();

            // ASSERT
            Check.That(isLate).IsFalse();
        }
    }
}
