import java.util.ArrayList;

/**
 * Student class representing a student with ID, name, and attendance records.
 * Uses encapsulation with private fields and public getters/setters.
 */
public class Student {
    private final int id;
    private String name;
    private ArrayList<Boolean> attendance; // true = present, false = absent
    private int totalClasses;

    /**
     * Constructor to initialize student with ID and name.
     * @param id unique student ID
     * @param name student name
     */
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.attendance = new ArrayList<>();
        this.totalClasses = 0;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public ArrayList<Boolean> getAttendance() { return new ArrayList<>(attendance); }
    public int getTotalClasses() { return totalClasses; }

    /**
     * Set student name (for updates).
     */
    public void setName(String name) { this.name = name; }

    /**
     * Mark attendance: present or absent.
     * @param present true for present, false for absent
     */
    public void markAttendance(boolean present) {
        attendance.add(present);
        totalClasses++;
    }

    /**
     * Calculate attendance percentage.
     * @return percentage as double (0-100)
     */
    public double getAttendancePercentage() {
        if (attendance.isEmpty()) return 0.0;
        long presentCount = attendance.stream().filter(Boolean::booleanValue).count();
        return (presentCount * 100.0) / totalClasses;
    }

    /**
     * Generate student report string.
     */
    @Override
    public String toString() {
        return String.format("ID: %d%nName: %s%nAttendance: %.1f%%%n", id, name, getAttendancePercentage());
    }
}
