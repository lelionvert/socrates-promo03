using System.Collections.Generic;
using Socrates.CandidateRegistration;

namespace Socrates.Test.Services
{
    internal class SpyCandidateProvider : ICandidateProvider
    {
        internal int AddCandidateCallCount {  get; private set; }

       

        public void AddCandidate(Candidate candidate)
        {
            AddCandidateCallCount++;
        }

        public IList<Email> GetCandidateEmails()
        {
            throw new System.NotImplementedException();
        }

        public bool HasAlready(params Candidate[] candidates)
        {
            return false;
        }
    }
}