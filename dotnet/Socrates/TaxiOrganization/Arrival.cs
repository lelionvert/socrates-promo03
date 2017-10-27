namespace Socrates.TaxiOrganization
{
    public class Arrival
    {
        private readonly ArrivalHour hour;
        private readonly Participant participant;

        public Arrival(ArrivalHour hour, Participant participant)
        {
            this.hour = hour;
            this.participant = participant;
        }

        public ArrivalHour GetHour()
        {
            return hour;
        }

        public string GetParticipantName()
        {
            return participant.GetName();
        }
    }
}