using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Socrates.TaxiOrganization
{
    public class Passenger
    {
        private readonly string passengerName;

        public Passenger(String passengerName)
        {
            this.passengerName = passengerName;
        }

        public override bool Equals(object obj)
        {
            var passenger = obj as Passenger;
            return passenger != null &&
                   passengerName == passenger.passengerName;
        }

        public override int GetHashCode()
        {
            return 1158908920 + EqualityComparer<string>.Default.GetHashCode(passengerName);
        }
    }
}
