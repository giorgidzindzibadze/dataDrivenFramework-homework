package Steps;

import Connections.DbConnection;
import Data.Dates;
import io.qameta.allure.Step;


import java.sql.*;

public class CommonSteps {
    Connection connection;
    Dates dates = new Dates();
    public CommonSteps() throws SQLException {
        connection = DbConnection.getConnection();
        connection.setAutoCommit(false);
    }

    @Step
    public void insertAndValidation() {
        try {
            connection.setAutoCommit(false);
            String query = "INSERT INTO students (id, firstName, lastName, phone)" +
                    " VALUES (?, ?, ?,?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, dates.id);
            pstmt.setString(2, dates.firstName);
            pstmt.setString(3, dates.lastName);
            pstmt.setString(4, dates.phone);
            int rowsAffected = pstmt.executeUpdate();


            if (rowsAffected == 1
            ) {
                connection.rollback();// ეს გზა მოვიფიქრე ვალიდაციისთვის.
                String selectQuery = "SELECT * FROM students WHERE id = ?";
                PreparedStatement pstm = connection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
                pstm = connection.prepareStatement(selectQuery);
                pstm.setInt(1, dates.id);
                ResultSet resultSet = pstm.executeQuery();
                if (!resultSet.next()) {
                    System.out.println("new row is not inserted");
                }

            } else {
                System.out.println("new row inserted");
            }

            int row = pstmt.executeUpdate();//თავიდან ვუშვებ ინსერტის ბრძანებას და ვაკომიტებ.
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Step
    public void validation() {
        try {

            String selectQuery = "SELECT * FROM students WHERE firstName = ? AND lastName = ? AND phone = ?";
            PreparedStatement pstmt = connection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);

            pstmt = connection.prepareStatement(selectQuery);
            pstmt.setString(1, dates.firstName);
            pstmt.setString(2, dates.lastName);
            pstmt.setString(3, dates.phone);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                String retrievedFirstName = resultSet.getString("firstName");
                String retrievedLastName = resultSet.getString("lastName");
                String retrievedPhone = resultSet.getString("phone");

                assert retrievedFirstName.equals(dates.firstName);
                assert retrievedLastName.equals(dates.lastName);
                assert retrievedPhone.equals(dates.phone);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Step
    public void update() {
        try {
            connection.setAutoCommit(false);
            String updateQuery = "UPDATE students SET firstName = ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, dates.newFirstName);
            updateStatement.setInt(2, dates.id);
            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected == 1) {
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ResultSet getCustomerFirstName() {
        try {
            String selectQuery = "SELECT firstName FROM students WHERE lastName = ? AND id = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, dates.lastName);
            selectStatement.setInt(2, dates.id);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                String retrievedFirstName = resultSet.getString("firstName");
                assert retrievedFirstName.equals(dates.newFirstName);



            } else {
                System.out.println("No record found for the specified criteria");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    @Step
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
