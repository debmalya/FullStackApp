package com.rakuten.fullstackrecruitmenttest.service;

import com.rakuten.fullstackrecruitmenttest.configuration.UploadConfiguration;
import com.rakuten.fullstackrecruitmenttest.exception.UploadException;
import com.rakuten.fullstackrecruitmenttest.payload.Employee;
import com.rakuten.fullstackrecruitmenttest.payload.ErrorRecord;
import com.rakuten.fullstackrecruitmenttest.payload.UploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

@Service
public class UploadFileService {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Where to store files.
     * Processed and error files.
     */
    private final Path location;

    /**
     * Name only contain alphabets.
     */
    Pattern namePattern = Pattern.compile("^[A-Za-z\\s]*$");

    /**
     * Name only contain alphabets.
     */
    Pattern alphanumericPattern = Pattern.compile("^[A-Za-z0-9\\s]*$");

    @Autowired
    public UploadFileService(UploadConfiguration uploadConfiguration) {
        this.location = Paths.get(uploadConfiguration.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.location);
        } catch (Exception ex) {
            throw new UploadException("Enable to create the directory for file storage.", ex);
        }
    }


    /**
     * This is process CSV data
     *
     * @param data employee CSV data.
     * @return upload response
     */
    public UploadResponse processCSVData(String[] data) {
        UploadResponse uploadResponse = new UploadResponse();

        long total = 1;
        long errors = 0;

        for (String eachRow : data) {
            boolean isError = false;
            String[] values = eachRow.split(",");

            Employee employee = new Employee();
            if (namePattern.matcher(values[0]).find()) {
                employee.setName(values[0]);
            } else {
                isError = true;
                ErrorRecord errorRecord = new ErrorRecord();
                errorRecord.setErrorMsg(String.format("Name '%s' should contain alphabets only", values[0]));
                errorRecord.setRecord(eachRow);
                errorRecord.setRecordNo(total);
                uploadResponse.getErrorRecordsList().add(errorRecord);
            }

            if (alphanumericPattern.matcher(values[1]).find()) {
                employee.setDepartment(values[1]);
            } else {
                isError = true;
                ErrorRecord errorRecord = new ErrorRecord();
                errorRecord.setErrorMsg(String.format("Department '%s' should contain alphanumeric character(s) only", values[1]));
                errorRecord.setRecord(eachRow);
                errorRecord.setRecordNo(total);
                uploadResponse.getErrorRecordsList().add(errorRecord);
            }

            if ("Developer".equalsIgnoreCase(values[2])) {
                employee.setDesignation(values[2]);
            } else if ("Senior Developer".equalsIgnoreCase(values[2])) {
                employee.setDesignation(values[2]);
            } else if ("Manager".equalsIgnoreCase(values[2])) {
                employee.setDesignation(values[2]);
            } else if ("Team Lead".equalsIgnoreCase(values[2])) {
                employee.setDesignation(values[2]);
            } else if ("VP".equalsIgnoreCase(values[2])) {
                employee.setDesignation(values[2]);
            } else if ("CEO".equalsIgnoreCase(values[2])) {
                employee.setDesignation(values[2]);
            } else {
                isError = true;
                ErrorRecord errorRecord = new ErrorRecord();
                errorRecord.setErrorMsg(String.format("Designation '%s' can be 'Developer, Senior Developer, Manager, Team Lead, VP, CEO' only", values[2]));
                errorRecord.setRecord(eachRow);
                errorRecord.setRecordNo(total);
                uploadResponse.getErrorRecordsList().add(errorRecord);
            }


            double salary = 0.00;
            try {
                salary = Double.parseDouble(values[3]);
                employee.setSalary(salary);
            } catch (NumberFormatException nfe) {
                isError = true;
                ErrorRecord errorRecord = new ErrorRecord();
                errorRecord.setErrorMsg(String.format("Salary '%s' is not a valid number", values[3]));
                errorRecord.setRecord(eachRow);
                errorRecord.setRecordNo(total);
                uploadResponse.getErrorRecordsList().add(errorRecord);
            }

            try {
                simpleDateFormat.setLenient(false);
                long joiningTime = simpleDateFormat.parse(values[4]).getTime();
                employee.setJoiningDate(joiningTime);
            } catch (ParseException e) {

                isError = true;

                ErrorRecord errorRecord = new ErrorRecord();
                errorRecord.setErrorMsg(String.format("Joining date '%s' is not valid. Format 'yyyy-MM-dd'", values[4]));
                errorRecord.setRecord(eachRow);
                errorRecord.setRecordNo(total);
                uploadResponse.getErrorRecordsList().add(errorRecord);
            }

            if (!isError) {
                uploadResponse.getEmployeeList().add(employee);
            } else {
                errors++;
            }
            total++;
        }
        uploadResponse.setTotalRows(total);
        uploadResponse.setRowsWithErrors(errors);

        return uploadResponse;
    }
}
