using SocratesLibrary;
using System;
using System.Collections.Generic;

namespace ConsumPostgresql
{
    class Program
    {
        static CandidateRepository candidateRepository = null;

        static void Main(string[] args)
        {
            var connectionString = "Server=postgres-lacombe.westeurope.cloudapp.azure.com;User Id=learner; Password=learn;Database=playground-3;";

            candidateRepository = new CandidateRepository(connectionString);

            int choice = 0;

            do
            {
                Console.WriteLine("0 - Quitter");
                Console.WriteLine("1 - Ajouter un candidat");
                Console.WriteLine("2 - Rechercher un candidat");
                Console.WriteLine("3 - Afficher tous les candidats");

                if (int.TryParse(Console.ReadLine(), out choice))
                {
                    switch (choice)
                    {
                        case 1: Insert(); break;
                        case 2: Find(); break;
                        case 3: FindAll(); break;
                        default: Environment.Exit(0); break;

                    }
                }
            }
            while (choice != 0);
        }

        private static void Insert()
        {
            Console.WriteLine(" Email du candidat à ajouter :");

            candidateRepository.Insert(Console.ReadLine());

            Console.WriteLine(" Candidat ajouté !");
            Console.WriteLine("\n\n\n");
        }

        private static void Find()
        {
            Console.WriteLine(" Email du candidat à chercher :");

            var candidates = candidateRepository.Find(Console.ReadLine());

            Console.WriteLine(" Résultats :");
            if (candidates.Type)
            {
                var candidatesList = (List<CandidateDb>)candidates.Result;
                foreach (var candidate in candidatesList)
                {
                    Console.WriteLine(candidate);
                }
            }
            else
            {
                Console.WriteLine(candidates.Message);
            }
           

            Console.WriteLine("\n\n\n");
        }

        private static void FindAll()
        {
            Console.WriteLine(" --- Liste de tous les candidats --- \n");

            var candidates = candidateRepository.Find();

            foreach (var candidate in candidates)
            {
                Console.WriteLine(candidate);
            }

            Console.WriteLine("\n\n\n");
        }
    }
}
