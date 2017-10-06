using NFluent;
using NUnit.Framework;
using Socrates.US1_CandidateRegistration;

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

            var listCandidates = candidateRegistrationManager.Candidates;

            Check.That(listCandidates).IsEmpty();
        }

           


        [Test]
        public void GetCandidates_Should_Return_One_Candidate_When_Already_One_Exists()
        {
            var regisDuboisCandidate = new Candidate(Email.Of(RegisDuboisEmail));
            var candidateProvider = new CandidateProvider(regisDuboisCandidate);

            var candidateRegistrationManager = new CandidateRegistrationManager(candidateProvider);

            var listCandidates = candidateRegistrationManager.Candidates;

            Check.That(listCandidates).ContainsExactly(regisDuboisCandidate);
        }
        
        [TestCase(RegisDuboisEmail)]
        [TestCase(FannyDuboisEmail)]
        public void AddEmail_Should_Return_The_Email_Previously_Added(string candidateEmail)
        {
            var candidateRegistrationManager = new CandidateRegistrationManager();

            candidateRegistrationManager.AddEmail(Email.Of(candidateEmail));

            var listCandidatesEmails = candidateRegistrationManager.GetEmails();

            Check.That(listCandidatesEmails[0]).IsEqualTo(Email.Of(candidateEmail));
        }

        [Test]
        public void AddEmail_Should_Insert_Multiples_Email_And_Return_Email_List_From_The_Candidate()
        {
            var candidateRegistrationManager = new CandidateRegistrationManager();

            candidateRegistrationManager.AddEmail(Email.Of(FannyDuboisEmail));

            candidateRegistrationManager.AddEmail(Email.Of(RegisDuboisEmail));
            
            var listCandidatesEmails = candidateRegistrationManager.GetEmails();

            Check.That(listCandidatesEmails).Contains(Email.Of(RegisDuboisEmail), Email.Of(FannyDuboisEmail)).Only().Once();
        }

        [TestCase(RegisDuboisEmail)]
        [TestCase(FannyDuboisEmail)]
        public void AddEmail_Should_Return_Unique_Candidate_Email_Even_If_You_Try_To_Add_Existing_Candidate_Email(string candidateEmail)
        {
            var candidateRegistrationManager = new CandidateRegistrationManager();

            candidateRegistrationManager.AddEmail(Email.Of(candidateEmail));

            Check.ThatCode(() => candidateRegistrationManager.AddEmail(Email.Of(candidateEmail))).Throws<ExistingEmailException>();
        }

        [Test]
        public void AddEmail_Should_Add_One_Candidate_Email_When_Multiples_Candidate_Email_Exists()
        {
            var candidateProvider = new CandidateProvider(Email.Of(RegisDuboisEmail), Email.Of(FannyDuboisEmail));

            var candidateRegistrationManager = new CandidateRegistrationManager(candidateProvider);

            candidateRegistrationManager.AddEmail(Email.Of(JulieFournierEmail));

            var listCandidatesEmails = candidateRegistrationManager.GetEmails();
            Check.That(listCandidatesEmails).Contains(Email.Of(RegisDuboisEmail), Email.Of(FannyDuboisEmail), Email.Of(JulieFournierEmail)).Only().Once();
        }

        [TestCase(JulieMarechalEmail, JulieFournierEmail)]
        public void AddEmail_Should_Add_Multiple_Candidate_Emails_When_Multiple_Candidate_Emails_Exist(string firstCandidateEmail, string secondCandidateEmail)
        {
            var candidateProvider = new CandidateProvider(Email.Of(RegisDuboisEmail), Email.Of(FannyDuboisEmail));

            var candidateRegistrationManager = new CandidateRegistrationManager(candidateProvider);

            candidateRegistrationManager.AddEmail(Email.Of(firstCandidateEmail));
            candidateRegistrationManager.AddEmail(Email.Of(secondCandidateEmail));

            var listCandidatesEmails = candidateRegistrationManager.GetEmails();
            Check.That(listCandidatesEmails).Contains(Email.Of(RegisDuboisEmail), Email.Of(FannyDuboisEmail), Email.Of(firstCandidateEmail), Email.Of(secondCandidateEmail)).Only().Once();
        }

        
    }
}
    
