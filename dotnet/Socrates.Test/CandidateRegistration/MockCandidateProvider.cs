using System.Collections.Generic;
using Socrates.CandidateRegistration;

namespace Socrates.Test.Services
{
    internal class MockCandidateProvider : ICandidateProvider
    {
        public bool hasAlreadyWasCalled { get; private set; };

        public void AddCandidate(Candidate candidate)
        {
            throw new System.NotImplementedException();
        }

        public IList<Email> GetCandidateEmails()
        {
            throw new System.NotImplementedException();
        }

        public bool HasAlready(params Candidate[] candidates)
        {
            hasAlreadyWasCalled = true;

            return true;
        }
    }
}