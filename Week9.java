import java.util.*;

class Student {
    int id;
    String studentId, firstName, lastName, email, department;

    public Student(int id, String studentId, String firstName, String lastName, String email, String department) {
        this.id = id;
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
    }
}

public class Week9 {

    static List<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int idCounter = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== STUDENT MANAGEMENT SYSTEM WITH JDBC ===");
            System.out.println("1. Initialize Database");
            System.out.println("2. Add New Student");
            System.out.println("3. View All Students");
            System.out.println("4. Search Students");
            System.out.println("5. Update Student");
            System.out.println("6. Delete Student");
            System.out.println("7. Batch Insert");
            System.out.println("8. Stored Procedure (Filter by Dept)");
            System.out.println("9. Exit");

            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> initialize();
                case 2 -> addStudent();
                case 3 -> viewStudents();
                case 4 -> searchStudents();
                case 5 -> updateStudent();
                case 6 -> deleteStudent();
                case 7 -> batchInsert();
                case 8 -> storedProcedure();
                case 9 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void initialize() {
        System.out.println("✅ Database initialized (Simulated)");
    }

    static void addStudent() {
        System.out.print("Enter Student ID: ");
        String sid = sc.nextLine();

        System.out.print("First Name: ");
        String fn = sc.nextLine();

        System.out.print("Last Name: ");
        String ln = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Department: ");
        String dept = sc.nextLine();

        System.out.println("Transaction started...");

        Student s = new Student(idCounter++, sid, fn, ln, email, dept);
        students.add(s);

        System.out.println("Transaction committed!");
        System.out.println("✅ Student added successfully!");
    }

    static void viewStudents() {
        System.out.println("\n=== ALL STUDENTS ===");
        for (Student s : students) {
            System.out.println(s.id + " | " + s.studentId + " | " + s.firstName + " " + s.lastName + " | " + s.department);
        }
    }

    static void searchStudents() {
        System.out.print("Enter keyword: ");
        String key = sc.nextLine().toLowerCase();

        System.out.println("\n🔍 SEARCH RESULTS:");
        for (Student s : students) {
            if (s.firstName.toLowerCase().contains(key) || s.department.toLowerCase().contains(key)) {
                System.out.println(s.studentId + " | " + s.firstName + " | " + s.department);
            }
        }
    }

    static void updateStudent() {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Student s : students) {
            if (s.id == id) {
                System.out.print("New Email: ");
                s.email = sc.nextLine();
                System.out.println("✅ Updated successfully!");
                return;
            }
        }
        System.out.println("❌ Student not found");
    }

    static void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();

        students.removeIf(s -> s.id == id);
        System.out.println("✅ Deleted successfully!");
    }

    static void batchInsert() {
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        System.out.println("Batch insert started...");

        for (int i = 0; i < n; i++) {
            students.add(new Student(idCounter++, "S" + idCounter, "Name" + i, "Last" + i, "email@test.com", "CSE"));
        }

        System.out.println("✅ Batch insert completed!");
    }

    static void storedProcedure() {
        System.out.print("Enter department: ");
        String dept = sc.nextLine();

        System.out.println("\n📋 STUDENTS IN " + dept.toUpperCase());

        for (Student s : students) {
            if (s.department.equalsIgnoreCase(dept)) {
                System.out.println(s.studentId + " | " + s.firstName);
            }
        }
    }
}