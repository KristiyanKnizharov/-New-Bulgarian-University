using System;
using System.Linq;
using System.Text;
using System.Collections.Generic;

namespace demoExamproblem
{
    public class Program
    {
        static void Main()
        {
            var n = int.Parse(Console.ReadLine());
            var sortArr = Console.ReadLine()
                        .Split()
                        .Select(int.Parse)
                        .ToArray();

            var centerInArray = n / 2;
            for (int i = 0; i < n - 1; i++)
            {

                var minIndex = i;
                for (int j = i + 1; j < n; j++)
                {
                    if (i < centerInArray)
                    {
                        if (sortArr[j] < sortArr[minIndex])
                            minIndex = j;
                    }
                    else
                    {
                        if (sortArr[j] > sortArr[minIndex])
                            minIndex = j;
                    }
                }
                int buff = sortArr[minIndex];
                sortArr[minIndex] = sortArr[i];
                sortArr[i] = buff;
            }
            Console.WriteLine(string.Join(" ", sortArr));
        }
    }
}
