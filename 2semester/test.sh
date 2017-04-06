#!/bin/bash

RES="";
GOAL=1;
COUNTER=0;
TOTAL=0;

while [[ $COUNTER != $GOAL ]]; do
    TRIES=0;
    while [[ $RES != "00" ]]; do
        RES=`./a.out`
        echo $RES
        TRIES=$(($TRIES + 1))
        if [[ $TRIES -ge 100000 ]]; then
            echo "after $TRIES tries no degenerate case"
        fi
    done
    TOTAL=$(($TOTAL + $TRIES))
    COUNTER=$(($COUNTER + 1))
    echo 'Found degenreate case after '$TRIES' tries.'
    RES=""
done

echo "Got $GOAL failures in $TOTAL runs, avg: $(($TOTAL / $GOAL)) runs"
