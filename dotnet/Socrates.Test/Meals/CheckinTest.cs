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
        public void IsLate_Should_Return_True_When_Checkin_Date_Is_After_Given_Date()
        {
            // SETUP
            var participantCheckinDate = new DateTime(2017, 10, 27, 22, 30, 00);
            var participantCheckin = new Checkin(Email.Of("regis.dubois@socrates.com"), participantCheckinDate);

            // RUN
            var isLate = participantCheckin.IsAfter(new DateTime(2017, 10, 27, 21, 30, 00));

            // ASSERT
            Check.That(isLate).IsTrue();
        }

        [Test]
        public void IsLate_Should_Return_True_When_Checkin_Date_Is_Before_Given_Date()
        {
            // SETUP
            var participantCheckinDate = new DateTime(2017, 10, 27, 20, 30, 00);
            var participantCheckin = new Checkin(Email.Of("regis.dubois@socrates.com"), participantCheckinDate);

            // RUN
            var isLate = participantCheckin.IsAfter(new DateTime(2017, 10, 27, 21, 30, 00));

            // ASSERT
            Check.That(isLate).IsFalse();
        }
    }
}
