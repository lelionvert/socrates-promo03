using NFluent;
using NUnit.Framework;
using Socrates.CandidateRegistration;

namespace Socrates.Test.CandidateRegistration
{
    class CandidateProviderTest
    {
        private const string RegisDuboisEmail = "regis.dubois@socrates.com";
        private const string FannyDuboisEmail = "fanny.dubois@crafts.com";
        private const string JulieFournierEmail = "jules.fournier@xp.com";
        private const string JulieMarechalEmail = "julie.marechal@microsoft.com";

        [Test]
        public void GetCandidates_Should_Return_No_Candidates_When_Initializing_Provider()
        {
            var candidateProvider = new CandidateProvider();

            Check.That(candidateProvider.IsEmpty()).IsTrue();
        }

        [TestCase(RegisDuboisEmail)]
        [TestCase(FannyDuboisEmail)]
        public void ContainsCandidates_Should_Return_The_Candidate_Previously_Added(string candidateEmail)
        {
            var candidate = new Candidate(Email.Of(candidateEmail));
            var candidateProvider = new CandidateProvider(candidate);
            Check.That(candidateProvider.ContainsCandidates(candidate)).IsTrue();
        }

    }
}