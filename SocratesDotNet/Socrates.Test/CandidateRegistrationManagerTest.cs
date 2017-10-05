using NFluent;
using NUnit.Framework;
using System.Collections.Generic;

namespace Socrates.Test
{
    [TestFixture]
    public class CandidateRegistrationManagerTest
    {
        private const string RegisDubois = "regis.dubois@socrates.com";
        private const string FannyDubois = "fanny.dubois@crafts.com";

        [Test]
        public void GetEmails_Should_Return_No_Email_From_Candidate_When_No_Candidate_Email_Exists()
        {
            var candidateRegistrationManager = new CandidateRegistrationManager();

            var listCandidatesEmail = candidateRegistrationManager.GetEmails();

            Check.That(listCandidatesEmail).HasSize(0);
        }

        [Test]
        public void GetEmails_Should_Return_One_Candidate_Email_When_Already_One_Exists()
        {
            var candidateRegistrationManager = new CandidateRegistrationManager(RegisDubois);

            var listCandidatesEmails = candidateRegistrationManager.GetEmails();
            Check.That(listCandidatesEmails).ContainsExactly(RegisDubois);
        }

        [TestCase(RegisDubois)]
        [TestCase(FannyDubois)]
        public void AddEmail_Should_Return_The_Email_Previously_Added(string candidateEmail)
        {
            var candidateRegistrationManager = new CandidateRegistrationManager();

            candidateRegistrationManager.AddEmail(candidateEmail);

            var listCandidatesEmails = candidateRegistrationManager.GetEmails();

            Check.That(listCandidatesEmails[0]).IsEqualTo(candidateEmail);

        }

        [Test]
        public void AddEmail_Should_Insert_Multiples_Email_And_Return_Email_List_From_The_Candidate()
        {
            var candidateRegistrationManager = new CandidateRegistrationManager();

            candidateRegistrationManager.AddEmail(FannyDubois);

            candidateRegistrationManager.AddEmail(RegisDubois);
            
            var listCandidatesEmails = candidateRegistrationManager.GetEmails();

            Check.That(listCandidatesEmails).Contains(RegisDubois, FannyDubois ).Only().Once();
        }

        [Test]
        public void AddEmail_Should_Return_Unique_Candidate_Email_Even_If_You_Try_To_Add_Existing_Candidate_Email()
        {
            var candidateRegistrationManager = new CandidateRegistrationManager();

            candidateRegistrationManager.AddEmail(RegisDubois);

            candidateRegistrationManager.AddEmail(RegisDubois);

            var listCandidatesEmails = candidateRegistrationManager.GetEmails();

            Check.That(listCandidatesEmails).Contains(RegisDubois).Once();
        }


    }

    internal class CandidateRegistrationManager
    {
        private IList<string> candidateEmailList = new List<string>();




        public CandidateRegistrationManager(params string[] emails)
        {
            foreach (var email in emails)
            {
                this.candidateEmailList.Add(email);
            }
        }

        internal void AddEmail(string candidateEmail)
        {
            if (candidateEmailList.Contains(candidateEmail))
            {
                return;
            }
            this.candidateEmailList.Add(candidateEmail);

        }

        internal IList<string> GetEmails()
        {
            return this.candidateEmailList;
        }
    }
}
