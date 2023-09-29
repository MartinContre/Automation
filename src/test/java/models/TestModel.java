package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestModel {
    private int id;
    private String name;
    private int statusId;
    private String methodName;
    private int projectId;
    private int sessionId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String env;
    private String browser;
    private int authorId;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        TestModel other = (TestModel) obj;
        return Objects.equals(this.getId(), other.getId())
                && Objects.equals(this.getName(), other.getName())
                && Objects.equals(this.getStatusId(), other.getStatusId())
                && Objects.equals(this.getMethodName(), other.getMethodName())
                && Objects.equals(this.getProjectId(), other.getProjectId())
                && Objects.equals(this.getSessionId(), other.getSessionId())
                && Objects.equals(this.getStartTime(), other.getStartTime())
                && Objects.equals(this.getEndTime(), other.getEndTime())
                && Objects.equals(this.getEnv(), other.getEnv())
                && Objects.equals(this.getBrowser(), other.getBrowser())
                && Objects.equals(this.getAuthorId(), other.getAuthorId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, statusId, methodName, projectId, sessionId, startTime, endTime, env, browser, authorId);
    }

    @Override
    public String toString() {
        return "TestModel{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", statusId=" + statusId
                + ", methodName='" + methodName + '\''
                + ", projectId=" + projectId
                + ", sessionId=" + sessionId
                + ", startTime=" + startTime
                + ", endTime=" + endTime
                + ", env='" + env + '\''
                + ", browser='" + browser + '\''
                + ", authorId=" + authorId
                + '}';
    }
}
