using System;
using Socrates.Meals;

namespace Socrates.Test.TaxisOrganization
{
    public class Taxi
    {
        private int placeNumber;

        private Taxi(int placeNumber)
        {
            this.placeNumber = placeNumber;
        }

        internal static Taxi WithSeat(int placeNumber)
        {
            return new Taxi(placeNumber);
        }

        public override bool Equals(object obj)
        {
            var taxi = obj as Taxi;
            return taxi != null &&
                   placeNumber == taxi.placeNumber;
        }

        public override int GetHashCode()
        {
            return 498235765 + placeNumber.GetHashCode();
        }

        public bool IsAvailableBy(Period taxisPeriod)
        {
            return true;
        }
    }
}