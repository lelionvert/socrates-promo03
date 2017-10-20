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
        public void IsContainedBy_Should_Return_True_When_Checkin_Date_Is_Contained_In_Range()
        {
            // SETUP
            var participantCheckinDate = new DateTime(2017, 10, 10, 21, 30, 40);
            var participantCheckin = new Checkin(Email.Of("regis.dubois@socrates.com"), participantCheckinDate);

            var periodStartingDate = new DateTime(2017, 10, 09);
            var periodEndingDate = new DateTime(2017, 12, 02);

            var period = new Period(periodStartingDate, periodEndingDate);

            // RUN
            bool isCheckinDateContainedInPeriod = participantCheckin.IsContainedBy(period);

            // ASSERT
            Check.That(isCheckinDateContainedInPeriod).IsTrue();
        }

         [Test]
        public void IsContainedBy_Should_Return_True_When_Checkin_Date_Is_Not_Contained_In_Range()
        {
            // SETUP
            var participantCheckinDate = new DateTime(2017, 05, 10, 21, 30, 40);
            var participantCheckin = new Checkin(Email.Of("regis.dubois@socrates.com"), participantCheckinDate);

            var periodStartingDate = new DateTime(2017, 10, 09);
            var periodEndingDate = new DateTime(2017, 12, 02);

            var period = new Period(periodStartingDate, periodEndingDate);

            // RUN
            bool isCheckinDateContainedInPeriod = participantCheckin.IsContainedBy(period);

            // ASSERT
            Check.That(isCheckinDateContainedInPeriod).IsFalse();
        }
    }
}
