## ETH Zurich

My personal work on my lectures at ETH Zurich.

## Notable projects

### Out of order execution

Compile `00_pprog.c` with `gcc -pthread` and run the result. In some cases
(~1/3000) on my machine, the result will be 00, which is not possible due to
thread interleaving but only because of out of order store/load instructions.

Optionally, run ./test.sh on linux to run until such a case is found (takes
a lot of cpu).

### Expression parser

Located in `eprog/ExpressionParser`

An expression parser and evaluator written in Java. Uses an unambiguous grammar
with some limitations (see below) to parse expressions by creating an expression
tree which can be inspected at runtime.

Supports arbitrary variable and function definitions.

Built-in functions: `sin`, `cos`, `log`, `abs`, `+`, `-`, `*`, `/`, `^`

Grammar used to parse stuff:

    expr -> term [op term]
    term -> `(` expr `)` | fun `(` expr `)` | atom
    atom -> num | var
    op   -> `+` | `-` | `*` | `/` | `^`
    num  -> int `.` int
    int  -> [0-9]+
    var  -> [A-Za-z][A-Za-z0-9]*

Negative numbers are entered as `-x = (0 - x)`.

Builds on double arithmetic, so may suffer from double precision errors.

The example evaluator reads an expression and resolves all undefined subexpressions
by querying the user. May be expanded to REPL later.
