using System;
using System.Collections.Generic;

namespace Socrates.Test.Meals
{
    internal class CheckinProvider
    {
        private IList<Checkin> participantCheckin = new List<Checkin>();

        public CheckinProvider(Checkin checkin)
        {
            participantCheckin.Add(checkin);
        }

        internal int CountLateCheckin()
        {
            return 0;
        }
    }
}