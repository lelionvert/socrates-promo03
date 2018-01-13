using Socrates.CandidateRegistration;
using Socrates.Meals;
using System;
using System.Collections.Generic;

namespace Socrates.Consoles
{
    class Program
    {
        private static ColdMealsCounter coldMealsCounter;

        static void Main(string[] args)
        {
            int choice = 0;

            do
            {
                Console.WriteLine("************************************************");
                Console.WriteLine("**              SOCRATES FR                   **");
                Console.WriteLine("**              EDITION 2018                  **");
                Console.WriteLine("**            27 Octobre 2018                 **");
                Console.WriteLine("************************************************");
                Console.WriteLine("**                                            **");
                Console.WriteLine("** 1 - Ajouter des participants               **");
                Console.WriteLine("** 2 - Afficher le nombre de repas froid      **");
                Console.WriteLine("** 0 - Quitter                                **");
                Console.WriteLine("**                                            **");
                Console.WriteLine("************************************************");

                string saisie = Console.ReadLine();

                if (int.TryParse(saisie, out choice))
                {
                    switch (choice)
                    {
                        case 1: AddCheckin(); break;
                        case 2: ShowColdMealsNumber(); break;
                        default: Environment.Exit(0); break;
                    }
                }
            }
            while (choice != 0);
        }

        private static void ShowColdMealsNumber()
        {
            Console.WriteLine("\n");

            if (coldMealsCounter == null)
            {
                Console.WriteLine("Aucun participant n'a été ajouté.");
            }
            else
            {
                Console.WriteLine($"{coldMealsCounter.CountColdMeals()} repas froid");
            }

            Console.WriteLine("\n\n\n");
        }

        private static void AddCheckin()
        {
            Console.WriteLine("\n");

            var checkins = new List<Checkin>();

            var participantNumber = 1;
            var addNewParticipantCheckin = false;
            do
            {
                var isEmailValid = false;
                Email email = null;

                do
                {
                    Console.WriteLine($" Email du participant n°{participantNumber} : ");
                    string emailParticipant = Console.ReadLine();

                    try
                    {
                        email = Email.Of(emailParticipant);

                        isEmailValid = true;
                    }
                    catch (InvalidEmailException invalidEmailException)
                    {
                        Console.WriteLine($"{invalidEmailException.Message}, veuillez resaisir l'adresse email du participant");
                    }
                }
                while (isEmailValid == false);

                var isCheckinDateValid = false;
                do
                {
                    Console.WriteLine($" Date de checkin du participant n°{participantNumber} - le format yyyy/mm/dd hh:mm:ss  ");
                    DateTime checkinDate;

                    if (DateTime.TryParse(Console.ReadLine(), out checkinDate))
                    {
                        var checkin = new Checkin(email, checkinDate);
                        checkins.Add(checkin);

                        isCheckinDateValid = true;
                    }
                    else
                    {
                        Console.WriteLine(" Date incorrect !");
                    }
                }
                while (isCheckinDateValid == false);

                Console.WriteLine("Voulez-vous ajouter un autre participant ? [O/N]");
                var responseAddNewParticipant = Console.ReadLine();

                if (responseAddNewParticipant.Equals("O"))
                {
                    addNewParticipantCheckin = true;
                }
                else
                {
                    addNewParticipantCheckin = false;
                }

                participantNumber++;
            }
            while (addNewParticipantCheckin == true);

            var checkinProvider = new CheckinProvider(checkins.ToArray());

            coldMealsCounter = new ColdMealsCounter(checkinProvider);

            Console.WriteLine("\n\n\n");
        }
    }
}