package org.example.api.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
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
