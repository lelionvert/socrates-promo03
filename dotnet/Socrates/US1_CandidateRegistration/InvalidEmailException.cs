using System;


namespace Socrates.US1_CandidateRegistration
{
    public class InvalidEmailException : Exception
    {

        public InvalidEmailException(string message) : base(message)
        {

        }
    }
}
    
