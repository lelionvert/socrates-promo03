using NFluent;
using NSubstitute;
using NUnit.Framework;

namespace Socrates.Test.Meals
{
    internal class ColdMealsCounterTest
    {
        [Test]
        public void CountColdMeals_Should_Return_Zero_Cold_Meal_When_Zero_Participant()
        {
            // SETUP
            var checkinProvider = Substitute.For<ICheckinProvider>();
            checkinProvider.CountLateCheckin().Returns(0);

            // RUN
            var coldMealsCounter = new ColdMealsCounter(checkinProvider);
            var coldMealsNumber = coldMealsCounter.CountColdMeals();

            // ASSERT
            Check.That(coldMealsNumber).IsZero();
        }

        [Test]
        public void CountColdMeals_Should_Return_Zero_Cold_Meals_When_Participants_Checkin_Before_21h()
        {
            // SETUP
            var checkinProvider = Substitute.For<ICheckinProvider>();
            checkinProvider.CountLateCheckin().Returns(0);

            // RUN
            var coldMealsCounter = new ColdMealsCounter(checkinProvider);

            // ASSERT
            Check.That(coldMealsCounter.CountColdMeals()).IsZero();
        }

        [TestCase(1)]
        [TestCase(2)]
        public void CountColdMeals_Should_Return_One_Cold_Meal_When_Participants_Checkin_After_21h(int numberLateCheckin)
        {
            // SETUP
            var checkinProvider = Substitute.For<ICheckinProvider>();
            checkinProvider.CountLateCheckin().Returns(numberLateCheckin);
            var coldMealCounter = new ColdMealsCounter(checkinProvider);

            // RUN
            var coldMealsNumber = coldMealCounter.CountColdMeals();

            // ASSERT
            Check.That(coldMealsNumber).IsEqualTo(numberLateCheckin);
        }
    }
}
