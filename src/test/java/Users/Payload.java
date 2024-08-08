package Users;

import Utilities.ExcelDataDriven;
import Utilities.RandomDataGenerator;
import Utilities.RandomDataTypeNames;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import pojos.CreateUsersPojo;

import java.io.IOException;
import java.util.*;

public class Payload {

    /**
     * Generates a JSON payload for creating a user from individual string parameters.
     *
     * @param user_id       The user ID.
     * @param name          The name of the user.
     * @param email         The email address of the user.
     * @param given_name    The given name of the user.
     * @param last_ip       The last IP address used by the user.
     * @param updated_at    The timestamp of the user's last update.
     * @param last_login    The timestamp of the user's last login.
     * @param email_verified Whether the user's email is verified.
     * @param createdAt     The timestamp of user creation.
     * @return A JSON payload as a string.
     */
    public static String getCreateUserPayloadFromString(String user_id, String name, String email, String given_name,
                                                        String last_ip, String updated_at, String last_login, Boolean email_verified, String createdAt) {
        String payload = "{\n" +
                "        \"user_id\": " + user_id + ",\n" +
                "        \"name\": \"" + name + "\",\n" +
                "        \"email\": \"" + email + "\",\n" +
                "        \"given_name\": \"" + given_name + "\",\n" +
                "        \"last_ip\": \"" + last_ip + "\",\n" +
                "        \"updated_at\": \"" + updated_at + "\",\n" +
                "        \"last_login\": \"" + last_login + "\",\n" +
                "        \"email_verified\": \"" + email_verified + "\"\n" +
                "        \"createdAt\": \"" + createdAt + "\"\n" +
                "}";
        return payload;
    }

    public static Map<String, Object> getCreateUserPayloadFromMap(String user_id, String name, String email, String given_name,
                                                                  String last_ip, String updated_at, String last_login, Boolean email_verified, String createdAt) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("user_id", user_id);
        payload.put("name", name);
        payload.put("email", email);
        payload.put("given_name", given_name);
        payload.put("last_ip", last_ip);
        payload.put("updated_at", updated_at);
        payload.put("last_login", last_login);
        payload.put("email_verified", email_verified);
        payload.put("createdAt", createdAt);
        return payload;
    }

    public static Map<String, Object> getCreateUserPayloadFromMap() {

        Map<String, Object> payload = new HashMap<>();
        Faker faker = new Faker();
        payload.put("user_id", "");
        payload.put("name", RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FULLNAME));
        payload.put("email", RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.EMAIL));
        payload.put("given_name", RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FIRSTNAME));
        payload.put("last_ip", RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.IP_ADDRESS));
        payload.put("updated_at", RandomDataGenerator.getRandomFutureDate());
        payload.put("last_login", RandomDataGenerator.getRandomFutureDate());
        payload.put("email_verified", RandomDataGenerator.getRandomBooleanValue());
        payload.put("createdAt", RandomDataGenerator.getRandomFutureDate());
        return payload;
    }

    public static CreateUsersPojo getCreateUserPayloadFromPojo() {

        return CreateUsersPojo
                .builder()
//                .user_id("")
                .name(RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FULLNAME))
                .email(RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.EMAIL))
                .given_name(RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FIRSTNAME))
                .last_ip(RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.IP_ADDRESS))
                .updated_at(RandomDataGenerator.getRandomFutureDate())
                .last_login(RandomDataGenerator.getRandomFutureDate())
                .email_verified(RandomDataGenerator.getRandomBooleanValue())
                .build();
    }

    //@DataProvider
    public Iterator<CreateUsersPojo> getCreateUserPayloadFromExcel() throws IOException {
        List<HashMap<String, String>> excelDataAsListOfMap = ExcelDataDriven.loadDSheetData("src/test/resources/TestData/RestAssured.xlsx", "CreateUserData");
        List<CreateUsersPojo> createUsersRequest = new ArrayList<>();
        for (Map<String, String> data : excelDataAsListOfMap) {
            CreateUsersPojo createUsersPojo = CreateUsersPojo.builder()
                    .name(data.get("name"))
                    .given_name(data.get("given_name"))
                    .email(data.get("email"))
                    .last_login(data.get("last_login"))
                    .last_ip(data.get("last_ip"))
                    .updated_at(data.get("updated_at"))
                    .email_verified(Boolean.valueOf(data.get("email_verified")))
                    .createdAt(data.get("createdAt"))
                    .build();
            createUsersRequest.add(createUsersPojo);
        }
        return createUsersRequest.iterator();
    }

    public static List<CreateUsersPojo> getCreateUserRequestBodyFromExcel() throws IOException {
        List<HashMap<String, String>> excelDataAsListOfMap = ExcelDataDriven.loadDSheetData("src/test/resources/TestData/RestAssured.xlsx", "Sheet2");
        List<CreateUsersPojo> requestBodyList = new ArrayList<>();

        for (Map<String, String> data : excelDataAsListOfMap) {
            String jsonRequest = data.get("Request");

            // Parse the JSON string into a CreateUsersPojo object
            CreateUsersPojo createUsersPojo = parseJsonToCreateUsersPojo(jsonRequest);

            // Add the CreateUsersPojo object to the list
            requestBodyList.add(createUsersPojo);
        }

        return requestBodyList;
    }

    public static Iterator<Map<String, String>> getHeadersFromExcel(String CellName) throws IOException {
        List<HashMap<String, String>> excelDataAsListOfMap = ExcelDataDriven.loadDSheetData("src/test/resources/TestData/RestAssured.xlsx", "Sheet2");
        List<Map<String, String>> headersList = new ArrayList<>();

        for (Map<String, String> data : excelDataAsListOfMap) {
            String headersJson = data.get(CellName);
            // Parse JSON headers into a map
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> headersMap = objectMapper.readValue(headersJson, new TypeReference<Map<String, String>>() {});
            headersList.add(headersMap);
        }
        return headersList.iterator();

    }

    private static CreateUsersPojo parseJsonToCreateUsersPojo(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, CreateUsersPojo.class);
    }
}
