using NFluent;
using NUnit.Framework;
using Socrates.CandidateRegistration;

namespace Socrates.Test.Services
{
    public class CandidateRegisterTest
    {
        private const string RegisDuboisEmail = "regis.dubois@socrates.com";
        private const string FannyDuboisEmail = "fanny.dubois@crafts.com";
        private const string JulieFournierEmail = "jules.fournier@xp.com";
        private const string JulieMarechalEmail = "julie.marechal@microsoft.com";

        [Test]
        public void GetCandidates_Should_Return_No_Candidates_When_Initializing_Register()
        {

            var candidateProvider = new CandidateProvider();
            var candidateRegister = new CandidateRegister(candidateProvider);

            Check.That(candidateProvider.IsEmpty()).IsTrue();
        }

        [Test]
        public void GetCandidates_Should_Return_One_Candidate_When_Already_One_Exists()
        {
            var regisDuboisCandidate = new Candidate(Email.Of(RegisDuboisEmail));
            var candidateProvider = new CandidateProvider(regisDuboisCandidate);

            var candidateRegister = new CandidateRegister(candidateProvider);

            var expectedCandidate = new Candidate(Email.Of(RegisDuboisEmail));

            Check.That(candidateProvider.ContainsCandidates(expectedCandidate)).IsTrue();
        }

        [TestCase(RegisDuboisEmail)]
        [TestCase(FannyDuboisEmail)]
        public void Register_Should_Return_The_Candidate_Previously_Added(string candidateEmail)
        {
            var candidate = new Candidate(Email.Of(candidateEmail));
            var candidateProvider = new CandidateProvider();
            var candidateRegistrer = new CandidateRegister(candidateProvider);

            candidateRegistrer.Register(candidate);

            Check.That(candidateProvider.ContainsCandidates(new Candidate(Email.Of(candidateEmail)))).IsTrue();
        }

        [Test]
        public void Register_Should_Insert_Multiples_Candidates_And_Verify_Their_Existance()
        {
            var candidateProvider = new CandidateProvider();
            var candidateRegister = new CandidateRegister(candidateProvider);

           candidateRegister.Register(new Candidate(Email.Of(FannyDuboisEmail)));
           candidateRegister.Register(new Candidate(Email.Of(RegisDuboisEmail)));

            Check.That(candidateProvider.ContainsCandidates(new Candidate(Email.Of(RegisDuboisEmail)), new Candidate(Email.Of(FannyDuboisEmail)))).IsTrue();
        }

        [TestCase(RegisDuboisEmail)]
        [TestCase(FannyDuboisEmail)]
        public void Add_Should_Return_Unique_Candidate_Even_If_You_Try_To_Add_Existing_Candidate(string candidateEmail)
        {
            var candidateProvider = new CandidateProvider();

            var candidateRegister = new CandidateRegister(candidateProvider);

            candidateRegister.Register(new Candidate(Email.Of(candidateEmail)));

            candidateRegister.Register(new Candidate(Email.Of(candidateEmail)));

            Check.That(candidateProvider.GetCandidates()).Contains(new Candidate(Email.Of(candidateEmail))).Only().Once();
        }

        [Test]
        public void Add_Should_Add_One_Candidate_When_Multiples_Candidate_Exists()
        {
            var candidateProvider = new CandidateProvider(new Candidate(Email.Of(RegisDuboisEmail)), new Candidate(Email.Of(FannyDuboisEmail)));

            var candidateRegister = new CandidateRegister(candidateProvider);

            candidateRegister.Register(new Candidate(Email.Of(JulieFournierEmail)));

            var candidates = candidateRegister.Candidates;

            Check.That(candidates).Contains(new Candidate(Email.Of(RegisDuboisEmail)), new Candidate(Email.Of(FannyDuboisEmail)), new Candidate(Email.Of(JulieFournierEmail))).Only().Once();
        }

        [TestCase(JulieMarechalEmail, JulieFournierEmail)]
        public void Add_Should_Add_Multiple_Candidate_Emails_When_Multiple_Candidate_Emails_Exist(string firstCandidateEmail, string secondCandidateEmail)
        {
            var candidateProvider = new CandidateProvider(new Candidate(Email.Of(RegisDuboisEmail)), new Candidate(Email.Of(FannyDuboisEmail)));

            var candidateRegister = new CandidateRegister(candidateProvider);

            candidateRegister.Register(new Candidate(Email.Of(firstCandidateEmail)));
            candidateRegister.Register(new Candidate(Email.Of(secondCandidateEmail)));

            var candidates = candidateRegister.Candidates;
            Check.That(candidates).Contains(new Candidate(Email.Of(RegisDuboisEmail)), new Candidate(Email.Of(FannyDuboisEmail)), new Candidate(Email.Of(firstCandidateEmail)), new Candidate(Email.Of(secondCandidateEmail))).Only().Once();
        }


    }
}

