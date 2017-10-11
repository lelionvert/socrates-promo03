using NFluent;
using NUnit.Framework;
using Socrates.CandidateRegistration;

namespace Socrates.Test.Models
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
