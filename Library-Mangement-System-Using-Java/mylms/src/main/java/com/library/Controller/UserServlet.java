package com.library.Controller;

import com.library.User.User;
import com.library.UserDAO.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO dao;

    public UserServlet() {
        super();
    }

    public void init() {
        dao = new UserDAO();
    }

    // DoGet method to handle requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/new":
                showNewForm(request, response);
                break;

            case "/insert":
                insertUser(request, response);
                break;

            case "/edit":
                showEditForm(request, response);
                break;

            case "/update":
                updateUser(request, response);
                break;

            case "/delete":
                deleteUser(request, response);
                break;

            case "/list":
                listUsers(request, response);
                break;

            case "/view":
                viewUser(request, response);
                break;

            default:
                listUsers(request, response);
                break;
        }
    }

    // DoPost method to handle form submissions
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    // Show the form for adding a new user
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    // Insert a new user to the database
    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userID");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        User newUser = new User(email, phone, password);
        dao.saveUser(newUser);
        response.sendRedirect("welcome.jsp");
    }

    // Show the form to edit an existing user
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userID");
        User existingUser = dao.getUser(userID);

        request.setAttribute("user", existingUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
        dispatcher.forward(request, response);
    }

    // Update user information in the database
    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userID");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        User updatedUser;
        updatedUser = new User(userID, email, phone);
        dao.updateUser(updatedUser);
        response.sendRedirect("welcome.jsp");
    }

    // Delete a user from the database
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        dao.deleteUser(userID);
        response.sendRedirect("user-list.jsp");
    }

    // List all users from the database
    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("userList", dao.getAllUsers());
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    // View a single user's details
    private void viewUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userID");
        User user = dao.getUser(userID);

        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view.jsp");
        dispatcher.forward(request, response);
    }
}
