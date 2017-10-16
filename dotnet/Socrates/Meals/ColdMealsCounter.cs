namespace Socrates.Meals
{
    public class ColdMealsCounter
    {
        private ICheckinProvider checkinProvider;

        public ColdMealsCounter(ICheckinProvider checkinProvider)
        {
            this.checkinProvider = checkinProvider;
        }

        public int CountColdMeals()
        {
            return checkinProvider.CountLateCheckin();
        }
    }
}