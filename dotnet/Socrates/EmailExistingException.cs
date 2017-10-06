using System;
using System.Runtime.Serialization;

namespace Socrates
{
    [Serializable]
    public class EmailExistingException : Exception
    {
        public EmailExistingException()
        {
        }

        public EmailExistingException(string message) : base(message)
        {

        }

        public EmailExistingException(string message, Exception innerException) : base(message, innerException)
        {
        }

        protected EmailExistingException(SerializationInfo info, StreamingContext context) : base(info, context)
        {
        }
    }
}