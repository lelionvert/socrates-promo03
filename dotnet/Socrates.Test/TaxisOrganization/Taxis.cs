using System;
using System.Collections.Generic;
using Socrates.Meals;
using System.Linq;

namespace Socrates.Test.TaxisOrganization
{
    public class Taxis
    {
        private readonly IList<Taxi> taxiList;

        private Taxis(IList<Taxi> list)
        {
            taxiList = list;
        }

        public Taxis()
        {
            taxiList = new List<Taxi>();
        }

        public bool Contains(Taxi taxi)
        {
            return taxiList.Contains(taxi);
        }

        public Taxis GetTaxisWithPeriod(Period taxisPeriod)
        {
             return FromList(this.taxiList.Where(t => t.IsAvailableBy(taxisPeriod)).ToList());
        }

        public bool IsEmpty()
        {
            return taxiList.Count == 0;
        }

        public static Taxis FromList(IList<Taxi> list)
        {
            return new Taxis(list);
        }
    }
}