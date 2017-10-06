using Socrates.Models;
using System;
using System.Collections.Generic;

namespace Socrates.Services
{
    public class CandidateProvider
    {
        private IList<Email> emailList = new List<Email>();

        public CandidateProvider(params Email[] emails)
        {
            foreach (var email in emails)
            {
                emailList.Add(email);
            }
        }

        public IList<Email> GetEmails()
        {
            return emailList;
        }

        internal void AddEmail(Email candidateEmail)
        {
            emailList.Add(candidateEmail);
        }
    }
}
    
