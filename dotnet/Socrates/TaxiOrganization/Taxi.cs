using System.Collections.Generic;

namespace Socrates.TaxiOrganization
{
    public class Taxi
    {
        private readonly string name;

        public Taxi(string name)
        {
            this.name = name;
        }

        public override bool Equals(object obj)
        {
            var taxi = obj as Taxi;
            return taxi != null &&
                   name == taxi.name;
        }

        public override int GetHashCode()
        {
            return 363513814 + 
                EqualityComparer<string>.Default.GetHashCode(name);
        }

        public override string ToString()
        {
            return name;
        }
    }
}
