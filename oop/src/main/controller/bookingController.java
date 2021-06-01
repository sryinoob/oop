package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.model.BookingModel;

import java.net.URL;
import java.util.ResourceBundle;

public class bookingController implements Initializable {

    public BookingModel bookingModel = new BookingModel();

    @FXML
    private Button bookingList, seatLock, backCBooking, confirmCBooking, homeCBooking ;
    @FXML
    private CheckBox selectSeatBooking,availableSeatBooking,unavailableSeatBooking,lockedSeatBooking ,A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16 ;
    @FXML
    private ComboBox seatPicker;
    @FXML
    private Label selectBooking,availableBooking,unavailableBooking,lockedBooking,bookinglbl;
    @FXML
    private DatePicker bookingDate;
    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){

    }
}
