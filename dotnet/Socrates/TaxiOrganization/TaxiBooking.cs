using System;
using System.Collections.Generic;
using System.Linq;

namespace Socrates.TaxiOrganization
{
    public class TaxiBooking
    {
        private readonly Taxi taxi;
        private readonly DateTime time;
        private readonly IList<Passenger> passengers;

        public TaxiBooking(Taxi taxi, String time, IList<Passenger> passengers)
        {
            this.taxi = taxi;
            this.time = new DateTime(2018, 10, 26, int.Parse(time), 00, 00);
            this.passengers = passengers;
        }

        public override bool Equals(object obj)
        {
            var booking = obj as TaxiBooking;
            return booking != null &&
                   EqualityComparer<Taxi>.Default.Equals(taxi, booking.taxi) &&
                   time == booking.time &&
                   passengers.SequenceEqual(booking.passengers);
        }

        public override int GetHashCode()
        {
            var hashCode = -646340067;
            hashCode = hashCode * -1521134295 + EqualityComparer<Taxi>.Default.GetHashCode(taxi);
            hashCode = hashCode * -1521134295 + time.GetHashCode();
            hashCode = hashCode * -1521134295 + EqualityComparer<IList<Passenger>>.Default.GetHashCode(passengers);
            return hashCode;
        }

        public Taxi GetTaxi()
        {
            return taxi;
        }

        public IEnumerable<Passenger> GetPassengers()
        {
            foreach (var passenger in passengers)
            {
                yield return passenger;
            }
        }
    }
}
