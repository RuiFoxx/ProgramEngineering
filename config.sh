# ������� ����� ����������
MAIN="com.Main"
# ����� � ������������
LIB="lib/*"
# ����� � �����������
SRC="src/"
# ��������� ����� � ������� ���������� ����������
OUT="out"
# �������� ��� jar �����
OUT_JAR="$OUT/aaa.jar"
# ����� � ������� ��������� ����������������� ������
OUT_CLS="$OUT/classes/"
# ����� � ������� ���������� ����������
OUT_LIB="$OUT/lib/"

# ����������� : ��� ; � ����������� �� ������������ �������
LIB="lib/*"
OUT_JAR="out/aaa.jar"
#echo $(uname -s)
if [ "$(uname)" == "Darwin" ]; then
    # Do something under Mac OS X platform
    CP="$LIB:$OUT_JAR"
elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
    # Do something under GNU/Linux platform
    CP="$LIB:$OUT_JAR"
elif [ "$(expr substr $(uname -s) 1 10)" == "MINGW32_NT" ]; then
    # Do something under Windows NT platform
    CP="$LIB;$OUT_JAR"
elif [ "$(expr substr $(uname -s) 1 7)" == "MSYS_NT" ]; then
    # Do something under MSYS platform
    CP="$LIB;$OUT_JAR"
fi
#echo $CP