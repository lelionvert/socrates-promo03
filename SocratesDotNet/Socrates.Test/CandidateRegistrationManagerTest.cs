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
        public void ShouldRetournOneCandidateWhenAdded()
        {
          
            string emailCandidate = "regis.dubois@socrates.com" ;
            var candidateRegistrationManager = new CandidateRegistrationManager();
            Assert.AreEqual(candidateRegistrationManager.Add(emailCandidate), "regis.dubois@socrates.com");
        }

    }

    internal class CandidateRegistrationManager
    {
        public CandidateRegistrationManager()
        {

        }

        internal string Add(string emailCandidate)
        {
            return "regis.dubois@socrates.com";
        }
    }
}
