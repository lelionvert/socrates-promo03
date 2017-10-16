using NFluent;
using NSubstitute;
using NUnit.Framework;
using Socrates.CandidateRegistration;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Socrates.Test.Meals
{
    internal class ColdMealsCounterTest
    {
        [Test]
        public void CountColdMeals_Should_Return_Zero_Cold_Meal_When_Zero_Participant()
        {
            var checkinProvider = Substitute.For<ICheckinProvider>();
            checkinProvider.CountLateCheckin().Returns(0);

            var coldMealsCounter = new ColdMealsCounter(checkinProvider);
            var coldMealsNumber = coldMealsCounter.CountColdMeals();

            Check.That(coldMealsNumber).IsZero();
        }

        [Test]
        public void CountColdMeals_Should_Return_Zero_Cold_Meals_When_Participants_Checkin_Before_21h()
        {
            var checkinProvider = Substitute.For<ICheckinProvider>();
            checkinProvider.CountLateCheckin().Returns(0);

            var coldMealsCounter = new ColdMealsCounter(checkinProvider);

            Check.That(coldMealsCounter.CountColdMeals()).IsZero();
        }

        [Test]
        public void CountColdMeals_Should_Return_One_Cold_Meal_When_Participants_Checkin_After_21h()
        {
            // SETUP
            var checkinProvider = Substitute.For<ICheckinProvider>();
            checkinProvider.CountLateCheckin().Returns(1);
            var coldMealCounter = new ColdMealsCounter(checkinProvider);

            // RUN
            var coldMealsNumber = coldMealCounter.CountColdMeals();

            // ASSERT
            Check.That(coldMealsNumber).IsEqualTo(1);
        }
    }
}
