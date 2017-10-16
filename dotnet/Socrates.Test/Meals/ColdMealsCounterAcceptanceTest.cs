using NFluent;
using NUnit.Framework;
using Socrates.CandidateRegistration;
using System;

namespace Socrates.Test.Meals
{
    internal class ColdMealsCounterAcceptanceTest
    {

        [Test]
        public void CountColdMeals_Should_Return_Zero_Cold_Meals_When_Zero_Checkin_Of_Participant()
        {
            var checkinProvider = new CheckinProvider();
            var coldMealsCounter = new ColdMealsCounter(checkinProvider);
            var coldMealsNumber = coldMealsCounter.CountColdMeals();

            Check.That(coldMealsNumber).IsZero();
        }

        [Test]
        public void CountColdMeals_Should_Return_Zero_Cold_Meals_When_Participants_Checkin_Before_21h()
        {
            var participantCheckinDate = new DateTime(2017, 10, 27, 16, 30, 00);
            var checkin = new Checkin(Email.Of("regis.dubois@socrates.com"), participantCheckinDate);

            var checkinProvider = new CheckinProvider(checkin);
            
            var coldMealsCounter = new ColdMealsCounter(checkinProvider);
            var coldMealsNumber = coldMealsCounter.CountColdMeals();

            Check.That(coldMealsNumber).IsZero();
        }

        [Test]
        public void CountColdMeals_Should_Return_One_Cold_Meal_When_Participants_Checkin_After_21h()
        {
            var participantCheckinDate = new DateTime(2017, 10, 27, 21, 30, 00);
            var checkin = new Checkin(Email.Of("regis.dubois@socrates.com"), participantCheckinDate);
            var checkinProvider = new CheckinProvider(checkin);
            var coldMealsCounter = new ColdMealsCounter(checkinProvider);
            var coldMealsNumber = coldMealsCounter.CountColdMeals();
            Check.That(coldMealsNumber).IsEqualTo(1);

        }


    }


}
