namespace Socrates.Meals
{
    public interface ICheckinProvider
    {
        void Add(Checkin checkin);
        Checkins GetCheckins();
    }
}