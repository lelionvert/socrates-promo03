using System.Collections.Generic;
using Socrates.CandidateRegistration;

namespace Socrates.Test.Services
{
    internal class MockCandidateProvider : ICandidateProvider
    {
        internal bool GetCandidateEmailsReturn { get; set; }
        internal bool HasAlreadyWasCalled { get; private set; }
        internal bool AddCandidateWasCalled { get; private set; }
        internal bool HasAlreadyReturn { get; set; }
       

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