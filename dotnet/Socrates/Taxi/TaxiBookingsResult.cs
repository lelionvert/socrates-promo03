using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Socrates.Taxi
{
    public abstract class TaxiBookingsResult
    {

        public static TaxiBookingsResult NotFound()
        {
            return new NotFoundResult();
        }

        private class NotFoundResult : TaxiBookingsResult
        {

            public NotFoundResult()
            {
            }

            public override bool Equals(object obj)
            {
                var notFound = obj as NotFoundResult;
                return notFound != null;
            }

            public override int GetHashCode()
            {
                return base.GetHashCode();
            }
        }
    }
}
