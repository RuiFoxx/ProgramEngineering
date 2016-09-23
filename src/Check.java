import java.util.ArrayList;
import java.util.HashMap;


public class Check
{
    public static int CheckRole ( ArrayList <Role> currentRoles, HashMap <String, String> arrArgValues)
    {
        for (int i=0;i<currentRoles.size();i++)
        {
            if (arrArgValues.get("role").equals(currentRoles.get(i)))
            {
               //проверка ресурса
                //если провильно то

                if (currentRoles.get(i).getResource().contains(arrArgValues.get("resource")))
                     return 0;
                else
                    return 4; //если нет то код ошибки не тот ресурс
            }

            return 3;
        }
    }
}
