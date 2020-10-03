package net.dohaw.play.corelib.helpers;

import net.dohaw.play.corelib.CoreLib;
import net.dohaw.play.corelib.StringUtils;
import org.bukkit.Material;

import static net.dohaw.play.corelib.StringUtils.firstUpperRestLower;

public class EnumHelper {

    public static String enumToConfigKey(Enum e){
        String stringValue = e.toString();
        String finalStringValue = "";

        if(stringValue.contains("_")){
            String[] enumSplit = stringValue.split("_");
            for(String s : enumSplit){
                finalStringValue += StringUtils.firstUpperRestLower(s);
            }
        }else{
            finalStringValue = StringUtils.firstUpperRestLower(stringValue);
        }

        return finalStringValue;
    }

    public static String enumToName(Enum e){
        String stringValue = e.toString();

        if(stringValue.contains("_")){
            stringValue = stringValue.replace("_", " ");
        }

        return stringValue;
    }

    public static Enum nameToEnum(Class clazz, String s){
        s = s.replace(" ", "_");
        s = s.toUpperCase();
        return Enum.valueOf(clazz, s);
    }

    public static String matToName(Material mat){
        String enumName = mat.name();
        if(enumName.contains("_")){
            enumName = enumName.replace("_", " ");
        }
        enumName = StringUtils.firstUpperRestLower(enumName);
        return enumName;
    }


}
