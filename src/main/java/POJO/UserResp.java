package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResp {
    private int applicantId;
    private int applicationId;
    private int citizenId;
    private int merrigeCertificateId;
}
