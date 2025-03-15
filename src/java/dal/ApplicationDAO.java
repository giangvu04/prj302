/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author -Asus-
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Calendar;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;
import model.Application;
import model.Employee;

public class ApplicationDAO extends DBContext {

    public List<Application> getAll() {
        List<Application> list = new ArrayList<>();
        String sql = "SELECT * FROM Application";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Application app = new Application(
                        rs.getInt("application_id"),
                        rs.getString("title"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("reason"),
                        rs.getString("status"),
                        rs.getInt("created_by"),
                        rs.getInt("processed_by"),
                        rs.getString("note"),
                        rs.getInt("leave_on_person")
                );
                list.add(app);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Application getById(int id) {
        String sql = "SELECT * FROM Application WHERE application_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Application app = new Application(
                        rs.getInt("application_id"),
                        rs.getString("title"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("reason"),
                        rs.getString("status"),
                        rs.getInt("created_by"),
                        rs.getInt("processed_by"),
                        rs.getString("note"),
                        rs.getInt("leave_on_person")
                );
                
                
                return app;
            }
        } catch (SQLException e) {
            System.out.println("getById error: " + e.getMessage());
        }
        return null;
    }

    public Application insert(Application app) {
        String sql = "INSERT INTO Application (title, start_date, end_date, reason, status, created_by,leave_on_person) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, app.getTitle());
            st.setDate(2, app.getStartDate());
            st.setDate(3, app.getEndDate());
            st.setString(4, app.getReason());
            st.setString(5, app.getStatus());
            st.setInt(6, app.getCreatedBy());
            st.setInt(7, app.getLeaveOnPerson());
            st.executeUpdate();

            // Get the generated application_id
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                int newId = rs.getInt(1);
                return getById(newId);
            }
        } catch (SQLException e) {
            System.out.println("insert: " + e.getMessage());
        }
        return null;
    }

    public void update(Application app) {
        String sql = "UPDATE Application SET title = ?, start_date = ?, end_date = ?, reason = ?, status = ?, "
                + "created_by = ?, processed_by = ?, note = ? , leave_on_person  = ?  WHERE application_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, app.getTitle());
            st.setDate(2, app.getStartDate());
            st.setDate(3, app.getEndDate());
            st.setString(4, app.getReason());
            st.setString(5, app.getStatus());
            st.setInt(6, app.getCreatedBy());
            st.setInt(7, app.getProcessedBy());
            st.setString(8, app.getNote());
            st.setInt(9, app.getLeaveOnPerson());
            st.setInt(10, app.getApplicationId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Application WHERE application_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Application> findApplicationsForEmployee(Employee employee) {
        List<Application> list = new ArrayList<>();
        String sql = " select * from Application where created_by =" + employee.getEmployeeId();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Application app = new Application(
                        rs.getInt("application_id"),
                        rs.getString("title"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("reason"),
                        rs.getString("status"),
                        rs.getInt("created_by"),
                        rs.getInt("processed_by"),
                        rs.getString("note"),
                        rs.getInt("leave_on_person")
                );
                list.add(app);
            }
        } catch (SQLException e) {
            System.out.println("findApplicationsByRoleAndDepartment: " + e.getMessage());
        }
        return list;
    }

    public List<Application> findApplicationsForTeamLeader(Employee employee) {
        List<Application> list = new ArrayList<>();
        String sql = " select * from Application where created_by = ? OR created_by IN (SELECT employee_id FROM Employee where manager_id= ?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, employee.getEmployeeId());
            st.setInt(2, employee.getEmployeeId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Application app = new Application(
                        rs.getInt("application_id"),
                        rs.getString("title"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("reason"),
                        rs.getString("status"),
                        rs.getInt("created_by"),
                        rs.getInt("processed_by"),
                        rs.getString("note"),
                        rs.getInt("leave_on_person")
                );
                list.add(app);
            }
        } catch (SQLException e) {
            System.out.println("findApplicationsByRoleAndDepartment: " + e.getMessage());
        }
        return list;
    }

    public List<Application> findApplicationsForDivisionLeader(Employee employee) {
        List<Application> list = new ArrayList<>();
        String sql = " select * from Application where created_by = ? OR created_by IN (\n"
                + "                                                SELECT e.employee_id \n"
                + "                                                FROM Employee e\n"
                + "                                                FULL JOIN Employee_Role er \n"
                + "                                                ON e.employee_id = er.employee_id\n"
                + "                                                WHERE e.department_id = ?\n"
                + "                                                GROUP BY e.employee_id, e.name\n"
                + "                                                HAVING MIN(er.role_id) > ?  \n"
                + "												)";
        // tìm những đơn của những người có role từ trưởng phòng xuống và cùng
        // department
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, employee.getEmployeeId());
            st.setInt(2, employee.getDepartmentId());
            st.setInt(3, employee.getRoleId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Application app = new Application(
                        rs.getInt("application_id"),
                        rs.getString("title"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("reason"),
                        rs.getString("status"),
                        rs.getInt("created_by"),
                        rs.getInt("processed_by"),
                        rs.getString("note"),
                        rs.getInt("leave_on_person")
                );
                list.add(app);
            }
        } catch (SQLException e) {
            System.out.println("findApplicationsByRoleAndDepartment: " + e.getMessage());
        }
        return list;
    }

    public void approveApplication(int applicationId, int processedBy, String note) {
        String sql = "UPDATE Application SET status = 'Approved', processed_by = ?, note = ? WHERE application_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, processedBy);
            st.setString(2, note);
            st.setInt(3, applicationId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("approveApplication: " + e.getMessage());
        }
    }

    public void rejectApplication(int applicationId, int processedBy, String note) {
        String sql = "UPDATE Application SET status = 'Rejected', processed_by = ?, note = ? WHERE application_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, processedBy);
            st.setString(2, note);
            st.setInt(3, applicationId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("rejectApplication: " + e.getMessage());
        }
    }

    public HashMap<Integer, List<Date>> absentDates(Employee employee) {
        HashMap<Integer, List<Date>> absentMap = new HashMap<>();
        String sql = "SELECT leave_on_person, start_date, end_date FROM Application WHERE status = 'Approved'";
        if (employee.getRoleId() != 1) {
            sql += "AND created_by IN (\n"
                    + "                                                SELECT e.employee_id \n"
                    + "                                                FROM Employee e\n"
                    + "                                                FULL JOIN Employee_Role er \n"
                    + "                                                ON e.employee_id = er.employee_id\n"
                    + "                                                WHERE e.department_id = ?\n"
                    + "                                                GROUP BY e.employee_id, e.name\n"
                    + "                                                HAVING MIN(er.role_id) > ?  \n"
                    + "												)";
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (employee.getRoleId() != 1) {
                // Với role khác 1, cần set 2 parameter
                st.setInt(1, employee.getDepartmentId());
                st.setInt(2, employee.getRoleId());
            }
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int employeeId = rs.getInt("leave_on_person");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");

                // Get or create list for this employee
                List<Date> dates = absentMap.getOrDefault(employeeId, new ArrayList<>());

                // Add all dates between start and end date
                Calendar cal = Calendar.getInstance();
                cal.setTime(startDate);
                while (!cal.getTime().after(endDate)) {
                    java.util.Date utilDate = cal.getTime();
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    System.out.println(sqlDate);
                    dates.add(sqlDate);
                    cal.add(Calendar.DATE, 1);
                }
                absentMap.put(employeeId, dates);
            }
        } catch (SQLException e) {
            System.out.println("absentDates: " + e.getMessage());
        }

        return absentMap;
    }

    public static void main(String[] args) {
        ApplicationDAO adao = new ApplicationDAO();
        EmployeeDAO edao = new EmployeeDAO();

        List<Application> applications = adao.getAll();
        for (Application application : applications) {
            System.out.println(application);
        }

    }
}
