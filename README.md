# Student Attendance Management System

## Professional Java Swing Application (Resume-Ready)

### Features
- **OOP**: Encapsulation, modular classes (Model-Service-GUI)
- **DSA**: `HashMap<Integer, Student>`, `ArrayList<Boolean>`
- **Persistence**: CSV file storage/load (data/attendance.txt)
- **GUI**: Swing with validation, table output, defaulter highlighting
- **All Operations**: Add/Update/Remove, Mark Attendance, Reports, Topper/Defaulters

### Run
```
cd "c:/Users/sayali/Downloads/StudentAttendanceSystem"
javac -d . model/*.java service/*.java gui/*.java
java -cp . gui.AttendanceGUI
```
Data auto-saves on exit, loads on start.

### Table Output Example
```
 ID  | Name        | Class | Section | Total | %Attendance |
-----|-------------|-------|---------|-------|-------------|
 101 | Rahul       | 10    | A       |   20  |     85.0%   |
```

**Clean, production-like code with comments and standards.**

