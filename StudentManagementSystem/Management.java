class Student {
    String name;
    int rollno;
    Student next;

    Student(String name, int rollno) {
        this.name = name;
        this.rollno = rollno;
        this.next = null;
    }
}

public class Management {
    Student head;

    public void addStudent(String name, int rollno) {
        Student student1 = new Student(name, rollno);
        student1.next = head;
        head = student1;
    }

    public void removeStudentByID(int rollno) {
        if (head == null) {
            System.out.println("Student list is empty.");
            return;
        }

        // Case 1: If the head itself needs to be removed
        if (head.rollno == rollno) {
            head = head.next;
            System.out.println("Student with Roll No " + rollno + " removed.");
            return;
        }

        // Case 2: Removing a non-head node
        Student current = head;
        while (current.next != null) {
            if (current.next.rollno == rollno) {
                current.next = current.next.next; // Bypass the node
                System.out.println("Student with Roll No " + rollno + " removed.");
                return;
            }
            current = current.next;
        }

        // If the roll number is not found
        System.out.println("Student with Roll No " + rollno + " not found.");
    }

    public void searchStudentByID(int rollno) {
        Student current = head;
        while (current != null) {
            if (current.rollno == rollno) {
                System.out.println("Student Found: " + current.rollno + ". " + current.name);
                return;
            }
            current = current.next;
        }
        System.out.println("Student with Roll No " + rollno + " not found.");
    }

    public void displayStudent() {
        if (head == null) {
            System.out.println("No students available.");
            return;
        }

        Student current = head;
        System.out.println("Student List:");
        while (current != null) {
            System.out.println(current.rollno + ". " + current.name);
            current = current.next;
        }
    }

    public static void main(String[] args) {
        Management obj = new Management();
        obj.addStudent("Aravind", 1);
        obj.addStudent("Avenger", 2);
        obj.addStudent("Batman", 3);

        System.out.println("Before Removing:");
        obj.displayStudent();

        obj.removeStudentByID(1);
        obj.removeStudentByID(10); // Test removing non-existing student

        System.out.println("\nAfter Removing:");
        obj.displayStudent();

        System.out.println("\nSearching for Student:");
        obj.searchStudentByID(2);
        obj.searchStudentByID(7); // Test searching for non-existing student
    }
}
