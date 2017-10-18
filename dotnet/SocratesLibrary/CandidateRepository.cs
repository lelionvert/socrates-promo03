using Npgsql;
using System.Collections;
using System.Collections.Generic;
using System.Data;

namespace SocratesLibrary
{
    public class CandidateRepository
    {
        private readonly string connectionString;

        public CandidateRepository(string connectionString)
        {
            this.connectionString = connectionString;
        }

        private IDbConnection OpenConnexion()
        {
            var conn = new NpgsqlConnection(this.connectionString);
            conn.Open();

            return conn;
        }

        public IList<CandidateDb> Find()
        {
            using (var conn = OpenConnexion())
            {
                using (IDbCommand dbcmd = conn.CreateCommand())
                {
                    string sql = "select * from \"Gabriel\".candidates";
                    dbcmd.CommandText = sql;

                    using (IDataReader reader = dbcmd.ExecuteReader())
                    {
                        var candidates = new List<CandidateDb>();

                        while (reader.Read())
                        {
                            candidates.Add(
                                new CandidateDb()
                                {
                                    Id = reader.GetInt32(0),
                                    Email = reader.GetString(1)
                                });
                        }

                        return candidates;
                    }
                }
            }
        }

        public DBResponse Find(string email)
        {
            using (var conn = OpenConnexion())
            {
                using (IDbCommand dbcmd = conn.CreateCommand())
                {
                    string sql = "select * from \"Gabriel\".candidates where email like @email";
                    dbcmd.CommandText = sql;

                    var emailParameter = dbcmd.CreateParameter();
                    emailParameter.ParameterName = "@email";
                    emailParameter.Value = $"%{email}%";

                    dbcmd.Parameters.Add(emailParameter);

                    using (IDataReader reader = dbcmd.ExecuteReader())
                    {
                        var candidates = new List<CandidateDb>();

                        while (reader.Read())
                        {
                            candidates.Add(
                                new CandidateDb()
                                {
                                    Id = reader.GetInt32(0),
                                    Email = reader.GetString(1)
                                });
                        }

                        if (candidates.Count != 0)
                        {
                            return new DBResponse()
                            {
                                Type = true,
                                Message = "Succès",
                                Result = candidates
                            };
                        }
                        else
                        {
                            return new DBResponse()
                            {
                                Type = false,
                                Message = "Erreur aucun candidat",
                                Result = candidates
                            };
                        }



                    }
                }
            }
        }

        public void Insert(string email)
        {
            using (var conn = OpenConnexion())
            {
                using (IDbCommand dbcmd = conn.CreateCommand())
                {
                    string sql = "insert into \"Gabriel\".candidates (email) values(@email)";
                    dbcmd.CommandText = sql;

                    var emailParameter = dbcmd.CreateParameter();
                    emailParameter.ParameterName = "@email";
                    emailParameter.Value = email;

                    dbcmd.Parameters.Add(emailParameter);

                    dbcmd.ExecuteNonQuery();
                }
            }
        }
    }
}
