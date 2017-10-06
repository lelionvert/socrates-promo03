using System;
using System.Runtime.Serialization;

namespace Socrates.Exceptions
{
    [Serializable]
    public class ExistingEmailException : Exception
    {
        public ExistingEmailException()
        {
        }

        public ExistingEmailException(string message) : base(message)
        {

        }

        public ExistingEmailException(string message, Exception innerException) : base(message, innerException)
        {
        }

        protected ExistingEmailException(SerializationInfo info, StreamingContext context) : base(info, context)
        {
        }
    }
}