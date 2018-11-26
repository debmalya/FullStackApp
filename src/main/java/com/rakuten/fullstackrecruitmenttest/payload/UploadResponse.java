package com.rakuten.fullstackrecruitmenttest.payload;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UploadResponse {

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    private List<Employee> employeeList = new ArrayList<>();

    private long totalRows;
    private long rowsWithErrors;
    private List<ErrorRecord> errorRecordsList = new ArrayList<>();

    public List<ErrorRecord> getErrorRecordsList() {
        return errorRecordsList;
    }

    public void setErrorRecordsList(List<ErrorRecord> errorRecordsList) {
        this.errorRecordsList = errorRecordsList;
    }

    public long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(long totalRows) {
        this.totalRows = totalRows;
    }

    public long getRowsWithErrors() {
        return rowsWithErrors;
    }

    public void setRowsWithErrors(long rowsWithErrors) {
        this.rowsWithErrors = rowsWithErrors;
    }
}
