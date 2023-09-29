package models;

import lombok.Data;

@Data
public class Header {
    private String name;
    private String value;

    @Override
    public String toString() {
        return "Header{"
                + "name='" + name
                + "', valuer='" + value
                + "'}";
    }
}
