using Socrates.US1_CandidateRegistration;
using System;
using System.Collections.Generic;
using System.Text.RegularExpressions;

namespace Socrates.US1_CandidateRegistration
{
    public class Email
    {
        private string _emailAddress;

        public Email(string emailAddress)
        {
            if (IsValid(emailAddress)==false)
            {
                throw new InvalidEmailException("Email format is invalid");
            }
            _emailAddress = emailAddress;
        }

        private bool IsValid(string emailAddress)
        {
            if (String.IsNullOrEmpty(emailAddress))
            {
                return false;
            }

            Regex regex = new Regex(@"^([\w\.\-]+)@([\w\-]+)((\.(\w){2,3})+)$");
            Match match = regex.Match(emailAddress);

            return match.Success;
        }

        public static Email Of(string emailAddress)
        {
            
            return new Email(emailAddress);
        }



        public override bool Equals(object obj)
        {
            var email = obj as Email;
            return email != null &&
                   _emailAddress == email._emailAddress;
        }

        public override int GetHashCode()
        {
            return -358254024 + EqualityComparer<string>.Default.GetHashCode(_emailAddress);
        }
    }
}
    
