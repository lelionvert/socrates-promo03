using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SocratesLibrary
{
    public class RepositoryDb : IDbCrud
    {
        private readonly CandidateContext candidate;

        public RepositoryDb(string postGresConnection)
        {
            this.candidate = new CandidateContext(postGresConnection);
        }

        public async Task<List<CandidateDb>> findAll()
        {
            return await candidate.Candidates.ToListAsync();
     
        }

        public async Task<List<CandidateDb>> findByEmail(string email)
        {
            return await candidate.Candidates.Where(c => c.Email.Equals(email)).ToListAsync();

        }
    }
}
