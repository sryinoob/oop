package main.controller;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class registerController implements Initializable{
    @FXML
    private TextField employeeId;
    @FXML
    private Label employeeIdError;

    @FXML
    private TextField firstName;
    @FXML
    private Label firstNameError;

    @FXML
    private TextField lastName;
    @FXML
    private Label lastNameError;

    @FXML
    private TextField username;
    @FXML
    private Label usernameError;


    @FXML
    private TextField password;
    @FXML
    private Label passwordError;

    @FXML
    private TextField secretQuestion;
    @FXML
    private Label secretError;

    @FXML
    private TextField answer;
    @FXML
    private Label answerError;

    @FXML
    private Label lblError;     // General Error Label

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {
        String employeeId;
        String firstName;
        String lastName;
        String role;
        String username;
        String password;
        String secretQuestion;
        String answer;

        // Check if any of the text field is empty
        ArrayList<TextField> txtList = new ArrayList<>();
        txtList.add(txtEmployeeId);
        txtList.add(txtFirstName);
        txtList.add(txtLastName);
        txtList.add(txtUsername);
        txtList.add(txtPass);
        txtList.add(txtSecretQuestion);
        txtList.add(txtAnswer);
        // iterate the textField nodes
        for (TextField nodes : txtList) {
            if (nodes.getText().isEmpty()) {
                lblError.setText("Please complete the all form");
            }

        }

            // store the user's inputs
            employeeId = txtEmployeeId.getText();
            firstName = txtFirstName.getText();
            lastName = txtLastName.getText();
            role = "employee";
            username = txtUsername.getText();
            password = txtPass.getText();
            secretQuestion = txtSecretQuestion.getText();
            answer = txtAnswer.getText();

            // create an object of the Record class
            Record record = new Record(employeeId, firstName, lastName, username, password,secretQuestion,answer);

            // send the information to the users_table
            record.employee_table();

            record.employee_table();


            lblError.setText("Registaration is succesful");
        }





    }

