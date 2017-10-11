using System;

namespace Socrates.Test.Meals
{
    internal class Participant
    {
        private string v;
        private DateTime participantCheckinDate;

        public Participant(string v, DateTime participantCheckinDate)
        {
            this.v = v;
            this.participantCheckinDate = participantCheckinDate;
        }
    }
}