import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * AttendanceManager handles all student data using HashMap<Integer, Student>.
 * Implements CRUD operations, reports, and analytics with DSA.
 */
public class AttendanceManager {
    private final HashMap<Integer, Student> students = new HashMap<>();
    private int totalClassesConducted = 0;

    /**
     * Add new student, prevent duplicate ID.
     * @return true if added successfully
     */
    public boolean addStudent(int id, String name) {
        if (students.containsKey(id)) {
            return false; // Duplicate ID
        }
        students.put(id, new Student(id, name));
        return true;
    }

    /**
     * Update student name by ID.
     * @return true if updated
     */
    public boolean updateStudentName(int id, String newName) {
        Student student = students.get(id);
        if (student != null) {
            student.setName(newName);
            return true;
        }
        return false;
    }

    /**
     * Remove student by ID.
     * @return true if removed
     */
    public boolean removeStudent(int id) {
        return students.remove(id) != null;
    }

    /**
     * Mark attendance for student.
     * @return true if marked
     */
    public boolean markAttendance(int id, boolean present) {
        Student student = students.get(id);
        if (student != null) {
            student.markAttendance(present);
            totalClassesConducted = Math.max(totalClassesConducted, student.getTotalClasses());
            return true;
        }
        return false;
    }

    /**
     * Search student by ID.
     */
    public Student searchStudent(int id) {
        return students.get(id);
    }

    /**
     * View all students.
     */
    public String viewAllStudents() {
        if (students.isEmpty()) return "No students registered.";
        return students.values().stream()
                .map(Student::toString)
                .collect(Collectors.joining("\n\n"));
    }

    /**
     * Show defaulters (<75% attendance).
     */
    public String showDefaulters() {
        StringBuilder sb = new StringBuilder("Defaulters (<75%):\n\n");
        boolean hasDefaulters = false;
        for (Student s : students.values()) {
            if (s.getAttendancePercentage() < 75.0) {
                sb.append(s).append("\n\n");
                hasDefaulters = true;
            }
        }
        return hasDefaulters ? sb.toString() : "No defaulters found.";
    }

    /**
     * Show topper (highest attendance).
     */
    public String showTopper() {
        if (students.isEmpty()) return "No students.";
        Student topper = students.values().stream()
                .max(Comparator.comparing(Student::getAttendancePercentage))
                .orElse(null);
        return topper != null ? "Topper:\n\n" + topper.toString() : "No data.";
    }

    /**
     * Get total students count.
     */
    public int getTotalStudents() {
        return students.size();
    }

    /**
     * Get total classes conducted.
     */
    public int getTotalClassesConducted() {
        return totalClassesConducted;
    }

    /**
     * Clear output helper - but manager keeps data.
     */
    public String clearOutput() {
        return "";
    }

    /**
     * Get student report by ID.
     */
    public String getStudentReport(int id) {
        Student student = students.get(id);
        return student != null ? student.toString() : "Student not found.";
    }
}
