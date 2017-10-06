using System;


namespace Socrates.Exceptions
{
    public class InvalidEmailException : Exception
    {

        public InvalidEmailException(string message) : base(message)
        {

        }
    }
}
    
