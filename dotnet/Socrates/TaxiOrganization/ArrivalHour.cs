using System;

namespace Socrates.TaxiOrganization
{
    public class ArrivalHour : IComparable<ArrivalHour>
    {
        private readonly int hour;

        public ArrivalHour(int hour)
        {
            this.hour = hour;
        }

        public override string ToString()
        {
            return hour.ToString();
        }

        public override bool Equals(object obj)
        {
            var hour = obj as ArrivalHour;
            return hour != null &&
                   this.hour == hour.hour;
        }

        public override int GetHashCode()
        {
            return -2145515161 + hour.GetHashCode();
        }

       

        public int CompareTo(ArrivalHour other)
        {
            return this.hour.CompareTo(other.hour);
        }
    }
}
