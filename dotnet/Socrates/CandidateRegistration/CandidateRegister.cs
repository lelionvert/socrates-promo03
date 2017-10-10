namespace Socrates.CandidateRegistration
{
    public class CandidateRegister
    {
        private ICandidateProvider candidateProvider;

        public CandidateRegister(ICandidateProvider candidateProvider)
        {
            this.candidateProvider = candidateProvider;
        }

        public void Register(Candidate candidate)
        {
            if (candidateProvider.HasAlready(candidate))
            {
                return;
            }
            candidateProvider.AddCandidate(candidate);
        }
    }
}
    
