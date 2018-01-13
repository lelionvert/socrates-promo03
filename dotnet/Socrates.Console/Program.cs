using Socrates.CandidateRegistration;
using Socrates.Meals;
using Socrates.TaxiOrganization;
using System;
using System.Collections.Generic;

namespace Socrates.Consoles
{
    class Program
    {
        private static CandidateRegister candidateRegister;
        private static CheckinProvider checkinProvider;
        private static ColdMealsCounter coldMealsCounter;
        private static List<TaxiOrganizer> taxiOrganizers;

        static void Main(string[] args)
        {
            Init();

            int choice = 0;

            do
            {
                Console.WriteLine("************************************************");
                Console.WriteLine("**              SOCRATES FR                   **");
                Console.WriteLine("**            27 Octobre 2018                 **");
                Console.WriteLine("************************************************");
                Console.WriteLine("**                                            **");
                Console.WriteLine("** 1 - Ajouter un candidat                    **");
                Console.WriteLine("** 2 - Récupérer la liste des emails candidat **");
                Console.WriteLine("** 3 - Ajouter une arrivee de participant     **");
                Console.WriteLine("** 4 - Compter le nombre de repas froids      **");
                Console.WriteLine("** 5 - Réserver des taxis                     **");
                Console.WriteLine("** 6 - Voir les réservations de taxis         **");
                Console.WriteLine("** 0 - Quitter                                **");
                Console.WriteLine("**                                            **");
                Console.WriteLine("************************************************");

                string saisie = Console.ReadLine();

                if (int.TryParse(saisie, out choice))
                {
                    switch (choice)
                    {
                        case 1:
                            AddCandidate();
                            break;
                        case 2:
                            ShowCandidatesEmail();
                            break;
                        case 3:
                            AddCheckin();
                            break;
                        case 4:
                            ShowCountColdMeals();
                            break;
                        case 5:
                            BookTaxi();
                            break;
                        case 6:
                            ShowTaxiBookings();
                            break;
                        default: Environment.Exit(0); break;
                    }
                }
            }
            while (choice != 0);
        }

        private static void Init()
        {
            var candidateProvider = new CandidateProvider();
            candidateRegister = new CandidateRegister(candidateProvider);

            checkinProvider = new CheckinProvider();

            taxiOrganizers = new List<TaxiOrganizer>();
        }

        private static void ShowTaxiBookings()
        {
            Console.WriteLine("\n");

            if (taxiOrganizers.Count == 0)
            {
                Console.WriteLine("Aucun taxi n'a été réservé.\n");
                Console.WriteLine("\n\n\n");

                return;
            }

            foreach (var taxiOrganizer in taxiOrganizers)
            {
                var bookings = taxiOrganizer.GetBookings();

                Console.WriteLine($"{bookings.GetTaxi()}\n");

                foreach (var passenger in bookings.GetPassengers())
                {
                    Console.WriteLine($"\t {passenger}\n");
                }
            }

            Console.WriteLine("\n\n\n");
        }

        private static void BookTaxi()
        {
            Console.WriteLine("\n");

            var arrivals = new Arrivals();

            var addNewParticipantCheckin = false;
            do
            {
                Console.WriteLine($" Nom du participant : ");
                string nameParticipant = Console.ReadLine();

                var isArrivalHourValid = false;
                do
                {
                    Console.WriteLine($" Heure d'arrivé à la gare du participant - le format hh : ");
                    int arrivalHourValue;

                    if (int.TryParse(Console.ReadLine(), out arrivalHourValue)
                            && arrivalHourValue >= 0 
                            && arrivalHourValue <= 23)
                    {
                        var arrivalHour = new ArrivalHour(arrivalHourValue);
                        var participant = new Participant(nameParticipant);

                        var arrival = new Arrival(arrivalHour, participant);
                        arrivals.Add(arrival);

                        isArrivalHourValid = true;
                    }
                    else
                    {
                        Console.WriteLine(" Heure d'arrivée incorrecte !");
                    }
                }
                while (isArrivalHourValid == false);

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
            }
            while (addNewParticipantCheckin == true);

            taxiOrganizers.Add(new TaxiOrganizer(arrivals));

            Console.WriteLine("\n\n\n");
        }

        private static void ShowCandidatesEmail()
        {
            Console.WriteLine("\n");

            var candidateEmails = candidateRegister.GetCandidateEmails();

            if (candidateEmails.IsEmpty())
            {
                Console.WriteLine("Aucun candidat n'a été ajouté.\n");
            }
            else
            {
                foreach (var email in candidateEmails.ToList())
                {
                    Console.WriteLine($"{email.ToString()} \n");
                }
            }

            Console.WriteLine("\n\n\n");
        }

        private static void AddCandidate()
        {
            Console.WriteLine("\n");

            var addNewCandidate = false;
            do
            {
                var isEmailValid = false;
                Email emailCandidate = null;

                do
                {
                    Console.WriteLine($" Email du candidat : ");
                    string emailValue = Console.ReadLine();

                    try
                    {
                        emailCandidate = Email.Of(emailValue);
                        var candidate = new Candidate(emailCandidate);
                        candidateRegister.Register(candidate);

                        isEmailValid = true;
                    }
                    catch (InvalidEmailException invalidEmailException)
                    {
                        Console.WriteLine($"{invalidEmailException.Message}, veuillez resaisir l'adresse email du candidat");
                    }
                }
                while (isEmailValid == false);

                Console.WriteLine("Voulez-vous ajouter un autre candidat ? [O/N]");
                var responseAddNewCandidate = Console.ReadLine();

                if (responseAddNewCandidate.Equals("O"))
                {
                    addNewCandidate = true;
                }
                else
                {
                    addNewCandidate = false;
                }
            }
            while (addNewCandidate == true);

            Console.WriteLine("\n\n\n");
        }

        private static void ShowCountColdMeals()
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

            var addNewParticipantCheckin = false;
            do
            {
                var isEmailValid = false;
                Email email = null;

                do
                {
                    Console.WriteLine($" Email du participant : ");
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
                    Console.WriteLine($" Date de checkin du participant - le format yyyy/mm/dd hh:mm:ss  ");
                    DateTime checkinDate;

                    if (DateTime.TryParse(Console.ReadLine(), out checkinDate))
                    {
                        var checkin = new Checkin(email, checkinDate);
                        checkinProvider.Add(checkin);

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
            }
            while (addNewParticipantCheckin == true);

            coldMealsCounter = new ColdMealsCounter(checkinProvider);

            Console.WriteLine("\n\n\n");
        }
    }
}