package com.library.Test;

import com.library.User.User;
import com.library.UserDAO.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {

    private UserDAO userDAO;

    @BeforeEach
    public void setUp() {
        userDAO = new UserDAO();
    }

    @Test
    public void testAddUser() {
        User user = new User("John Doe", "john@example.com", "password123");
        boolean result = userDAO.addUser(user);
        assertTrue(result, "User should be added successfully.");
    }

    @Test
    public void testGetUserById() {
        User user = userDAO.getUserById(1); // Assuming 1 is a valid user ID
        assertNotNull(user, "User should be found by ID.");
        assertEquals("John Doe", user.getName(), "User's name should be 'John Doe'.");
    }

    @Test
    public void testUpdateUser() {
        User user = new User("Jane Doe", "jane@example.com", "newpassword123");
        user.setId(1); // Assuming the ID of the user to update is 1
        boolean result = userDAO.updateUser(user);
        assertTrue(result, "User should be updated successfully.");
    }

    @Test
    public void testDeleteUser() {
        boolean result = userDAO.deleteUser(1); // Assuming 1 is a valid user ID
        assertTrue(result, "User should be deleted successfully.");
    }

    @Test
    public void testGetAllUsers() {
        Object allUsers = userDAO.getAllUsers();
        assertNotNull(allUsers, "All users should not be null.");
    }

    @Test
    public void testSaveUser() {
        User newUser = new User("New User", "newuser@example.com", "password123");
        userDAO.saveUser(newUser);
        // You can add further validation like checking if the user is saved successfully in the DB.
        assertNotNull(userDAO.getUserById(newUser.getId()), "New user should be saved and retrievable by ID.");
    }
}
