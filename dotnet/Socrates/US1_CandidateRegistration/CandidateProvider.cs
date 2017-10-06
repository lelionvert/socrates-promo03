using Socrates.US1_CandidateRegistration;
using System;
using System.Collections.Generic;

namespace Socrates.US1_CandidateRegistration
{
    public class CandidateProvider
    {
        private IList<Email> emailList = new List<Email>();
        private IList<Candidate> candidatsList = new List<Candidate>();
      

        public CandidateProvider(params Email[] emails)
        {
            foreach (var email in emails)
            {
                emailList.Add(email);
            }
        }

        public CandidateProvider(Candidate candidat)
        {
            candidatsList.Add(candidat);
        }

        public IList<Email> GetEmails()
        {
            return emailList;
        }

        public void AddEmail(Email candidateEmail)
        {
            emailList.Add(candidateEmail);
        }

        public IList<Candidate> GetCandidats()
        {
            return candidatsList;
        }
    }
}
    
