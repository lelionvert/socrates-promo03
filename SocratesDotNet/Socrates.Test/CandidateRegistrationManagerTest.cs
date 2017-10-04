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
        public void ShouldReturnNoCandidate()
        {
            var candidateRegistrationManager = new CandidateRegistrationManager();

            var listCandidates = candidateRegistrationManager.getEmails();

            Check.That(listCandidates).IsNull();
        }


        internal class CandidateRegistrationManager
        {
            public CandidateRegistrationManager()
            {

            }

            internal object getEmails()
            {
                return null;
            }
        }
    }
}
