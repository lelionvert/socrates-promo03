using System.Collections.Generic;

namespace Socrates
{
    public class CandidateRegistrationManager
    {

        private CandidateProvider candidateProvider;

        public CandidateRegistrationManager()
        {
            candidateProvider = new CandidateProvider();
        }

        

        public CandidateRegistrationManager(CandidateProvider candidateProvider)
        {
            this.candidateProvider = candidateProvider;
        }

        public void AddEmail(Email candidateEmail)
        {
            if (candidateProvider.GetEmails().Contains(candidateEmail))
            {
                throw new EmailExistingException("This email already exists") ;
            }

            candidateProvider.AddEmail(candidateEmail);
        }

        public IList<Email> GetEmails()
        {
            return candidateProvider.GetEmails();
        }

    }
}
    
