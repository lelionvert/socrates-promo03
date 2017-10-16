using NFluent;
using NUnit.Framework;

namespace Socrates.Test.Meals
{
    internal class CheckinProviderTest
    {
        [Test]
        public void CountLateCheckin_Should_Return_Zero_When_Zero_Checkin()
        {
            // SETUP
            var checkinProvider = new CheckinProvider();

            // RUN
            var countLateCheckin = checkinProvider.CountLateCheckin();

            // ASSERT
            Check.That(countLateCheckin).IsZero();
        }
    }
}
