using System.Collections.Generic;

namespace Socrates.CandidateRegistration
{
    public interface ICandidateProvider
    {
        bool HasAlready(params Candidate[] candidates);

        void AddCandidate(Candidate candidate);

        IList<Email> GetCandidateEmails();
    }
}