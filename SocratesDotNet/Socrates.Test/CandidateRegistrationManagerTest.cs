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
        private const string JulieFournier = "jules.fournier@xp.com";
        private const string JulieMarechal = "julie.marechal@microsoft.com";

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
            var candidateRegistrationManager = new CandidateRegistrationManager(new Email(RegisDubois));

            var listCandidatesEmails = candidateRegistrationManager.GetEmails();

            Check.That(listCandidatesEmails).ContainsExactly(new Email(RegisDubois));
        }
        
        [TestCase(RegisDubois)]
        [TestCase(FannyDubois)]
        public void AddEmail_Should_Return_The_Email_Previously_Added(string candidateEmail)
        {
            var candidateRegistrationManager = new CandidateRegistrationManager();

            candidateRegistrationManager.AddEmail(new Email(candidateEmail));

            var listCandidatesEmails = candidateRegistrationManager.GetEmails();

            Check.That(listCandidatesEmails[0]).IsEqualTo(new Email(candidateEmail));
        }

        [Test]
        public void AddEmail_Should_Insert_Multiples_Email_And_Return_Email_List_From_The_Candidate()
        {
            var candidateRegistrationManager = new CandidateRegistrationManager();

            candidateRegistrationManager.AddEmail(new Email(FannyDubois));

            candidateRegistrationManager.AddEmail(new Email(RegisDubois));
            
            var listCandidatesEmails = candidateRegistrationManager.GetEmails();

            Check.That(listCandidatesEmails).Contains(new Email(RegisDubois), new Email(FannyDubois) ).Only().Once();
        }

        [TestCase(RegisDubois)]
        [TestCase(FannyDubois)]
        public void AddEmail_Should_Return_Unique_Candidate_Email_Even_If_You_Try_To_Add_Existing_Candidate_Email(string candidateEmail)
        {
            var candidateRegistrationManager = new CandidateRegistrationManager();

            candidateRegistrationManager.AddEmail(new Email(candidateEmail));
            candidateRegistrationManager.AddEmail(new Email(candidateEmail));

            var listCandidatesEmails = candidateRegistrationManager.GetEmails();

            Check.That(listCandidatesEmails).Contains(new Email(candidateEmail)).Only().Once();
        }

        [Test]
        public void AddEmail_Should_Add_One_Candidate_Email_When_Multiples_Candidate_Email_Exists()
        {
            var candidateRegistrationManager = new CandidateRegistrationManager(new Email(RegisDubois), new Email(FannyDubois));
            candidateRegistrationManager.AddEmail(new Email(JulieFournier));

            var listCandidatesEmails = candidateRegistrationManager.GetEmails();
            Check.That(listCandidatesEmails).Contains(new Email(RegisDubois), new Email(FannyDubois), new Email(JulieFournier)).Only().Once();
        }

        [TestCase(JulieMarechal, JulieFournier)]
        public void AddEmail_Should_Add_Multiple_Candidate_Emails_When_Multiples_Candidate_Email_Exists(string firstCandidateEmail, string secondCandidateEmail)
        {
            var candidateRegistrationManager = new CandidateRegistrationManager(new Email(RegisDubois), new Email(FannyDubois));

            candidateRegistrationManager.AddEmail(new Email(firstCandidateEmail));
            candidateRegistrationManager.AddEmail(new Email(secondCandidateEmail));

            var listCandidatesEmails = candidateRegistrationManager.GetEmails();
            Check.That(listCandidatesEmails).Contains(new Email(RegisDubois), new Email(FannyDubois), new Email(firstCandidateEmail), new Email(secondCandidateEmail)).Only().Once();
        }
    }
    
    public class Email
    {
        private string mailAdress;

        public Email(string _mailAdress)
        {
            mailAdress = _mailAdress;
        }

        public override bool Equals(object obj)
        {
            var email = obj as Email;
            return email != null &&
                   mailAdress == email.mailAdress;
        }

        public override int GetHashCode()
        {
            return -358254024 + EqualityComparer<string>.Default.GetHashCode(mailAdress);
        }
    }

    public class CandidateRegistrationManager
    {
        private IList<Email> candidateEmailList = new List<Email>();

        public CandidateRegistrationManager(params Email[] emails)
        {
            foreach (var email in emails)
            {
                candidateEmailList.Add(email);
            }
        }

        internal void AddEmail(Email candidateEmail)
        {
            if (candidateEmailList.Contains(candidateEmail))
            {
                return;
            }

            candidateEmailList.Add(candidateEmail);
        }

        internal IList<Email> GetEmails()
        {
            return candidateEmailList;
        }

    }
}
    
