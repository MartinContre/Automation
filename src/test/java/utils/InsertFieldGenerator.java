package utils;

public class InsertFieldGenerator {

    /**
     * Generates the insert fields string for SQL statements.
     *
     * @param columns The array of columns.
     * @return The generated insert fields string.
     */
    public static String generateInsertFields(ColumnsKey[] columns) {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < columns.length; i++) {
            sb.append("`").append(columns[i]).append("`");
            if (i < columns.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Generates the update fields string for SQL statements.
     *
     * @param columns The array of columns.
     * @return The generated update fields string.
     */
    public static String generateUpdateFields(ColumnsKey[] columns) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            sb.append("`").append(columns[i]).append("` = ?");
            if (i < columns.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
