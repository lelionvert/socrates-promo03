using System.Collections.Generic;

namespace Socrates.Meals
{
    public class CheckinProvider : ICheckinProvider
    {
        private readonly IList<Checkin> participantCheckin = new List<Checkin>();

        public CheckinProvider(params Checkin[] checkins)
        {
            foreach (var checkin in checkins)
            {
                participantCheckin.Add(checkin);
            }
        }

        public IList<Checkin> GetCheckins()
        {
            return new List<Checkin>(participantCheckin);
        }
    }
}