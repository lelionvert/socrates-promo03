using System.Collections.Generic;

namespace Socrates.CandidateRegistration
{
    public class Candidate
    {
        public Email Email { get; private set; }

        public Candidate(Email email)
        {
            Email = email;
        }

        public override bool Equals(object obj)
        {
            var candidate = obj as Candidate;
            return candidate != null &&
                   EqualityComparer<Email>.Default.Equals(Email, candidate.Email);
        }

        public override int GetHashCode()
        {
            return -506688385 + EqualityComparer<Email>.Default.GetHashCode(Email);
        }
    }
}
    
