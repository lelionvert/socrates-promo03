using NFluent;
using NUnit.Framework;
using System;
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
            var candidateRegistrationManager = new CandidateRegistrationManager(Email.Of(RegisDubois));

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
            candidateRegistrationManager.AddEmail(Email.Of(candidateEmail));

            var listCandidatesEmails = candidateRegistrationManager.GetEmails();

            Check.That(listCandidatesEmails).Contains(Email.Of(candidateEmail)).Only().Once();
        }

        [Test]
        public void AddEmail_Should_Add_One_Candidate_Email_When_Multiples_Candidate_Email_Exists()
        {
            var candidateRegistrationManager = new CandidateRegistrationManager(Email.Of(RegisDubois), Email.Of(FannyDubois));
            candidateRegistrationManager.AddEmail(Email.Of(JulieFournier));

            var listCandidatesEmails = candidateRegistrationManager.GetEmails();
            Check.That(listCandidatesEmails).Contains(Email.Of(RegisDubois), Email.Of(FannyDubois), Email.Of(JulieFournier)).Only().Once();
        }

        [TestCase(JulieMarechal, JulieFournier)]
        public void AddEmail_Should_Add_Multiple_Candidate_Emails_When_Multiples_Candidate_Email_Exists(string firstCandidateEmail, string secondCandidateEmail)
        {
            var candidateRegistrationManager = new CandidateRegistrationManager(Email.Of(RegisDubois), Email.Of(FannyDubois));

            candidateRegistrationManager.AddEmail(Email.Of(firstCandidateEmail));
            candidateRegistrationManager.AddEmail(Email.Of(secondCandidateEmail));

            var listCandidatesEmails = candidateRegistrationManager.GetEmails();
            Check.That(listCandidatesEmails).Contains(Email.Of(RegisDubois), Email.Of(FannyDubois), Email.Of(firstCandidateEmail), Email.Of(secondCandidateEmail)).Only().Once();
        }

        [Test]
        public void AddEmail_Should_Throw_An_Exception_When_The_Candidate_Email_Adress_Is_Invalid()
        {
            Check.ThatCode(() =>
            {
                var candidateRegistrationManager = new CandidateRegistrationManager();
                candidateRegistrationManager.AddEmail(Email.Of(String.Empty));
            }).Throws<InvalidEmailException>();
        }
    }

    public class InvalidEmailException : Exception
    {

        public InvalidEmailException(string message) : base(message)
        {

        }
    }

    public class Email
    {
        private string mailAdress;

        public Email(string _mailAdress)
        {
            mailAdress = _mailAdress;
        }

        public static Email Of(string _mailAdress)
        {
            if (String.IsNullOrEmpty(_mailAdress))
            {
                throw new InvalidEmailException($"The Candidate email address can't be empty");
            }
            return new Email(_mailAdress);
        }


        public void Test() { }
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
    
