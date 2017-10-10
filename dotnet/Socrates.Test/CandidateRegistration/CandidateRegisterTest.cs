using NFluent;
using NUnit.Framework;
using Socrates.CandidateRegistration;

namespace Socrates.Test.Services
{
    public class CandidateRegisterTest
    {
        private const string RegisDuboisEmail = "regis.dubois@socrates.com";
        private const string FannyDuboisEmail = "fanny.dubois@crafts.com";
        private const string JulieFournierEmail = "jules.fournier@xp.com";
        private const string JulieMarechalEmail = "julie.marechal@microsoft.com";

        [Test]
        public void Register_Should_Call_HasAlready()
        {
            var candidateProvider = new MockCandidateProvider();
            var candidateRegister = new CandidateRegister(candidateProvider);
            
            candidateRegister.Register(new Candidate(Email.Of(RegisDuboisEmail)));

            Check.That(candidateProvider.hasAlreadyWasCalled).IsTrue();
        }

        [Test]
        public void Register_Should_Call_AddCandidate_When_HasAlready_Return_False()
        {
            var candidateProvider = new MockCandidateProvider();
            var candidateRegister = new CandidateRegister(candidateProvider);

            candidateRegister.Register(new Candidate(Email.Of(RegisDuboisEmail)));

            Check.That(candidateProvider.addCandidateWasCalled).IsTrue();
        }
        
    }
}

