package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an author model.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorModel {
    private int id;
    private String name;
    private String  login;
    private String email;
}
