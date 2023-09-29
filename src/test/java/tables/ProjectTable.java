package tables;

import aquality.selenium.core.logging.Logger;
import models.ProjectModel;
import utils.ColumnsKey;
import utils.InsertFieldGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectTable extends BaseTable<ProjectModel> {
    private static final String INSERT_VALUES = "(?)";
    private static final int UPDATE_VALUES_COUNT = 2;
    private static final ColumnsKey[] USED_COLUMNS = {ColumnsKey.NAME};

    public ProjectTable() {
        tableName = "project";
    }

    @Override
    protected ProjectModel createModel(ResultSet resultSet) {
        try {
            Logger.getInstance().info("Creating model");
            int id = resultSet.getInt(ColumnsKey.ID.getKey());
            String name = resultSet.getString(ColumnsKey.NAME.getKey());
            return new ProjectModel(id, name);
        } catch (SQLException e) {
            Logger.getInstance().error("Couldn't create model " + e.getMessage());
            throw new RuntimeException("Couldn't create model " + e);
        }
    }

    @Override
    protected String getInsertFields() {
        return InsertFieldGenerator.generateInsertFields(USED_COLUMNS);
    }

    @Override
    protected String getInsertValues() {
        return INSERT_VALUES;
    }

    @Override
    protected void setInsertStatementValues(PreparedStatement statement, ProjectModel project) {
        try {
            Logger.getInstance().info("Setting insert statement values for project model");
            statement.setString(1, project.getName());
        } catch (SQLException e) {
            Logger.getInstance().error("Couldn't set statement values: " + e.getMessage());
        }
    }

    @Override
    protected String getUpdateFields() {
        return InsertFieldGenerator.generateUpdateFields(USED_COLUMNS);
    }

    @Override
    protected void setUpdateStatementValues(PreparedStatement statement, ProjectModel project) {
        try {
            Logger.getInstance().info("Setting update statement values for project model");
            statement.setString(1, project.getName());
        } catch (SQLException e) {
            Logger.getInstance().error("Couldn't sut update statements values: " + e.getMessage());
        }
    }

    @Override
    protected int getUpdateStatementValuesCount() {
        return UPDATE_VALUES_COUNT;
    }

    @Override
    protected long getId(ProjectModel project) {
        return project.getId();
    }

    /**
     * Gets the current project ID.
     *
     * @return The current project ID.
     */
    public int getCurrentProjectId() {
        Logger.getInstance().info("Getting current project id");
        ProjectModel project = new ProjectModel();
        project.setName(testData.getValue("/test/projectName").toString());

        if (isModelInDb(ColumnsKey.NAME.getKey(), project.getName())) {
            Logger.getInstance().info("Current author was not found in db, adding: " + project.getName());
            insert(project);
        }

        return getCurrentModelId(ColumnsKey.NAME.getKey(), project.getName());
    }
}
