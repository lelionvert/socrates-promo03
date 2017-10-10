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
    
