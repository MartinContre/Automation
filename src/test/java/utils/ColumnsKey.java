package utils;

public enum ColumnsKey {
    AUTHOR_ID("author_id"),
    BROWSER("browser"),
    BUILD_NUMBER("build_number"),
    CREATED_TIME("created_time"),
    EMAIL("email"),
    END_TIME("end_time"),
    ENV("env"),
    ID("id"),
    LOGIN("login"),
    METHOD_NAME("method_name"),
    NAME("name"),
    PROJECT_ID("project_id"),
    SESSION_ID("session_id"),
    SESSION_KEY("session_key"),
    START_TIME("start_time"),
    STATUS_ID("status_id");

    private final String key;

    ColumnsKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
