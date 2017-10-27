using System.Collections.Generic;
using System.Linq;

namespace Socrates.TaxiOrganization
{
    public class TaxiOrganizer
    {
        private readonly Arrivals arrivals;

        public TaxiOrganizer(Arrivals arrivals)
        {
            this.arrivals = arrivals;
        }

        public TaxiBooking GetBookings()
        {
            var taxi = new Taxi($"Taxi_{ arrivals.GetLastArrivalTime() }h");

            var time = arrivals.GetLastArrivalTime().ToString();

            var passengers = arrivals.GetArrivals().Select(arrival => new Passenger(arrival.GetParticipantName())).ToList();

            return new TaxiBooking(taxi, time, passengers);
        }

    }
}
