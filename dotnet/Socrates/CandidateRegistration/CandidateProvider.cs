using Socrates.CandidateRegistration;
using System;
using System.Collections.Generic;

namespace Socrates.CandidateRegistration
{
    public class CandidateProvider
    {
      
        private IList<Candidate> candidates = new List<Candidate>();
      

        public CandidateProvider(params Candidate[] candidates)
        {
            foreach (var candidat in candidates)
            {
                this.candidates.Add(candidat);
            }
        }


        public IList<Candidate> GetCandidates()
        {
            return candidates;
        }

        public void AddCandidate(Candidate candidate)
        {
            candidates.Add(candidate);
        }

        public bool IsEmpty()
        {
            return candidates.Count == 0;
        }

        public bool ContainsCandidates(Candidate candidate)
        {
            return candidates.Contains(candidate); 
        }
    }
}
    
