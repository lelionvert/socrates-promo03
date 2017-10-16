using NFluent;
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
            var coldMealsCounter = new ColdMealsCounter();
            var coldMealsNumber = coldMealsCounter.CountColdMeals();
            Check.That(coldMealsNumber).IsZero();
        }


        [TestCase(2017, 10, 27, 20, 30, 00)]
        public void CountColdMeals_Should_Return_Zero_Cold_Meals_When_Participants_Checkin_Before_21h(int year, int month,int day,int hour,int minute,int second)
        {
           var participantCheckinDate =  new DateTime(year, month, day, hour, minute, second);
           var checkin = new Checkin(Email.Of("gabriel@lcdlv.fr"), participantCheckinDate);
           var checkinProvider = new CheckinProvider(checkin);
           Check.That(checkinProvider.CountLateCheckin()).IsZero();
        }
    }
}
