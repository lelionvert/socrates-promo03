using System.Collections.Generic;

namespace Socrates.CandidateRegistration
{
    public class Emails
    {
        private List<Email> candidateEmails;

        private Emails(List<Email> candidateEmails)
        {
            this.candidateEmails = candidateEmails;
        }

        public static Emails FromList(List<Email> candidateEmails)
        {
            return new Emails(candidateEmails);
        }

        public IEnumerable<Email> ToList()
        {
            foreach (var email in candidateEmails)
            {
                yield return email;
            }
        }

        public bool IsEmpty()
        {
            return candidateEmails.Count == 0;
        }
    }
}