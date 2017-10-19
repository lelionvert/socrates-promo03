using System;

namespace Socrates.Meals
{
    public class ColdMealsNumber
    {
        private int value;

        public ColdMealsNumber(int numberColdMeals)
        {
            this.value = numberColdMeals;
        }

        public override bool Equals(object obj)
        {
            var number = obj as ColdMealsNumber;
            return number != null &&
                   value == number.value;
        }

        public override int GetHashCode()
        {
            return -1584136870 + value.GetHashCode();
        }

        public int ObtainTheValue()
        {
            return value;
        }



        public override string ToString()
        {
            return value.ToString();
        }

        public static ColdMealsNumber ValueOf(int number)
        {
            return new ColdMealsNumber(number);
        }
    }
}