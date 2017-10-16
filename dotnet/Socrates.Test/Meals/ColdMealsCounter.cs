using System;

namespace Socrates.Test.Meals
{
    internal class ColdMealsCounter
    {
        private ICheckinProvider checkinProvider;

     

        public ColdMealsCounter(ICheckinProvider checkinProvider)
        {
            this.checkinProvider = checkinProvider;
        }

        internal int CountColdMeals()
        {
            return checkinProvider.CountLateCheckin();
        }
    }
}