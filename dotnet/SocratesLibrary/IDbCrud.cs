using System.Collections.Generic;
using System.Threading.Tasks;

namespace SocratesLibrary
{
    public interface IDbCrud
    {
        Task<List<CandidateDb>> findAll();
        Task<List<CandidateDb>> findByEmail(string email);
    }
}