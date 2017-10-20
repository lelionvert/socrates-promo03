using System;

namespace Socrates.Meals
{
    public class Period
    {
        private readonly DateTime startingDate;
        private readonly DateTime endingDate;

        public Period(DateTime startingDate, DateTime endingDate)
        {
            this.startingDate = startingDate;
            this.endingDate = endingDate;
        }

        public bool Contains(DateTime specificDate)
        {
            return specificDate >= startingDate && specificDate < endingDate;
        }
    }
}