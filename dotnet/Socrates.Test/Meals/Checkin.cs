using System;
using Socrates.CandidateRegistration;

namespace Socrates.Test.Meals
{
    public class Checkin
    {
        private Email email;
        private DateTime participantCheckinDate;

        public Checkin(Email email, DateTime participantCheckinDate)
        {
            this.email = email;
            this.participantCheckinDate = participantCheckinDate;
        }
    }
}