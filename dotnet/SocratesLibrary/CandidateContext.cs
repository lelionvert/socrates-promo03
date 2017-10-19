using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;

namespace SocratesLibrary
{
    public class CandidateContext : DbContext
    {
        public DbSet<CandidateDb> Candidates {  get; set; }
        private string connectionString;

        

        public CandidateContext(string connectionString)
        {
               this.connectionString = connectionString;  
        }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseNpgsql(connectionString);
            LoggerFactory loggerFactory = new LoggerFactory();
            loggerFactory.AddProvider(new TraceLoggerProvider());
            optionsBuilder.UseLoggerFactory(loggerFactory);
        }
        
       

    }
}
