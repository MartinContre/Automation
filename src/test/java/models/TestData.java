package models;

import java.util.List;

public class TestData {
    private List<Employee> employees;
    private String btnAlert;
    private String btnClick;
    private String appearedTxt;
    private String enterName;
    private int lengthRandomText;
    private String parentTxt;
    private String childTxt;
    private String newTabTxt;
    private int rowsTable;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getBtnAlert() {
        return btnAlert;
    }

    public void setBtnAlert(String btnAlert) {
        this.btnAlert = btnAlert;
    }

    public String getBtnClick() {
        return btnClick;
    }

    public void setBtnClick(String btnClick) {
        this.btnClick = btnClick;
    }

    public String getAppearedTxt() {
        return appearedTxt;
    }

    public void setAppearedTxt(String appearedTxt) {
        this.appearedTxt = appearedTxt;
    }

    public String getEnterName() {
        return enterName;
    }

    public void setEnterName(String enterName) {
        this.enterName = enterName;
    }

    public int getLengthRandomText() {
        return lengthRandomText;
    }

    public void setLengthRandomText(int lengthText) {
        this.lengthRandomText = lengthText;
    }

    public String getParentTxt() {
        return parentTxt;
    }

    public void setParentTxt(String parentTxt) {
        this.parentTxt = parentTxt;
    }

    public String getChildTxt() {
        return childTxt;
    }

    public void setChildTxt(String childTxt) {
        this.childTxt = childTxt;
    }

    public String getNewTabTxt() {
        return newTabTxt;
    }

    public void setNewTabTxt(String newTabTxt) {
        this.newTabTxt = newTabTxt;
    }

    public int getRowsTable() {
        return rowsTable;
    }

    public void setRowsTable(int rowsTable) {
        this.rowsTable = rowsTable;
    }
}
