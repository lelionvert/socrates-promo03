using System;
using System.Collections.Generic;
using System.Linq;

namespace Socrates.CandidateRegistration
{
    public class Candidates
    {

        private IList<Candidate> values;
        

        private Candidates(IList<Candidate> list)
        {
            values = list;
        }

        public static Candidates FromList(IList<Candidate> list)
        {
            return new Candidates(list);
        }

        public int CountCheckins()
        {
            return values.Count;
        }

        public void Add(Candidate candidate)
        {
            values.Add(candidate);
        }

        public bool IsEmpty()
        {
            return values.Count == 0;
        }

        public bool Contains(params Candidate[] candidates)
        {
            return candidates.All(this.values.Contains) && this.values.All(candidates.Contains);

        }

        public Emails GetEmails()
        {
           List<Email> candidateEmails = values.Select(candidate => candidate.Email).ToList();
           return Emails.FromList(candidateEmails);
        }

        internal bool ContainsEmails(Email[] emails)
        {
            return values.Select(c => c.Email).Intersect(emails).Any() && emails.Intersect(values.Select(c => c.Email)).Any();
        }
    }
}