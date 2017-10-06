using Socrates.US1_CandidateRegistration;
using Socrates.US1_CandidateRegistration;
using System.Collections.Generic;
using System;

namespace Socrates.US1_CandidateRegistration
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

        public IList<Candidate> Candidates => candidateProvider.GetCandidats();

        public IList<Email> GetEmails()
        {
            return candidateProvider.GetEmails();
        }

       
    }
}
    
