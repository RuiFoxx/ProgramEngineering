source ./config.sh

rm -rf $OUT
# Компиляция всех *.java файлов
mkdir -p $OUT_CLS
find . -name "*.java" | xargs javac -cp "$CP" -d $OUT_CLS -sourcepath $SRC #-verbose

# Копирование их из $RES в $OUT_CLS
#cp -r &RES $OUT_CLS

# Копирование библиотек
mkdir -p $OUT_LIB
cp $LIB $OUT_LIB

# Архивация всех классов в $OUT_JAR в jar файл, где $MAIN – класс с main()
jar -cfe $OUT_JAR $MAIN -C $OUT_CLS .
javac -sourcepath "./src" -classpath "./lib/commons-cli-1.3.1.jar;" src/com/Main.java -d out/