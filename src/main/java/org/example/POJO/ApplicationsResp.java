package org.example.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationsResp {
    private int applicantId;
    private int applicationId;
    private int citizenId;
    private String dateOfApplication;
    private String kindOfApplication;
    private int statusOfApplication;
    private int staffId;
}
