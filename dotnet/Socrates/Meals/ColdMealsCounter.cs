using System;

namespace Socrates.Meals
{
    public class ColdMealsCounter
    {
        private ICheckinProvider checkinProvider;
        private  DateTime startingDate = new DateTime(2017,10,27,21,00,00);
        private DateTime endingDate = new DateTime(2017, 10, 27, 23, 59, 59);

        public ColdMealsCounter(ICheckinProvider checkinProvider)
        {
            this.checkinProvider = checkinProvider;
        }

        public int CountColdMeals()
        {
            int numberColdMeals = 0;
            var checkins = checkinProvider.GetCheckins();
            foreach (var checkin in checkins)
            {
                if (checkin.IsAfter(startingDate) && checkin.IsBefore(endingDate))
                    numberColdMeals++;
            }
            return numberColdMeals;
        }
    }
}