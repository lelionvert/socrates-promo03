namespace Socrates.TaxiOrganization
{
    public class Participant
    {
        private readonly string name;

        public Participant(string name)
        {
            this.name = name;
        }

        public string GetName()
        {
            return name;
        }
    }
}
