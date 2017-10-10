
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
            : this()
        {
            if (candidateProvider != null)
            {
                this.candidateProvider = candidateProvider;
            }
            
        }

       

        public   IList<Candidate> Candidates => candidateProvider.GetCandidates();

       

        public void Register(Candidate candidate)
        {
            if (candidateProvider.Exists(candidate))
            {
                throw new ExistingCandidateException("This candidate already exists");
            }
            candidateProvider.AddCandidate(candidate);
        }
    }
}
    
