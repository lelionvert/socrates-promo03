using System.Collections.Generic;

namespace Socrates
{
    public class CandidateRegistrationManager
    {
        private IList<Email> candidateEmailList = new List<Email>();

        public CandidateRegistrationManager(params Email[] emails)
        {
            foreach (var email in emails)
            {
                candidateEmailList.Add(email);
            }
        }

        public void AddEmail(Email candidateEmail)
        {
            if (candidateEmailList.Contains(candidateEmail))
            {
                return;
            }

            candidateEmailList.Add(candidateEmail);
        }

        public IList<Email> GetEmails()
        {
            return candidateEmailList;
        }

    }
}
    
