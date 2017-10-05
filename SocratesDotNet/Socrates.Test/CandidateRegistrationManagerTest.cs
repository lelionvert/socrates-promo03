using NFluent;
using NUnit.Framework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Socrates.Test
{
    [TestFixture]
    public class CandidateRegistrationManagerTest
    {
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
            var candidateRegistrationManager = new CandidateRegistrationManager("regis.dubois@socrates.com");

            var listCandidatesEmails = candidateRegistrationManager.GetEmails();

            Check.That(listCandidatesEmails).HasSize(1);
            Check.That(listCandidatesEmails[0]).IsEqualTo("regis.dubois@socrates.com");
        }

        [Test]
        public void AddEmail_Should_Return_The_Email_Previously_Added()
        {
            var candidateRegistrationManager = new CandidateRegistrationManager();

            var candidateEmail = "regis.dubois@socrates.com";

            candidateRegistrationManager.AddEmail(candidateEmail);

            var listCandidatesEmails = candidateRegistrationManager.GetEmails();

            Check.That(listCandidatesEmails[0]).IsEqualTo("regis.dubois@socrates.com");

        }

        internal class CandidateRegistrationManager
        {
            private IList<string> candidateEmailList = new List<string>();

            public CandidateRegistrationManager()
            {
               
            }

            public CandidateRegistrationManager(params string[] emails)
            {
                foreach (var email in emails)
                {
                    this.candidateEmailList.Add(email);
                }
            }

            internal void AddEmail(string candidateEmail)
            {
                this.candidateEmailList.Add(candidateEmail);
            }

            internal IList<string> GetEmails()
            {
                return this.candidateEmailList;
            }
        }
    }
}
