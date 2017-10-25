using Socrates.Meals;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Socrates.Taxi
{
    public class TaxiOrganizer
    {

        private ICheckinProvider checkInProvider;

        public TaxiOrganizer(ITaxiProvider inMemoryTaxiProvider, ICheckinProvider checkInProvider)
        {

            this.checkInProvider = checkInProvider;
        }

        public TaxiBookingsResult GetBookings()
        {
            checkInProvider.GetCheckins();

            return TaxiBookingsResult.NotFound();
        }
    }
}
