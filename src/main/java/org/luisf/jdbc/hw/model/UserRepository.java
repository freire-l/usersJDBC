package org.luisf.jdbc.hw.model;

import org.luisf.jdbc.hw.reposity.Repository;
import org.luisf.jdbc.hw.utils.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {

    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getInstance();
    }

    private User createUser(ResultSet results) throws SQLException {
        User user = new User(
                results.getLong("id"),
                results.getString("username"),
                results.getString("password"),
                results.getString("email")
        );
        return user;
    }


    @Override
    public List<User> list() {
        List<User> users = new ArrayList<>();

        try ( Statement stmt = getConnection().createStatement();
                ResultSet results = stmt.executeQuery("SELECT * FROM usuarios")) {
            while (results.next()){
                User user = createUser(results);
                users.add(user);
            }
            results.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User searchId(Long id) {
        User user = null;

        try (PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM usuarios WHERE id = ?")) {
            stmt.setLong(1, id);
            ResultSet results = stmt.executeQuery();
            if(results.next()){
                user = createUser(results);
            }
            results.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    @Override
    public void store(User user) {
        String sql_stmt;
        boolean isUpdate = user.getId() != null && user.getId()>0;

        if(isUpdate){
            sql_stmt = "UPDATE usuarios SET username =?, password=?, email=? WHERE id=?";
            System.out.println("updating");
        } else {
            sql_stmt = "INSERT INTO usuarios  (username, password, email) VALUES (?,?,?)";
        }

        try(PreparedStatement stmt = getConnection().prepareStatement(sql_stmt)){
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPwd());
            stmt.setString(3,user.getEmail());

            if(isUpdate){
                stmt.setLong(4, user.getId());
                System.out.println("updating");
            }
            stmt.executeUpdate();

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }

    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM usuarios WHERE id=?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
