using System.Collections.Generic;

namespace Socrates.Meals
{
    public class CheckinProvider : ICheckinProvider
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
            int lateCheckinNumber = 0;

            foreach(var checkin in participantCheckin)
            {
                if (checkin.IsLate())
                {
                    lateCheckinNumber++;
                }
            }

            return lateCheckinNumber;
        }
    }
}