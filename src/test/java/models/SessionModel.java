package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionModel {
    private int id;
    private String sessionKey;
    private Timestamp createdTime;
    private int buildNumber;
}
