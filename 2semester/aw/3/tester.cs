#define GoogleCodeJam
#define DEBUG

using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Globalization;
using System.IO;
using System.Linq;

namespace CodingChallenge
{
	// Test
	public class Program
	{
        public static void Main(string[] args) {
            int n = 3;
            int[][] path = s.Split(Environment.NewLine.ToCharArray(), StringSplitOptions.RemoveEmptyEntries)
                          .Select(x => x.Split().Select(y => int.Parse(y)).ToArray())
                          .ToArray();
            bool[][] seen = new bool[n+1][];
            seen[0] = new[] { true };
            seen[n] = new[] { false };
            for (int i = 1; i < n; i++) {
                seen[i] = new bool[7];
            }

            int[] prev = new[] { 0, 0 };
            for (int i = 0; i < path.Length; i++) {
                int[] node = path[i];
                //Console.WriteLine($"Looking at: ({node[0]}, {node[1] + 1})");
                if (seen[node[0]][node[1]]) {
                    // do nothing
                } else {
//                    if (node[0] != 0 && node[0] != n && prev[0] != n && prev[0] != 0) {
//                        if (prev[0] != node[0]) {
//                            if (prev[1] % 2 != node[1] % 2) {
//                                throw new Exception("Invalid path");
//                            }
//                        }
//                        //if (prev[1] - node[1]) {
//                        //}
//                    }
                    Console.WriteLine($"{node[0] == prev[0] ? "---------------------------" : ""}marked: ({prev[0]}, {prev[1] + 1}) to ({node[0]}, {node[1] + 1}) as golden");
                    seen[node[0]][node[1]] = true;
                }
                prev = node;
            }
        }
    static string s = @"
1 1
2 1
1 3
2 3
1 5
2 1
3 0
2 2
1 2
2 4
1 4
2 2
1 6
2 4
3 0
2 3
1 1
2 5
1 3
0 0
1 2
2 6
1 4
0 0
1 5
2 5
3 0
2 6
2 5
2 4
2 3
2 2
2 1
2 6
1 6
1 5
1 4
1 3
1 2
1 1
1 6
0 0
1 6
1 1
1 2
1 3
1 4
1 5
1 6
2 6
2 1
2 2
2 3
2 4
2 5
2 6
3 0
2 5
1 5
0 0
1 4
2 6
1 2
0 0
1 3
2 5
1 1
2 3
3 0
2 4
1 6
2 2
1 4
2 4
1 2
2 2
3 0
2 1
1 5
2 3
1 3
2 1
1 1
0 0
";
    }

}
