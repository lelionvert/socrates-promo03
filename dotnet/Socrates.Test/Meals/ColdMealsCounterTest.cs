using NFluent;
using NUnit.Framework;
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
            var coldMealsCounter = new ColdMealsCounter();
            var coldMealsNumber = coldMealsCounter.CountColdMeals();
            Check.That(coldMealsNumber).IsZero();
        }
    }
}
