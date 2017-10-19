namespace Socrates.CandidateRegistration
{
    public interface ICandidateProvider
    {
        bool HasAlready(params Candidate[] candidates);

        void AddCandidate(Candidate candidate);

        bool ContainsEmailsExactly(params Email[] emails);

        Emails GetEmails();
    }
}