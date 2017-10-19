using System.Linq;

namespace Socrates.Meals
{
    public class CheckinProvider : ICheckinProvider
    {
        private readonly Checkins participantCheckins;

        public CheckinProvider(params Checkin[] checkins)
        {
            participantCheckins = Checkins.FromList(checkins.ToList());
        }

        public Checkins GetCheckins()
        {
            return participantCheckins;
        }
    }
}