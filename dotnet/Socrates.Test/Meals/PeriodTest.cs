using NFluent;
using NUnit.Framework;
using Socrates.Meals;
using System;

namespace Socrates.Test.Meals
{
    class PeriodTest
    {

        [Test]
        public void Should_Verify_Date_Is_In_Period()
        {
            // SETUP
            var inRangeDate = new DateTime(2017,10,10,09,00,00);

            var startingDate = new DateTime(2017, 10, 01);
            var endingDate = new DateTime(2017, 10, 30);
            var period = new Period(startingDate,endingDate);

            // RUN
            var isSpecificDateContainedInPeriod = period.Contains(inRangeDate);

            // ASSERT
            Check.That(isSpecificDateContainedInPeriod).IsTrue();
        }

        [Test]
        public void Should_Verify_Date_Is_Not_In_Period()
        {
            // SETUP
            var notInRangeDate = new DateTime(2018, 05, 21, 10, 00, 00);

            var startingDate = new DateTime(2017, 10, 01);
            var endingDate = new DateTime(2017, 10, 30);
            var period = new Period(startingDate, endingDate);

            // RUN
            var isSpecificDateContainedInPeriod = period.Contains(notInRangeDate);

            // ASSERT
            Check.That(isSpecificDateContainedInPeriod).IsFalse();
        }
    }
}
