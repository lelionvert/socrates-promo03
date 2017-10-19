using System.ComponentModel.DataAnnotations.Schema;

namespace SocratesLibrary
{
    [Table("candidates", Schema = "Gabriel")]
    public class CandidateDb
    {
        [Column("id")]
        public int Id { get; set; }

        [Column("email")]
        public string Email { get; set; }

        public override string ToString()
        {
            return $"{Id} - {Email}";
        }
    }
}