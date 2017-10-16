using System;
using Socrates.CandidateRegistration;

namespace Socrates.Test.Meals
{
    public class Checkin
    {
        private Email email;
        private DateTime participantCheckinDate;
        private DateTime maximumCheckinDate = new DateTime(2017, 10, 27, 21, 00, 00);

        public Checkin(Email email, DateTime participantCheckinDate)
        {
            this.email = email;
            this.participantCheckinDate = participantCheckinDate;
        }

        internal bool IsLate()
        {
            return participantCheckinDate > maximumCheckinDate;
        }
    }
}