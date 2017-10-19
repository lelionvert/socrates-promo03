using SocratesLibrary;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ConsumEntityFramework
{
    class Program
    {
        static void Main(string[] args)
        {
            var connectionString = "Server=postgres-lacombe.westeurope.cloudapp.azure.com;User Id=learner; Password=learn;Database=playground-3;";
            /*
            using (var db = new CandidateContext(connectionString))
            {
               var candidateResearched = db.Candidates.Where(c => c.Email.Contains("gab"));
                
                foreach (var candidate in candidateResearched)
                {
                    Console.WriteLine(candidate);
                }

                db.Candidates.Add(new CandidateDb() { Email = "gabyzaaf@toto.com" });
                db.SaveChanges();
                Console.WriteLine("******************************");
                var candidateResearchedNewEmail = db.Candidates.Where(c => c.Email.Contains("gab"));

                foreach (var candidate in candidateResearchedNewEmail)
                {
                    Console.WriteLine($"---> {candidate} ");
                }
                Console.WriteLine("******************************");

                Console.ReadLine(); 
            }
            */
            RepositoryDb repositoryDb = new RepositoryDb(connectionString);
            repositoryDb.findAll().Result.ForEach(c => Console.WriteLine(c.Email));
            repositoryDb.findByEmail("gabriel@lcdlv.fr").Result.ForEach(c => Console.WriteLine($" {c.Id} - {c.Email}"));
            

        }

        
    }
}
