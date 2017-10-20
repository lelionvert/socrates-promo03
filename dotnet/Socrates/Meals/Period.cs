using System;

namespace Socrates.Meals
{
    public class Period
    {
        private DateTime startingDate;
        private DateTime endingDate;

        public Period(DateTime startingDate, DateTime endingDate)
        {
            this.startingDate = startingDate;
            this.endingDate = endingDate;
        }

        public bool IsContainedWith(DateTime specificDate)
        {
            return specificDate >= startingDate && specificDate < endingDate;
        }
    }
}