using NFluent;
using NUnit.Framework;
using Socrates.TaxiOrganization;

namespace Socrates.Test.TaxiOrganization
{
    public class ArrivalsTest
    {
        [Test]
        public void GetLast_should_return_last_arrival_time_when_two_same_arrivals_hour()
        {
            // GIVEN
            Participant participant1 = new Participant("Houssam");
            Participant participant2 = new Participant("Thierry de Pauw");

            ArrivalHour arrivalHour = new ArrivalHour(15);

            Arrival arrival = new Arrival(arrivalHour, participant1);
            Arrival arrival2 = new Arrival(arrivalHour, participant2);
            Arrivals arrivals = new Arrivals();
            arrivals.Add(arrival);
            arrivals.Add(arrival2);

            // WHEN
            ArrivalHour lastArrival = arrivals.GetLastArrivalTime();

            // THEN
            Check.That(lastArrival).IsEqualTo(new ArrivalHour(15));
        }

        [Test]
        public void GetLast_should_return_last_arrival_time_when_two_differents_arrivals_hour()
        {
            // GIVEN
            Participant participant1 = new Participant("Houssam");
            Participant participant2 = new Participant("Thierry de Pauw");

            ArrivalHour arrivalHour = new ArrivalHour(15);
            ArrivalHour arrivalHour2 = new ArrivalHour(16);


            Arrival arrival = new Arrival(arrivalHour, participant1);
            Arrival arrival2 = new Arrival(arrivalHour2, participant2);
            Arrivals arrivals = new Arrivals();
            arrivals.Add(arrival2);
            arrivals.Add(arrival);

            // WHEN
            ArrivalHour lastArrival = arrivals.GetLastArrivalTime();

            // THEN
            Check.That(lastArrival).IsEqualTo(new ArrivalHour(16));
        }
    }
}
