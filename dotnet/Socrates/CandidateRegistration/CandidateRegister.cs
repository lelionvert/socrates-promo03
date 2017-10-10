using System.Collections.Generic;

namespace Socrates.CandidateRegistration
{
    public class CandidateRegister
    {
        private CandidateProvider candidateProvider;

        public CandidateRegister()
        {
            candidateProvider = new CandidateProvider();
        }

        public CandidateRegister(CandidateProvider candidateProvider)
        {
            this.candidateProvider = candidateProvider;
        }

        public IList<Candidate> Candidates => candidateProvider.GetCandidates();

        public void Register(Candidate candidate)
        {
            if (candidateProvider.GetCandidates().Contains(candidate))
            {
                throw new ExistingCandidateException("This candidate already exists");
            }
            candidateProvider.AddCandidate(candidate);
        }
    }
}
    
