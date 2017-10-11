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

            Check.That(candidateProvider.HasAlreadyWasCalled).IsTrue();
        }

        [Test]
        public void Register_Should_Call_AddCandidate_When_HasAlready_Return_False()
        {
            var candidateProvider = new MockCandidateProvider();
            candidateProvider.HasAlreadyReturn = false;

            var candidateRegister = new CandidateRegister(candidateProvider);

            candidateRegister.Register(new Candidate(Email.Of(RegisDuboisEmail)));

            Check.That(candidateProvider.HasAlreadyWasCalled).IsTrue();

            Check.That(candidateProvider.AddCandidateWasCalled).IsTrue();
        }

        [Test]
        public void Register_Should_Not_Call_AddCandidate_When_HasAlready_Return_True()
        {
            var candidateProvider = new MockCandidateProvider();
            candidateProvider.HasAlreadyReturn = true;

            var candidateRegister = new CandidateRegister(candidateProvider);

            candidateRegister.Register(new Candidate(Email.Of(RegisDuboisEmail)));

            Check.That(candidateProvider.HasAlreadyWasCalled).IsTrue();

            Check.That(candidateProvider.AddCandidateWasCalled).IsFalse();
        }

        [Test]
        public void GetCandidateEmails_Of_Register_Should_Call_GetCandidateEmails_Of_Provider()
        {
            var candidateProvider = new MockCandidateProvider();
            
            var candidateRegister = new CandidateRegister(candidateProvider);
            candidateRegister.GetCandidateEmails();
            Check.That(candidateProvider.GetCandidateEmailsReturn).IsTrue();
        }

        [Test]
        public void Register_Should_Test_How_Many_Time_AddCandidate_From_Provider_Is_Called()
        {
            var candidateProvider = new SpyCandidateProvider();

            var candidateRegister = new CandidateRegister(candidateProvider);

            candidateRegister.Register(new Candidate(Email.Of(RegisDuboisEmail)));
            candidateRegister.Register(new Candidate(Email.Of(RegisDuboisEmail)));

            Check.That(candidateProvider.AddCandidateCallCount).IsEqualTo(2) ;
        }

        [Test]
        public void Register_Should_Test_How_Many_Time_HasAlready_From_Provider_Is_Called()
        {
            var candidateProvider = new SpyCandidateProvider();

            var candidateRegister = new CandidateRegister(candidateProvider);

            candidateRegister.Register(new Candidate(Email.Of(RegisDuboisEmail)));

            Check.That(candidateProvider.HasAlreadyCallCount).IsEqualTo(1);
        }

        [Test]
        public void Register_Should_Test_How_Many_Time_GetCandidateEmail_From_Provider_Is_Called()
        {
            var candidateProvider = new SpyCandidateProvider();
            var candidateRegister = new CandidateRegister(candidateProvider);

            var emailsReceiveFromTheFirstCalled = candidateRegister.GetCandidateEmails();
            var emailsReceiveFromTheSecondCalled = candidateRegister.GetCandidateEmails();

            Check.That(candidateProvider.GetCandidateCallCount).IsEqualTo(2);         
        }
    }
}

