using NFluent;
using NUnit.Framework;

namespace Socrates.Test
{
    public class EmailTest
    {
        [TestCase("")]
        [TestCase(null)]
        [TestCase("gabriel.zaafrani")]
        public void AddEmail_Should_Throw_An_Exception_When_The_Candidate_Email_Is_Invalid(string invalidEmail)
        {
            Check.ThatCode(() =>
            {
                Email.Of(invalidEmail);
            })
            .Throws<InvalidEmailException>();
        }
    }
}
