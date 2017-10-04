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

            Check.That(listCandidates).HasSize(0);
        }

        [Test]
        public void ShouldReturnOneCandidateWhenInitializedCandidateList()
        {
            var candidateRegistrationManager = new CandidateRegistrationManager("regis.dubois@socrates.com");

            var listCandidates = candidateRegistrationManager.getEmails();

            Check.That(listCandidates).HasSize(1);
            Check.That(listCandidates[0]).IsEqualTo("regis.dubois@socrates.com");
        }

        
        internal class CandidateRegistrationManager
        {
            private IList<string> array = new List<string>();

            public CandidateRegistrationManager()
            {
               
            }

            public CandidateRegistrationManager(string v)
            {

                this.array.Add(v);
            }

            internal IList<string> getEmails()
            {
                return this.array;
            }
        }
    }
}
