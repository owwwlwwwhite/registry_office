package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationResp {
    private int applicantId;
    private int dateOfApplication;
    private int kindOfApplication;
    private int statusOfApplication;
}
