using System.Collections.Generic;

namespace Socrates.Meals
{
    public interface ICheckinProvider
    {
        IList<Checkin> GetCheckins();
    }
}