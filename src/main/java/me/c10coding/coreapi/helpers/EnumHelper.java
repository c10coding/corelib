package me.c10coding.coreapi.helpers;

import me.c10coding.coreapi.CoreAPI;
import org.bukkit.Material;

public class EnumHelper {

    public String enumToConfigKey(Enum e){
        String stringValue = e.toString();
        String finalStringValue = "";

        if(stringValue.contains("_")){
            String[] enumSplit = stringValue.split("_");
            for(String s : enumSplit){
                finalStringValue += CoreAPI.getInstance().getChatFactory().firstUpperRestLower(s);
            }
        }else{
            finalStringValue = CoreAPI.getInstance().getChatFactory().firstUpperRestLower(stringValue);
        }

        return finalStringValue;
    }

    public String enumToName(Enum e){
        String stringValue = e.toString();

        if(stringValue.contains("_")){
            stringValue = stringValue.replace("_", " ");
        }

        return stringValue;
    }

    public String matToName(Material mat){
        String enumName = mat.name();
        if(enumName.contains("_")){
            enumName = enumName.replace("_", " ");
        }
        enumName = CoreAPI.getInstance().getChatFactory().firstUpperRestLower(enumName);
        return enumName;
    }


}
