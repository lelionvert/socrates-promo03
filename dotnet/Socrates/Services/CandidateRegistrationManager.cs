using Socrates.Models;
using System.Collections.Generic;

namespace Socrates.Services
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
                throw new ExistingEmailException("This email already exists") ;
            }

            candidateProvider.AddEmail(candidateEmail);
        }

        public IList<Email> GetEmails()
        {
            return candidateProvider.GetEmails();
        }

    }
}
    
