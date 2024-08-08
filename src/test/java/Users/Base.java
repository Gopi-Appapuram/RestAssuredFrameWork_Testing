package Users;

import Utilities.JsonUtils;

import java.io.IOException;
import java.util.Map;

public class Base {
    public static Map<String, Object> dataFromJsonFile;

    static {
        String ENV = System.getProperty("ENV") == null ? "QA" : System.getProperty("ENV");
        try {
            dataFromJsonFile = JsonUtils.getJsonDataAsMap("Environments/" + ENV + "/ApiData.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
