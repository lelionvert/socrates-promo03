using System.Collections.Generic;

namespace Socrates
{
    public class CandidateRegistrationManager
    {
        private IList<Email> candidateEmailList = new List<Email>();

        public CandidateRegistrationManager()
        {

        }

        

        public CandidateRegistrationManager(CandidateProvider candidateProvider)
        {
            candidateEmailList = candidateProvider.GetEmails();
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
    
