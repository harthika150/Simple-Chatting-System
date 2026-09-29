package com.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
            session.getAttribute("username") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        String username =
                (String) session.getAttribute("username");

        String message = request.getParameter("message");

        String sql =
                "INSERT INTO messages(username, message) VALUES(?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, message);

            ps.executeUpdate();

            response.sendRedirect("chat.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Chat Error: " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String sql =
                "SELECT username, message, created_at " +
                "FROM messages ORDER BY id ASC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                out.println("<p><b>" +
                        rs.getString("username") +
                        ":</b> " +
                        rs.getString("message") +
                        "</p>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("Database Error: " + e.getMessage());
        }
    }
}