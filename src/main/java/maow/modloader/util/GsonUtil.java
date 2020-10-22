package maow.modloader.util;

import com.google.gson.Gson;
import maow.modloader.mod.ModInfo;

public class GsonUtil {
    private static final Gson gson = new Gson();

    public static ModInfo readModInfo(String json) {
        return gson.fromJson(json, ModInfo.class);
    }
}
