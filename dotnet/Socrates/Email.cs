using System;
using System.Collections.Generic;

namespace Socrates
{
    public class Email
    {
        private string mailAdress;

        public Email(string _mailAdress)
        {
            mailAdress = _mailAdress;
        }

        public static Email Of(string _mailAdress)
        {
            if (String.IsNullOrEmpty(_mailAdress))
            {
                throw new InvalidEmailException($"The Candidate email address can't be empty");
            }
            return new Email(_mailAdress);
        }

        public override bool Equals(object obj)
        {
            var email = obj as Email;
            return email != null &&
                   mailAdress == email.mailAdress;
        }

        public override int GetHashCode()
        {
            return -358254024 + EqualityComparer<string>.Default.GetHashCode(mailAdress);
        }
    }
}
    
