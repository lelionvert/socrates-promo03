using System.Linq;

namespace Socrates.CandidateRegistration
{
    public class CandidateProvider : ICandidateProvider
    {
        private Candidates candidates;
        
        public CandidateProvider(params Candidate[] candidates)
        {
            this.candidates = Candidates.FromList(candidates.ToList());
        }

        public Candidates GetCandidates()
        {
            return candidates;
        }

        public void AddCandidate(Candidate candidate)
        {
            candidates.Add(candidate);
        }

        public bool IsEmpty()
        {
            return candidates.IsEmpty();
        }

        public bool HasAlready(params Candidate[] candidates)
        {
            return this.candidates.Contains(candidates);
        }

        public bool ContainsEmailsExactly(params Email[] emails)
        {
            return candidates.ContainsEmails(emails);
        }

        public Emails GetEmails()
        {
            return candidates.GetEmails();
        }
    }
}
    
