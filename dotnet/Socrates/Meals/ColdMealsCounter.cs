using System;

namespace Socrates.Meals
{
    public class ColdMealsCounter
    {
        private ICheckinProvider checkinProvider;
        private DateTime startingDate = new DateTime(2018, 10, 27, 21, 00, 00);
        private DateTime endingDate = new DateTime(2018, 10, 27, 23, 59, 59);
        private readonly Period coldMealsPeriod;

        public ColdMealsCounter(ICheckinProvider checkinProvider)
        {
            this.checkinProvider = checkinProvider;
            coldMealsPeriod = new Period(startingDate, endingDate);
        }

        public int CountColdMeals()
        {
            var checkins = checkinProvider.GetCheckins();

            var checkinsNumber = checkins.GiveCheckinsNumberBetween(coldMealsPeriod);

            return checkinsNumber;
        }
    }
}