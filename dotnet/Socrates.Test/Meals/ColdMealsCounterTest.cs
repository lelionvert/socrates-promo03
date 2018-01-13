using NFluent;
using NSubstitute;
using NUnit.Framework;
using Socrates.CandidateRegistration;
using Socrates.Meals;
using System;
using System.Collections.Generic;

namespace Socrates.Test.Meals
{
    internal class ColdMealsCounterTest
    {
        [Test]
        public void CountColdMeals_Should_Return_Zero_Cold_Meal_When_Zero_Participant()
        {
            // SETUP
            var checkinProvider = Substitute.For<ICheckinProvider>();
            checkinProvider.GetCheckins().Returns(Checkins.FromList(new List<Checkin>()));

            // RUN
            var coldMealsCounter = new ColdMealsCounter(checkinProvider);
            var coldMealsNumber = coldMealsCounter.CountColdMeals();

            // ASSERT
            Check.That(coldMealsNumber).IsEqualTo(0);
        }

        [Test]
        public void CountColdMeals_Should_Return_Zero_Cold_Meals_When_Participants_Checkin_Before_21h()
        {
            // SETUP
            var checkins = new List<Checkin>();
            var firstParticipantCheckin = new Checkin(Email.Of("regis.dubois@socrates.com"), new DateTime(2018, 10, 27, 20, 59, 59));
            var secondParticipantCheckin = new Checkin(Email.Of("fanny.dubois@socrates.com"), new DateTime(2018, 10, 27, 17, 13, 00));
            checkins.Add(firstParticipantCheckin);
            checkins.Add(secondParticipantCheckin);

            var checkinProvider = Substitute.For<ICheckinProvider>();
            checkinProvider.GetCheckins().Returns(Checkins.FromList(checkins));

            // RUN
            var coldMealsCounter = new ColdMealsCounter(checkinProvider);
            var coldMealsNumber = coldMealsCounter.CountColdMeals();
            // ASSERT
            Check.That(coldMealsNumber).IsEqualTo(0);
        }

        [Test]
        public void CountColdMeals_Should_Return_One_Cold_Meal_When_Participants_Checkin_Thursday_After_21h()
        {
            // SETUP
            var checkins = new List<Checkin>();
            var firstParticipantCheckin = new Checkin(Email.Of("regis.dubois@socrates.com"), new DateTime(2018, 10, 27, 21, 59, 59));
            var secondParticipantCheckin = new Checkin(Email.Of("fanny.dubois@socrates.com"), new DateTime(2018, 10, 27, 20, 13, 00));
            var thirdParticipantCheckin = new Checkin(Email.Of("Nany.dubois@socrates.com"), new DateTime(2018, 10, 27, 21, 13, 00));

            checkins.Add(firstParticipantCheckin);
            checkins.Add(secondParticipantCheckin);
            checkins.Add(thirdParticipantCheckin);

            var checkinProvider = Substitute.For<ICheckinProvider>();
            checkinProvider.GetCheckins().Returns(Checkins.FromList(checkins));

            // RUN
            var coldMealsCounter = new ColdMealsCounter(checkinProvider);
            var coldMealsNumber = coldMealsCounter.CountColdMeals();
            // ASSERT
            Check.That(coldMealsNumber).IsEqualTo(2);
        }

        [Test]
        public void CountColdMeals_Should_Return_Zero_Cold_Meal_When_Participants_Checkin_After_Friday_Midnight()
        {
            // SETUP
            var checkins = new List<Checkin>();
            var firstParticipantCheckin = new Checkin(Email.Of("regis.dubois@socrates.com"), new DateTime(2018, 10, 28, 00, 00, 00));
            var secondParticipantCheckin = new Checkin(Email.Of("fanny.dubois@socrates.com"), new DateTime(2018, 10, 28, 09, 20, 00));
            var thirdParticipantCheckin = new Checkin(Email.Of("Nany.dubois@socrates.com"), new DateTime(2018, 10, 29, 21, 13, 00));

            checkins.Add(firstParticipantCheckin);
            checkins.Add(secondParticipantCheckin);
            checkins.Add(thirdParticipantCheckin);

            var checkinProvider = Substitute.For<ICheckinProvider>();
            checkinProvider.GetCheckins().Returns(Checkins.FromList(checkins));

            // RUN
            var coldMealsCounter = new ColdMealsCounter(checkinProvider);
            var coldMealsNumber = coldMealsCounter.CountColdMeals();
            // ASSERT
            Check.That(coldMealsNumber).IsEqualTo(0);
        }
    }
}
