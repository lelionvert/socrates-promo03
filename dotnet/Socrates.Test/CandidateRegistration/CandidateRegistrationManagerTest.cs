using NFluent;
using NUnit.Framework;
using Socrates.CandidateRegistration;

namespace Socrates.Test.Services
{
    public class CandidateRegistrationManagerTest
    {
        private const string RegisDuboisEmail = "regis.dubois@socrates.com";
        private const string FannyDuboisEmail = "fanny.dubois@crafts.com";
        private const string JulieFournierEmail = "jules.fournier@xp.com";
        private const string JulieMarechalEmail = "julie.marechal@microsoft.com";

        [Test]
        public void GetCandidates_Should_Return_No_Candidates_When_No_Candidate_Exists()
        {
            var candidateRegistrationManager = new CandidateRegistrationManager();

            var candidates = candidateRegistrationManager.Candidates;

            Check.That(candidates).IsEmpty();
        }

        [Test]
        public void GetCandidates_Should_Return_One_Candidate_When_Already_One_Exists()
        {
            var regisDuboisCandidate = new Candidate(Email.Of(RegisDuboisEmail));
            var candidateProvider = new CandidateProvider(regisDuboisCandidate);

            var candidateRegistrationManager = new CandidateRegistrationManager(candidateProvider);

            var candidates = candidateRegistrationManager.Candidates;

            Check.That(candidates).ContainsExactly(regisDuboisCandidate);
        }
        
        [TestCase(RegisDuboisEmail)]
        [TestCase(FannyDuboisEmail)]
        public void Add_Should_Return_The_CandidateEmail_Previously_Added(string candidateEmail)
        {
            var candidate = new Candidate(Email.Of(candidateEmail));

            var candidateRegistrationManager = new CandidateRegistrationManager();

            candidateRegistrationManager.Register(candidate);

            var candidates = candidateRegistrationManager.Candidates;

            Check.That(candidates[0]).IsEqualTo(new Candidate(Email.Of(candidateEmail)));
        }

        [Test]
        public void Add_Should_Insert_Multiples_Candidate_And_Return_Candidate_List_From_The_Candidate()
        {
            var candidateRegistrationManager = new CandidateRegistrationManager();

            candidateRegistrationManager.Register(new Candidate(Email.Of(FannyDuboisEmail)));
            candidateRegistrationManager.Register(new Candidate(Email.Of(RegisDuboisEmail)));

            var candidates = candidateRegistrationManager.Candidates;

            Check.That(candidates).Contains(new Candidate(Email.Of(RegisDuboisEmail)), new Candidate(Email.Of(FannyDuboisEmail))).Only().Once();
        }

        [TestCase(RegisDuboisEmail)]
        [TestCase(FannyDuboisEmail)]
        public void Add_Should_Return_Unique_Candidate_Even_If_You_Try_To_Add_Existing_Candidate(string candidateEmail)
        {
            var candidateRegistrationManager = new CandidateRegistrationManager();

            candidateRegistrationManager.Register(new Candidate(Email.Of(candidateEmail)));

            Check.ThatCode(() => candidateRegistrationManager.Register(new Candidate(Email.Of(candidateEmail)))).Throws<ExistingCandidateException>();
        }

        [Test]
        public void Add_Should_Add_One_Candidate_When_Multiples_Candidate_Exists()
        {
            var candidateProvider = new CandidateProvider(new Candidate(Email.Of(RegisDuboisEmail)), new Candidate(Email.Of(FannyDuboisEmail)));

            var candidateRegistrationManager = new CandidateRegistrationManager(candidateProvider);

            candidateRegistrationManager.Register(new Candidate(Email.Of(JulieFournierEmail)));

            var candidates = candidateRegistrationManager.Candidates;

            Check.That(candidates).Contains(new Candidate(Email.Of(RegisDuboisEmail)), new Candidate(Email.Of(FannyDuboisEmail)), new Candidate(Email.Of(JulieFournierEmail))).Only().Once();
        }

        [TestCase(JulieMarechalEmail, JulieFournierEmail)]
        public void Add_Should_Add_Multiple_Candidate_Emails_When_Multiple_Candidate_Emails_Exist(string firstCandidateEmail, string secondCandidateEmail)
        {
            var candidateProvider = new CandidateProvider(new Candidate(Email.Of(RegisDuboisEmail)), new Candidate(Email.Of(FannyDuboisEmail)));

            var candidateRegistrationManager = new CandidateRegistrationManager(candidateProvider);

            candidateRegistrationManager.Register(new Candidate(Email.Of(firstCandidateEmail)));
            candidateRegistrationManager.Register(new Candidate(Email.Of(secondCandidateEmail)));

            var candidates = candidateRegistrationManager.Candidates;
            Check.That(candidates).Contains(new Candidate(Email.Of(RegisDuboisEmail)), new Candidate(Email.Of(FannyDuboisEmail)), new Candidate(Email.Of(firstCandidateEmail)), new Candidate(Email.Of(secondCandidateEmail))).Only().Once();
        }

        
    }
}
    
