﻿using NFluent;
using NUnit.Framework;

namespace Socrates.Test
{
    public class CandidateRegistrationManagerTest
    {
        private const string RegisDubois = "regis.dubois@socrates.com";
        private const string FannyDubois = "fanny.dubois@crafts.com";
        private const string JulieFournier = "jules.fournier@xp.com";
        private const string JulieMarechal = "julie.marechal@microsoft.com";

        [Test]
        public void GetEmails_Should_Return_No_Email_From_Candidate_When_No_Candidate_Email_Exists()
        {
            var candidateRegistrationManager = new CandidateRegistrationManager();

            var listCandidatesEmail = candidateRegistrationManager.GetEmails();

            Check.That(listCandidatesEmail).IsEmpty();
        }

        [Test]
        public void GetEmails_Should_Return_One_Candidate_Email_When_Already_One_Exists()
        {
            var candidateProvider = new CandidateProvider(Email.Of(RegisDubois));

            var candidateRegistrationManager = new CandidateRegistrationManager(candidateProvider);

            var listCandidatesEmails = candidateRegistrationManager.GetEmails();

            Check.That(listCandidatesEmails).ContainsExactly(Email.Of(RegisDubois));
        }
        
        [TestCase(RegisDubois)]
        [TestCase(FannyDubois)]
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

            candidateRegistrationManager.AddEmail(Email.Of(FannyDubois));

            candidateRegistrationManager.AddEmail(Email.Of(RegisDubois));
            
            var listCandidatesEmails = candidateRegistrationManager.GetEmails();

            Check.That(listCandidatesEmails).Contains(Email.Of(RegisDubois), Email.Of(FannyDubois)).Only().Once();
        }

        [TestCase(RegisDubois)]
        [TestCase(FannyDubois)]
        public void AddEmail_Should_Return_Unique_Candidate_Email_Even_If_You_Try_To_Add_Existing_Candidate_Email(string candidateEmail)
        {
            var candidateRegistrationManager = new CandidateRegistrationManager();

            candidateRegistrationManager.AddEmail(Email.Of(candidateEmail));

            Check.ThatCode(() => candidateRegistrationManager.AddEmail(Email.Of(candidateEmail))).Throws<EmailExistingException>();
        }

        [Test]
        public void AddEmail_Should_Add_One_Candidate_Email_When_Multiples_Candidate_Email_Exists()
        {
            var candidateProvider = new CandidateProvider(Email.Of(RegisDubois), Email.Of(FannyDubois));

            var candidateRegistrationManager = new CandidateRegistrationManager(candidateProvider);

            candidateRegistrationManager.AddEmail(Email.Of(JulieFournier));

            var listCandidatesEmails = candidateRegistrationManager.GetEmails();
            Check.That(listCandidatesEmails).Contains(Email.Of(RegisDubois), Email.Of(FannyDubois), Email.Of(JulieFournier)).Only().Once();
        }

        [TestCase(JulieMarechal, JulieFournier)]
        public void AddEmail_Should_Add_Multiple_Candidate_Emails_When_Multiples_Candidate_Email_Exists(string firstCandidateEmail, string secondCandidateEmail)
        {
            var candidateProvider = new CandidateProvider(Email.Of(RegisDubois), Email.Of(FannyDubois));

            var candidateRegistrationManager = new CandidateRegistrationManager(candidateProvider);

            candidateRegistrationManager.AddEmail(Email.Of(firstCandidateEmail));
            candidateRegistrationManager.AddEmail(Email.Of(secondCandidateEmail));

            var listCandidatesEmails = candidateRegistrationManager.GetEmails();
            Check.That(listCandidatesEmails).Contains(Email.Of(RegisDubois), Email.Of(FannyDubois), Email.Of(firstCandidateEmail), Email.Of(secondCandidateEmail)).Only().Once();
        }

        
    }
}
    
