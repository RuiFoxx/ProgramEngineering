java -cp "../../../lib/commons-cli-1.3.1.jar;." com.Main ; echo "***" $?
java -cp "../../../lib/commons-cli-1.3.1.jar;." com.Main  -h ; echo "***" $?
java -cp "../../../lib/commons-cli-1.3.1.jar;." com.Main  -login 'XXX' -pass 'XXX' ; echo "***" $?
java -cp "../../../lib/commons-cli-1.3.1.jar;." com.Main  -login 'jdoe' -pass 'XXX' ; echo "***" $?
java -cp "../../../lib/commons-cli-1.3.1.jar;." com.Main  -login 'jdoe' -pass 'sup3rpaZZ' ; echo "***" $?
java -cp "../../../lib/commons-cli-1.3.1.jar;." com.Main  -login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'a' ; echo "***" $?
java -cp "../../../lib/commons-cli-1.3.1.jar;." com.Main  -login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'a.b' ; echo "***" $?
java -cp "../../../lib/commons-cli-1.3.1.jar;." com.Main  -login 'jdoe' -pass 'sup3rpaZZ' -role 'XXX' -res 'a.b' ; echo "***" $?
java -cp "../../../lib/commons-cli-1.3.1.jar;." com.Main  -login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'XXX' ; echo "***" $?
java -cp "../../../lib/commons-cli-1.3.1.jar;." com.Main  -login 'jdoe' -pass 'sup3rpaZZ' -role 'WRITE' -res 'a' ; echo "***" $?
java -cp "../../../lib/commons-cli-1.3.1.jar;." com.Main  -login 'jdoe' -pass 'sup3rpaZZ' -role 'WRITE' -res 'a.bc' ; echo "***" $?
java -cp "../../../lib/commons-cli-1.3.1.jar;." com.Main  -login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'a.b' -ds '2015-01-01' -de '2015-12-31' -vol '100' ; echo "***" $?
java -cp "../../../lib/commons-cli-1.3.1.jar;." com.Main  -login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'a.b' -ds '01-01-2015' -de '2015-12-31' -vol '100' ; echo "***" $?
java -cp "../../../lib/commons-cli-1.3.1.jar;." com.Main  -login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'a.b' -ds '2015-01-01' -de '2015-12-31' -vol 'XXX' ; echo "***" $?