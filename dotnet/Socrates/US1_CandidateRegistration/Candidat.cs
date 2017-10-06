using Socrates.US1_CandidateRegistration;

namespace Socrates.US1_CandidateRegistration
{
    public class Candidate
    {
        public Email Email { get; private set; }

        public Candidate(Email email)
        {
            Email = email;
        }

       
    }
}
    
