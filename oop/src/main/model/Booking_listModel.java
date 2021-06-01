package main.model;

import javafx.beans.property.SimpleStringProperty;
public class Booking_listModel {


        private final SimpleStringProperty status, firstName, lastName, film, date, time, seat, idNumber;
        private String username;

        public Booking_listModel (String status, String username, String firstName, String lastName, String film, String date, String time, String price, String idNumber) {

            this.status = new SimpleStringProperty(status);
            this.firstName = new SimpleStringProperty(firstName);
            this.lastName = new SimpleStringProperty(lastName);
            this.film = new SimpleStringProperty(film);
            this.date = new SimpleStringProperty(date);
            this.time = new SimpleStringProperty(time);
            this.seat = new SimpleStringProperty(price);
            this.idNumber = new SimpleStringProperty(idNumber);
            this.username = username;
        }

        public String getStatus() {

            return status.get();
        }

        public String getFirstName() {

            return firstName.get();
        }

        public String getLastName() {

            return lastName.get();
        }

        public String getFilm() {

            return film.get();
        }

        public String getDate() {

            return date.get();
        }

        public String getTime() {

            return time.get();
        }

        public String getSeat() {

            return seat.get();
        }

        public String getIdNumber() {

            return idNumber.get();
        }

        public String getUsername() {

            return username;
        }

}
