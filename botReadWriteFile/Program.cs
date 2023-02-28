using System;
using System.Threading.Tasks;
using System.Collections.Generic;
using System.Text;
using System.IO;
using System.Linq;

namespace PracticRabot5
{
    class Program
    {

        static void StreamWrite(string pach)
        {
            int count = File.ReadAllLines(pach).Length;
            using (StreamWriter handBook = new StreamWriter(pach, true, Encoding.Unicode))
            {
                char key = 'д';
                do
                {
                    DateTime toDayTime = DateTime.Now;

                    Console.WriteLine("\nФ.И.О.: ");
                    string name = Console.ReadLine();
                    Console.WriteLine("\n__________________");

                    Console.WriteLine("\nВозвраст: ");
                    int age = Int32.Parse(Console.ReadLine());
                    Console.WriteLine("\n__________________");

                    Console.WriteLine("\nРост: ");
                    int height = Int32.Parse(Console.ReadLine());
                    Console.WriteLine("\n__________________");

                    Console.WriteLine("\nДата рождения:\n00/00/0000 ");
                    DateTime dateBird = new DateTime(2020, 01, 01);
                    dateBird = DateTime.Parse(Console.ReadLine());
                    Console.WriteLine("\n__________________");

                    Console.WriteLine("\nМесто рождения: ");
                    string placeBird = Console.ReadLine();
                    Console.WriteLine("\n__________________");

                    count++;
                    string text = count + "#" + toDayTime + "#" + name + "#" + age + "#" + height + "#" + dateBird + "#" + placeBird;
                   handBook.WriteLine(text);
                    Console.Write("Продолжить н/д"); key = Console.ReadKey(true).KeyChar;
                } while (char.ToLower(key) == 'д');  
            }
        }
        static void StreamRead(string pach)
        {
            using (StreamReader ReadHandBook = new StreamReader(pach, Encoding.Unicode))
            {
                string line;
                 while ((line = ReadHandBook.ReadLine()) != null)
                 {
                    string[] dataArray = line.Split('#');
                    
                    Console.WriteLine($"{dataArray[0],1} {dataArray[1],15} {dataArray[2],10} {dataArray[3],5} {dataArray[4],5} {dataArray[5],10} {dataArray[6],10}");
                 }
            } 
        }
        static void Exist(string pach)
        {
            if (File.Exists(pach))
            {
                Console.WriteLine("Файл существует");
            }
            else
            {
                Console.WriteLine("Файла нет!Нажмите Enter что бы создать файл");
                Console.ReadKey();
                using (FileStream createFile = File.Create(pach))
                {
                    createFile.Close();
                    
                }
            }
        }
        static void Main(string[] args)
        { 
            string pach = @"handBook.txt";
            Exist(pach);
            int i = 0;
            do
            {
                Console.WriteLine("Выберите пункт меню для дальнейших действий, нажав соответствующую цифру:");
                Console.WriteLine();
                Console.WriteLine("1 - Просмотр записей из блокнота в консоли ");
                Console.WriteLine("2 - Добавление записей в блокнот ");
                Console.WriteLine("3 - Выход");

                int value;
                bool isInt;

                isInt = Int32.TryParse(Console.ReadLine(), out value);
                while (!isInt || value < 1 || value > 3) 
                {
                    Console.WriteLine("Недопустимое значение,введите число  от 1 до 3");
                    isInt = Int32.TryParse(Console.ReadLine(), out value);
                }

                switch (value)
                {
                    case 1:
                        Console.Clear();
                        StreamRead(pach);
                        break;
                    case 2:
                        Console.Clear();
                        StreamWrite(pach);
                        break;
                    case 3:
                        i = 1;
                        break;
                }
                Console.WriteLine("________________________________________________________________________________");
                Console.WriteLine();
            } while (i==0);
            Console.ReadKey();
        }

    }
}