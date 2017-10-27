using System.Collections.Generic;
using System.Linq;

namespace Socrates.TaxiOrganization
{
    public class Arrivals
    {
        private IList<Arrival> arrivals = new List<Arrival>();

        public ArrivalHour GetLastArrivalTime()
        {
            return arrivals.Max(arrival => arrival.GetHour());
        }

        public void Add(Arrival arrival)
        {
            arrivals.Add(arrival);
        }

        public IList<Arrival> GetArrivals()
        {
            return new List<Arrival>(arrivals);
        }
    }
}
