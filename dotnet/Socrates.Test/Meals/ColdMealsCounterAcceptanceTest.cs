using NFluent;
using NUnit.Framework;
using Socrates.CandidateRegistration;
using Socrates.Meals;
using System;

namespace Socrates.Test.Meals
{
    internal class ColdMealsCounterAcceptanceTest
    {
        [Test]
        public void CountColdMeals_Should_Return_Zero_Cold_Meals_When_Zero_Checkin_Of_Participant()
        {
            // SETUP
            var checkinProvider = new CheckinProvider();
            var coldMealsCounter = new ColdMealsCounter(checkinProvider);

            // RUN
            var coldMealsNumber = coldMealsCounter.CountColdMeals();

            // ASSERT
            Check.That(coldMealsNumber).IsZero();
        }

        [Test]
        public void CountColdMeals_Should_Return_Zero_Cold_Meals_When_Participants_Checkin_Before_21h()
        {
            // SETUP
            var participantCheckinDate = new DateTime(2017, 10, 27, 16, 30, 00);
            var checkin = new Checkin(Email.Of("regis.dubois@socrates.com"), participantCheckinDate);
            var checkinProvider = new CheckinProvider(checkin);
            var coldMealsCounter = new ColdMealsCounter(checkinProvider);

            // RUN
            var coldMealsNumber = coldMealsCounter.CountColdMeals();

            // ASSERT
            Check.That(coldMealsNumber).IsZero();
        }

        [Test]
        public void CountColdMeals_Should_Return_One_Cold_Meal_When_Participants_Checkin_After_21h()
        {
            // SETUP
            var participantCheckinDate = new DateTime(2017, 10, 27, 21, 30, 00);
            var checkin = new Checkin(Email.Of("regis.dubois@socrates.com"), participantCheckinDate);
            var checkinProvider = new CheckinProvider(checkin);
            var coldMealsCounter = new ColdMealsCounter(checkinProvider);

            // RUN
            var coldMealsNumber = coldMealsCounter.CountColdMeals();

            // ASSERT
            Check.That(coldMealsNumber).IsEqualTo(1);
        }
        [Test]
        public void CountColdMeals_Should_Return_Three_Cold_Meal_When_Participants_Checkin_After_21h()
        {
            // SETUP
            var firstParticipantCheckinDate = new DateTime(2017, 10, 27, 21, 30, 00);
            var secondParticipantCheckinDate = new DateTime(2017, 10, 27, 22, 30, 00);
            var thirdParticipantCheckinDate = new DateTime(2017, 10, 29, 09, 30, 00);

            var firstCheckin = new Checkin(Email.Of("regis.dubois@socrates.com"), firstParticipantCheckinDate);
            var secondCheckin = new Checkin(Email.Of("ismail.nguyen@socrates.com"), secondParticipantCheckinDate);
            var thirdCheckin = new Checkin(Email.Of("gabriel.zaafrani@socrates.com"), thirdParticipantCheckinDate);

            var checkinProvider = new CheckinProvider(firstCheckin,secondCheckin,thirdCheckin);
            var coldMealsCounter = new ColdMealsCounter(checkinProvider);

            // RUN
            var coldMealsNumber = coldMealsCounter.CountColdMeals();

            // ASSERT
            Check.That(coldMealsNumber).IsEqualTo(3);
        }
    }
}
