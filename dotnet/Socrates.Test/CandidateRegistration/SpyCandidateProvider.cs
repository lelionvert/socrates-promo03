using System.Collections.Generic;
using Socrates.CandidateRegistration;

namespace Socrates.Test.Services
{
    internal class SpyCandidateProvider : ICandidateProvider
    {
        internal int GetEmailsCallCount { get; set; }
        internal int HasAlreadyCallCount { get; private set; }
        internal int AddCandidateCallCount {  get; private set; }

        public void AddCandidate(Candidate candidate)
        {
            AddCandidateCallCount++;
        }

        public bool ContainsEmailsExactly(params Email[] emails)
        {
            throw new System.NotImplementedException();
        }

        public Emails GetEmails()
        {
            GetEmailsCallCount++;
            return null;
        }

        public bool HasAlready(params Candidate[] candidates)
        {
            HasAlreadyCallCount++;
            return false;
        }
    }
}