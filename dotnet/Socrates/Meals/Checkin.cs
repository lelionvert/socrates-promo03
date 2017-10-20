using System;
using Socrates.CandidateRegistration;

namespace Socrates.Meals
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

        private bool IsAfter(DateTime startingDate)
        {
            return participantCheckinDate >= startingDate;
        }

        public bool IsBetween(DateTime startingDate, DateTime endingDate)
        {
            return IsAfter(startingDate) && !IsAfter(endingDate);
        }

        public bool IsContainedBy(Period period)
        {
            return period.Contains(participantCheckinDate);
        }
    }
}