using System;
using System.Collections.Generic;

namespace Socrates.Test.Meals
{
    internal class CheckinProvider : ICheckinProvider
    {
        private IList<Checkin> participantCheckin = new List<Checkin>();
            
        public CheckinProvider(params Checkin[] checkins)
        {
            foreach (var checkin in checkins)
            {
                participantCheckin.Add(checkin);
            }
        }

        public int CountLateCheckin()
        {
            throw new NotImplementedException();
        }
    }
}