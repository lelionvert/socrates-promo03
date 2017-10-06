using System;

namespace Socrates
{
    public class InvalidEmailException : Exception
    {

        public InvalidEmailException(string message) : base(message)
        {

        }
    }
}
    
