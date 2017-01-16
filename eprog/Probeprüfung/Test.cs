using System;

class Program {
    public static void Main(string[] args) {
        Test t = new Test();
        Console.WriteLine(t.foo(3.0f, 4.0f));
    }
}

class Test {
    public void foo(int a, int b) { }
    public int foo(float a, float b) { return 0; }
}
