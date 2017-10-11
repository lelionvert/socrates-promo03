using System.Collections.Generic;
using Socrates.CandidateRegistration;

namespace Socrates.Test.Services
{
    internal class MockCandidateProvider : ICandidateProvider
    {
        public bool GetCandidateEmailsReturn { get; set; }
        public bool HasAlreadyWasCalled { get; private set; }
        public bool AddCandidateWasCalled { get; private set; }
        public bool HasAlreadyReturn { get; internal set; }

        public void AddCandidate(Candidate candidate)
        {
            AddCandidateWasCalled = true;
        }

        public IList<Email> GetCandidateEmails()
        {
            GetCandidateEmailsReturn = true;
            return null;
        }

        public bool HasAlready(params Candidate[] candidates)
        {
            HasAlreadyWasCalled = true;

            return HasAlreadyReturn;
        }
    }
}