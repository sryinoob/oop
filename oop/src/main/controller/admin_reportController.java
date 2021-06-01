package main.controller;

import javafx.event.ActionEvent;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class admin_reportController {
    private List<String>[] rows;if (admin.isDbConnected()){
        isConnected.setText("Connected");
    }else{
        isConnected.setText("Not Connected");
    }

    public void csvBooking(ActionEvent event) throws IOException {


        FileWriter csvWriter = new FileWriter("Booking.csv");
        try {
            csvWriter.append( booking_id);
            csvWriter.append(",");
            csvWriter.append(first_name);
            csvWriter.append(",");
            csvWriter.append(last_name);
            csvWriter.append(",");
            csvWriter.append(date);
            csvWriter.append(",");
            csvWriter.append(seat);
            csvWriter.append(",");
            csvWriter.append(employee_id);
            csvWriter.append("\n");



        for (List<String> rowData : rows) {
            csvWriter.append(String.join(",", rowData));
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    public void csvBooking(ActionEvent event) throws IOException {


        FileWriter csvWriter = new FileWriter("Employee.csv");
        try {
            csvWriter.append( employee_id);
            csvWriter.append(",");
            csvWriter.append(first_name);
            csvWriter.append(",");
            csvWriter.append(last_name);
            csvWriter.append(",");
            csvWriter.append(role);
            csvWriter.append("\n");



            for (List<String> rowData : rows) {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
