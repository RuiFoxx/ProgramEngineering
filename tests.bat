java -cp "./lib/commons-cli-1.3.1.jar;./out/production/ProgramEngineering" com.Main ; echo Exit code: %errorlevel%
java -cp "./lib/commons-cli-1.3.1.jar;./out/production/ProgramEngineering" com.Main  -h ; echo Exit code: %errorlevel%
java -cp "./lib/commons-cli-1.3.1.jar;./out/production/ProgramEngineering" com.Main  -login XXX -pass XXX ; echo Exit code: %errorlevel%
java -cp "./lib/commons-cli-1.3.1.jar;./out/production/ProgramEngineering" com.Main  -login jdoe -pass XXX ; echo Exit code: %errorlevel%
java -cp "./lib/commons-cli-1.3.1.jar;./out/production/ProgramEngineering" com.Main  -login jdoe -pass sup3rpaZZ ; echo Exit code: %errorlevel%
java -cp "./lib/commons-cli-1.3.1.jar;./out/production/ProgramEngineering" com.Main  -login jdoe -pass sup3rpaZZ -role READ -res a ; echo Exit code: %errorlevel%
java -cp "./lib/commons-cli-1.3.1.jar;./out/production/ProgramEngineering" com.Main  -login jdoe -pass sup3rpaZZ -role READ -res a.b ; echo Exit code: %errorlevel%
java -cp "./lib/commons-cli-1.3.1.jar;./out/production/ProgramEngineering" com.Main  -login jdoe -pass sup3rpaZZ -role XXX -res a.b ; echo Exit code: %errorlevel%
java -cp "./lib/commons-cli-1.3.1.jar;./out/production/ProgramEngineering" com.Main  -login jdoe -pass sup3rpaZZ -role READ -res XXX ; echo Exit code: %errorlevel%
java -cp "./lib/commons-cli-1.3.1.jar;./out/production/ProgramEngineering" com.Main  -login jdoe -pass sup3rpaZZ -role WRITE -res a ; echo Exit code: %errorlevel%
java -cp "./lib/commons-cli-1.3.1.jar;./out/production/ProgramEngineering" com.Main  -login jdoe -pass sup3rpaZZ -role WRITE -res a.bc ; echo Exit code: %errorlevel%
java -cp "./lib/commons-cli-1.3.1.jar;./out/production/ProgramEngineering" com.Main  -login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 2015-01-01 -de 2015-12-31 -vol 100 ; echo Exit code: %errorlevel%
java -cp "./lib/commons-cli-1.3.1.jar;./out/production/ProgramEngineering" com.Main  -login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 01-01-2015 -de 2015-12-31 -vol 100 ; echo Exit code: %errorlevel%
java -cp "./lib/commons-cli-1.3.1.jar;./out/production/ProgramEngineering" com.Main  -login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 2015-01-01 -de 2015-12-31 -vol XXX ; echo Exit code: %errorlevel%