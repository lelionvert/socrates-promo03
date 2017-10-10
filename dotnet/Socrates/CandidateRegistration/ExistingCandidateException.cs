using System;
using System.Runtime.Serialization;

namespace Socrates.CandidateRegistration
{
    [Serializable]
    public class ExistingCandidateException : Exception
    {
        public ExistingCandidateException()
        {
        }

        public ExistingCandidateException(string message) : base(message)
        {

        }

        public ExistingCandidateException(string message, Exception innerException) : base(message, innerException)
        {
        }

        protected ExistingCandidateException(SerializationInfo info, StreamingContext context) : base(info, context)
        {
        }
    }
}