using Socrates.Meals;

namespace Socrates.Test.TaxisOrganization
{
    public class TaxisOrganizer
    {
        private readonly CheckinProvider checkinProvider;
        private readonly TaxiProvider taxiProvider;

       

        public TaxisOrganizer(CheckinProvider checkinProvider)
        {
            this.checkinProvider = checkinProvider;
        }

        public TaxisOrganizer(CheckinProvider checkinProvider, TaxiProvider taxiProvider) : this(checkinProvider)
        {
            this.taxiProvider = taxiProvider;
        }

        public Taxis GetNeededTaxisBy(Period taxisPeriod)
        {
            return taxiProvider.GetTaxisWithPeriod(taxisPeriod);
        }
    }
}