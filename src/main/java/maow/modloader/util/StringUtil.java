package maow.modloader.util;

import java.util.List;

public class StringUtil {
    public static String collapseStrings(List<String> strings) {
        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
            builder.append(string);
        }
        return builder.toString().replaceAll(" ", "");
    }
}
