using System.Collections.Generic;
using System.Linq;

class Program
{
    static void Main(string[] args)
    {
        int counts = 100;

        Random rand = new Random();

        List<int> numberCount = new List<int>();

        for (int i = 0; i < counts; i++)
            numberCount.Add(rand.Next(0,100));


        Console.WriteLine("Лист случаных целых чисел от 0 до 100 ");

        for (int i = 0; i < numberCount.Count; i++)
        {
            Console.Write($"{numberCount[i]} ");
        }

        Console.WriteLine();

        Console.WriteLine("\nСортировка листа целых чисел");

        numberCount.Sort();
        for (int i = 0; i < numberCount.Count; i++)
        {
            Console.Write($"{numberCount[i]} ");
        }

        Console.WriteLine();
        Console.WriteLine("\nУдаление чисел которые больше 25 но меньше 50");

        for (int i = numberCount.Count-1; i > 0; i--)
        {
            int j = 25;
            int k = 50;
            if (numberCount[i] >= j)
            {
                if (numberCount[i] <= k)
                {
                    numberCount.Remove(numberCount[i]);
                } 
            }
        }

        for (int i = 0; i < numberCount.Count; i++)
        {
            Console.Write($"{numberCount[i]} ");
        }

        Console.ReadLine();
    }
}