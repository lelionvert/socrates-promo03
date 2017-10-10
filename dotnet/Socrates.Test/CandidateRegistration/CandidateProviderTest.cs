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
        public void AddCandidate_Should_Return_The_Candidate_Initialized(string candidateEmail)
        {
            var candidate = new Candidate(Email.Of(candidateEmail));
            var candidateProvider = new CandidateProvider();
            candidateProvider.AddCandidate(candidate);
            var expectedCandidate = new Candidate(Email.Of(candidateEmail));

            Check.That(candidateProvider.HasAlready(expectedCandidate)).IsTrue();
        }

        [TestCase(RegisDuboisEmail)]
        [TestCase(FannyDuboisEmail)]
        public void ContainsCandidate_Should_Return_The_Candidate_Previously_Added(string candidateEmail)
        {
            var candidate = new Candidate(Email.Of(candidateEmail));
            var candidateProvider = new CandidateProvider(candidate);

            var expectedCandidate = new Candidate(Email.Of(candidateEmail));

            Check.That(candidateProvider.HasAlready(expectedCandidate)).IsTrue();
        }

        [Test]
        public void ContainsCandidates_Should_Insert_Multiples_Candidate_And_Return_Candidate_List_From_The_Candidate()
        {
            var candidateProvider = new CandidateProvider();

            candidateProvider.AddCandidate(new Candidate(Email.Of(FannyDuboisEmail)));
            candidateProvider.AddCandidate(new Candidate(Email.Of(RegisDuboisEmail)));

            var candidates = candidateProvider.HasAlready(new Candidate(Email.Of(FannyDuboisEmail)), new Candidate(Email.Of(RegisDuboisEmail)));

            Check.That(candidates).IsTrue();
        }

        [Test]
        public void GetCandidateEmails_Should_Return_Candidates_Email_List()
        {
            var candidateProvider = new CandidateProvider();

            candidateProvider.AddCandidate(new Candidate(Email.Of(FannyDuboisEmail)));
            candidateProvider.AddCandidate(new Candidate(Email.Of(RegisDuboisEmail)));

            Check.That(candidateProvider.GetCandidateEmails()).ContainsExactly(Email.Of(FannyDuboisEmail), Email.Of(RegisDuboisEmail));
        }
    }
}