package pojos;

import Utilities.RandomDataGenerator;
import Utilities.RandomDataTypeNames;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(toBuilder = true)
public class CreateUsersPojo {
    //private String user_id = "";
    private String name = RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FULLNAME);
    private String email = RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.EMAIL);
    private String given_name = RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FIRSTNAME);
    private String last_ip = RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.IP_ADDRESS);
    private String updated_at = RandomDataGenerator.getRandomFutureDate();
    private String last_login = RandomDataGenerator.getRandomFutureDate();
    private Boolean email_verified = RandomDataGenerator.getRandomBooleanValue();
    private String createdAt = RandomDataGenerator.getRandomFutureDate();
}
