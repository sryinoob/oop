package main.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import main.Main;
import main.model.Booking_listModel;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class Booking_listController implements Initializable {

    @FXML
    private TableView<Booking_listModel> table;
    @FXML
    private TableColumn<Booking_listModel, String> idNumber, firstName, lastName, date, seat,employeeId;
    @FXML
    Booking_listModel tableDate;
    @FXML
    private ArrayList<Booking_listModel> listRows = new ArrayList<Booking_listModel>();
    @FXML
    public ObservableList<Booking_listModel> populateTableList;
    @FXML
    private Button backButton, cancelBookingButton;
    @FXML
    private String selectedRowId;


    // method that gets executed at load time
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setTableColumns();
        Main.resetBookingList();



        if (Main.isEmployee())
            populateTableList = FXCollections.observableArrayList(Main.getBookingList());
        else {
            ArrayList<Booking_listModel> currentCustomerBookings = new ArrayList<Booking_listModel>();
            for (Booking_listModel booking : Main.getBookingList()) {
                if (booking.getUsername().equals(Main.getCurrentUser().getUsername()))
                    currentCustomerBookings.add(booking);
            }
            populateTableList = FXCollections.observableArrayList(currentCustomerBookings);
        }

        // populating the table with the elements stored in observable list
        table.getItems().addAll(populateTableList);
        // the rows of the list are automatically sorted by key value

        changeColor();
    }

    private void setTableColumns() {

        // specifying how to populate the columns of the table
        idNumber.setCellValueFactory(new PropertyValueFactory<Booking_listModel, String>("idNumber"));
        firstName.setCellValueFactory(new PropertyValueFactory<Booking_listModel, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Booking_listModel, String>("lastName"));
       date.setCellValueFactory(new PropertyValueFactory<Booking_listModel, String>("date"));
        seat.setCellValueFactory(new PropertyValueFactory<Booking_listModel, String>("seat"));
        employeeId.setCellValueFactory(new PropertyValueFactory<Booking_listModel, String>("employeeId"));
    }


    @FXML
    public boolean validateBookingCancellation() {

        // getting current date for reference
        LocalDate currentDate = LocalDate.now();

        // retrieving date from the table and saving it as String
        String selectedBookingDate = table.getSelectionModel().getSelectedItem().getDate();


        // creating Date object from int array
        int[] dateToInt = Arrays.stream(selectedBookingDate.split("-")).mapToInt(Integer::parseInt).toArray();
        LocalDate bookingDate = LocalDate.of(dateToInt[0], dateToInt[1], dateToInt[2]);

        // comparing current date with booking date
        int dateComparison = bookingDate.compareTo(currentDate);
        // System.out.println(dateComparison);
        return dateComparison >= 0 ? true : false;
    }


    @FXML
    void changeColor() {

table.setRowFactory(row -> {
            return new TableRow<Booking_listModel>() {
                @Override
                public void updateItem(Booking_listModel item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) { // if the row is empty
                        setText(null);
                        setStyle("");
                    } else { // if the row is not empty
                        // we get here all the info of the booking of this row
                        Booking_listModel bookingInfo = getTableView().getItems().get(getIndex());
                        // style all rows whose idNumber is set to "cancelled"
                        if (bookingInfo.getIdNumber().equals("cancelled")) {
                            // set the background colour of the row to gray
                            setStyle("-fx-background-color: #D3D3D3");
                        } else {
                            // if the table is reordered the colors update accordingly
                            setStyle(table.getStyle());
                        }
                    }
                }
            };
        });
    }

    @FXML
    public void backToPrevScene(ActionEvent event) throws IOException {

        Main.resetBookingList();
        SceneCreator.launchScene("/scenes/ManageBookingsScene.fxml");
    }

    @FXML
    void getRowId(MouseEvent e) {

        // storing the id number of the selected row in a String
        if (table.getSelectionModel().getSelectedItem() != null)
            this.selectedRowId = table.getSelectionModel().getSelectedItem().getIdNumber();
    }

    boolean isSelectedRowValid(String selectedRowId) {
        return selectedRowId != null ? true : false;
    }

    @FXML
    void deleteBooking(ActionEvent event) throws IOException {

        Alert alert = new Alert(AlertType.CONFIRMATION, "Do you wish to cancel this booking?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        // if the user clicks OK
        if (alert.getResult() == ButtonType.YES) {
            if (isSelectedRowValid(selectedRowId)) {
                if (table.getSelectionModel().getSelectedItem().getIdNumber()idNumber().equals("booked")) {
                    if (validateBookingCancellation()) { // comparing booking's date with the current date
                        Main.modifyBookingDatabase( selectedRowId, "idNumber", "cancelled");
                        Main.resetBookingList();
                        SceneCreator.launchScene("/ui/booking_list.fxml");
                        alert.close();
                    }
                    else {
                        Alert alert2 = new Alert(AlertType.ERROR, "You cannot cancel an old booking!", ButtonType.CLOSE);
                        alert2.showAndWait();
                        if (alert2.getResult() == ButtonType.CLOSE)
                            alert2.close();
                    }
                }
                else {
                    Alert alert2 = new Alert(AlertType.ERROR, "Booking already cancelled!", ButtonType.CLOSE);
                    alert2.showAndWait();
                    if (alert2.getResult() == ButtonType.CLOSE)
                        alert2.close();
                }
            }
        }
        else if (alert.getResult() == ButtonType.NO) {
            alert.close();
        }
    }
}