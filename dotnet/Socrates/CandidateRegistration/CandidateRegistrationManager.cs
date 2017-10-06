
using System.Collections.Generic;


namespace Socrates.CandidateRegistration
{
    public class CandidateRegistrationManager
    {

        private CandidateProvider candidateProvider;

        public CandidateRegistrationManager()
        {
            candidateProvider = new CandidateProvider();
        }

        public CandidateRegistrationManager(CandidateProvider candidateProvider)
        {
            this.candidateProvider = candidateProvider;
        }

       

        public IList<Candidate> Candidates => candidateProvider.GetCandidates();

       

        public void Add(Candidate candidate)
        {
            if (candidateProvider.GetCandidates().Contains(candidate))
            {
                throw new ExistingEmailException("This candidate already exists");
            }
            candidateProvider.AddCandidate(candidate);
        }
    }
}
    
