using System;
using System.Linq;
using Socrates.Meals;

namespace Socrates.Test.TaxisOrganization
{
    public class TaxiProvider
    {
        private readonly Taxis taxis;

        

        public TaxiProvider(params Taxi[] taxis)
        {
            this.taxis = Taxis.FromList(taxis.ToList());
        }

        public Taxis GetTaxisWithPeriod(Period taxisPeriod)
        {
            return taxis.GetTaxisWithPeriod(taxisPeriod);
        }
    }
}