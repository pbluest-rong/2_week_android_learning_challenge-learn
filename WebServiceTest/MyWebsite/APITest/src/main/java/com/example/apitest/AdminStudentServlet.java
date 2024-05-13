package com.example.apitest;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api-admin-student")
public class AdminStudentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        // Giả sử bạn có một phương thức để lấy danh sách sinh viên
        List<Student> listStudent = getStudentList();
        mapper.writeValue(resp.getOutputStream(), listStudent);
    }
    // Phương thức mẫu để lấy danh sách sinh viên
    private List<Student> getStudentList() {
        // Thực hiện của bạn để lấy danh sách sinh viên ở đây
        // Cho mục đích minh họa, hãy tạo một danh sách giả mạo
        List<Student> dummyList = new ArrayList<>();
        dummyList.add(new Student("John", "Doe"));
        dummyList.add(new Student("Jane", "Doe"));
        return dummyList;
    }

    // Lớp mẫu Sinh viên
    private class Student {
        private String firstName;
        private String lastName;

        // Constructor, getters, and setters
        public Student(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }
}
