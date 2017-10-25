using System;

namespace Socrates.CandidateRegistration
{
    public class InvalidEmailException : Exception
    {
        public InvalidEmailException(string message) : base(message)
        {
        }
    }
}
    
