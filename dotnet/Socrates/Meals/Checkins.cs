using System;
using System.Collections.Generic;
using System.Linq;

namespace Socrates.Meals
{
    public class Checkins
    {
        private IList<Checkin> checkinList;

        private Checkins(IList<Checkin> list)
        {
            checkinList = list;
        }

        public static Checkins FromList(IList<Checkin> list)
        {
            return new Checkins(list);
        }

        public int GiveCheckinsNumberBetween(Period period)
        {
            return checkinList.Where(checkin => checkin.IsContainedBy(period)).Count();
        }

        public void Add(Checkin checkin)
        {
            if (checkinList == null)
            {
                return;
            }

            checkinList.Add(checkin);
        }

        public int CountCheckins()
        {
            return checkinList.Count;
        }
    }
}