﻿
using NFluent;
using NUnit.Framework;
using System;

namespace Socrates.Test.Meals
{
    internal class ColdMealsCounterAcceptanceTest
    {

        [Test]
        public void CountColdMeals_Should_Return_Zero_Cold_Meals_When_Zero_Checkin_Of_Participant()
        {
            var coldMealsCounter = new ColdMealsCounter();
            var coldMealsNumber = coldMealsCounter.CountColdMeals();

            Check.That(coldMealsNumber).IsZero();
        }

        [Test]
        public void CountColdMeals_Should_Return_Zero_Cold_Meals_When_Participants_Checkin_Before_21h()
        {
            var participantCheckinDate = DateTime.ParseExact("27/10/2017 20:50:00", "dd/MM/yyyy HH:mm:ss", System.Globalization.CultureInfo.InvariantCulture);
            var participant = new Participant("regis.dubois@socrates.com", participantCheckinDate);

            var coldMealsCounter = new ColdMealsCounter(participant);
            var coldMealsNumber = coldMealsCounter.CountColdMeals();

            Check.That(coldMealsNumber).IsZero();
        }
        /*
        [Test]
        public void CountColdMeals_Should_Return_One_Cold_Meal_When_Participants_Checkin_After_21h()
        {
            var participantCheckinDate = DateTime.ParseExact("27/10/2017 21:50:00", "dd/MM/yyyy HH:mm:ss", System.Globalization.CultureInfo.InvariantCulture);
            var participant = new Participant("regis.dubois@socrates.com", participantCheckinDate);

            var coldMealsCounter = new ColdMealsCounter(participant);
            var coldMealsNumber = coldMealsCounter.CountColdMeals();

            Check.That(coldMealsNumber).IsEqualTo(1);
        }
        */
    }

   
}