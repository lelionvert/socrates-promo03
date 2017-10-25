using System.Collections.Generic;
using Socrates.CandidateRegistration;

namespace Socrates.Test.Services
{
    internal class MockCandidateProvider : ICandidateProvider
    {
        internal bool GetEmailsReturn { get; set; }
        internal bool HasAlreadyWasCalled { get; private set; }
        internal bool AddCandidateWasCalled { get; private set; }
        internal bool ContainsEmailsExactlyReturnWasCalled { get; private set; }
        internal bool HasAlreadyReturn { get; set; }
        internal bool ContainsEmailsExactlyReturn { get; set; }

        public void AddCandidate(Candidate candidate)
        {
            AddCandidateWasCalled = true;
        }

        public Emails GetEmails()
        {
            GetEmailsReturn = true;
            return null;
        }

        public bool HasAlready(params Candidate[] candidates)
        {
            HasAlreadyWasCalled = true;

            return HasAlreadyReturn;
        }

        public bool ContainsEmailsExactly(params Email[] emails)
        {
            ContainsEmailsExactlyReturnWasCalled = true;

            return ContainsEmailsExactlyReturn;
        }
    }
}