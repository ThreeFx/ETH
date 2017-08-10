#!/bin/bash

echo 'compiling and generating test'
javac test.java
java Test

echo 'compiling and running program'
javac quickselect.java
cat test.in | java Main > output.out

#if [[ `diff -q test.out output.out` -ne '' ]]; then
#    echo 'test failed!'
#else
#    echo 'test succeeded'
#fi

echo 'comparing output'
echo 'if no difference is reported, files match'
diff test.out output.out > /dev/stdout
