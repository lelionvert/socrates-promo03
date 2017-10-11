using System;

namespace Socrates.Test.Meals
{
    internal class ColdMealsCounter
    {
        private Participant participant;

        public ColdMealsCounter()
        {
        }

        public ColdMealsCounter(Participant participant)
        {
            this.participant = participant;
        }

        internal int CountColdMeals()
        {
            throw new NotImplementedException();
        }
    }
}