package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangeApplStatusResp {
    private int applicantid;
    private int applicationid;
    private int citizenid;
    private String dateofapplication;
    private String kindofapplication;
    private int statusofapplication;
    private int staffid;
}
