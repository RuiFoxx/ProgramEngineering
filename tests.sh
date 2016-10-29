result=0 # number of failed tests

test () {
    arr=($1)
    ./run.sh ${arr[*]}
    status=$?
    if [[ $status -ne $2 ]]; then
        echo TESTING FAIL [$1] $status "!=" $2
        ((result+=1))
    else
        echo TESTING OK [$1] $status "==" $2
    fi
    return $status
}

./build.sh

test "" 0
test "-h" 0
test "-l XXX -p XXX" 1
test "-l jdoe -p XXX" 2
test "-l jdoe -p sup3rpaZZ" 0
test "-l jdoe -p sup3rpaZZ -rol READ -res a" 0
test "-l jdoe -p sup3rpaZZ -rol READ -res a.b" 0
test "-l jdoe -p sup3rpaZZ -rol XXX -res a.b" 3
test "-l jdoe -p sup3rpaZZ -rol READ -res XXX" 4
test "-l jdoe -p sup3rpaZZ -rol WRITE -res a" 4
test "-l jdoe -p sup3rpaZZ -rol WRITE -res a.bc" 4
test "-l jdoe -p sup3rpaZZ -rol READ -res a.b -ds 2015-01-01 -de 2015-12-31 -vol 100" 0
test "-l jdoe -p sup3rpaZZ -rol READ -res a.b -ds 01-01-2015 -de 2015-12-31 -vol 100" 5
test "-l jdoe -p sup3rpaZZ -rol READ -res a.b -ds 2015-01-01 -de 2015-12-31 -vol XXX" 5
test "-l X -p X -rol READ -res X -ds 2015-01-01 -de 2015-12-31 -vol XXX" 1
test "-l X -p X -rol READ -res X" 1

echo
if [[ $result -gt 0 ]]; then
    echo $result tests failed
else
    echo ALL TESTS PASSED
fi
exit ${result}