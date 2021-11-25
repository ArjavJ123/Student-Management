import java.util.*;

import javax.security.auth.Subject;


class Student {
    private String Sname;
    private ArrayList<Course> courses = new ArrayList<>();
    private int SId;
    private int daysAttended;

    Student(String Sname, int SId, int daysAttended) {
        this.Sname = Sname;
        this.SId = SuperAdmin.Admin.students.size();
        this.daysAttended = daysAttended;
    }
    public int getStudentID() {
        return this.SId;
    }
    public Student returnStudentDetails() {
        return this;
    }
    public int getDays() {
        return this.daysAttended;
    }
    public String getName(){
        return this.Sname;
    }
    public int getMarks(String SubName) {
        int score = 0 ;
        for(int i=0; i<this.courses.size(); i++ ) {
            if(this.courses.get(i).getSubjectName() == SubName ) {
                score = this.courses.get(i).getMarks(this.SId);
            }
        }
        return score;
    }
    public List<Integer> reportCard() {
        List<Integer> marks = new ArrayList<Integer>();
        for(Course course: courses) {
            marks.add(course.getMarks(this.SId));
        }
        return marks;
    }
    public void addCourse(Course course) {
        courses.add(course);
    }
}


class Teacher {
    private Course subject;
    private int TId;
    private String Tname;

    Teacher( int TId, String Tname){
        this.TId = TId;
        this.Tname = Tname;
    }
    public int getTeacherID() {
        return this.TId;
    }
    public Teacher returnTeacherDetails() {
        return this;
    }
    public void setMarks(
            List<Integer> markList) {
        this.subject.setMarks(markList);
    }
    public void addCourse(Course Subject) {
        this.subject = Subject;
    }
    public Course getSubject() {
        return this.subject;
    }
}




class Course {
    private int id;
    private String name;
    private Teacher teacher;
    private ArrayList<Student> classroom = new ArrayList<>();
    private ArrayList<Integer> marks = new ArrayList<>();

    Course(String name) {
        this.name = name;
        this.id = SuperAdmin.Admin.AllCourses.size();
    }
    public String getSubjectName() {
        return this.name;
    }
    public Teacher getTeacherName() {
        return this.teacher;
    }
    public void addStudent(Student s1) {
        this.classroom.add(s1);
        this.marks.add(0);
    }
    public void addTeacher(Teacher t1){
        this.teacher = t1;
    }
    public void setMarks(List<Integer> marklist) {
        for(int i = 0; i< marklist.size(); i++) {
            marks.set(i, marklist.get(i));
        }
    }
    public int getMarks(int SID) {
        int i;
        int score = 0;
        for(i =0 ; i<classroom.size(); i++) {
            if(classroom.get(i).getStudentID() == SID) {
                score = marks.get(i);
            }
        }
        return score;
    }
    public int getClassSize() {
        return this.classroom.size();
    }
    public void showClassAttendance() {
        Double x ;
        for(int i=0 ; i< classroom.size(); i++) {
            int days = classroom.get(i).getDays();
            x = (days/285.0)*100;
            System.out.println(classroom.get(i).getName() + ":" + x);
        }
    }
}
class solution {
    public static void main(String[] args) {

        int ch = 0;
        Scanner inp = new Scanner(System.in);
        do {
            System.out.println("Who is using the app: \n 1.Student \n 2.Teacher \n 3.Admin \n 4.Exit ");
            ch = Integer.parseInt(inp.nextLine());


            if (ch == 1) {
                int ID;
                int ch2 = 0;
                String Sname = new String();
                System.out.println("Enter Student ID");
                ID = Integer.parseInt(inp.nextLine());

                Student S1 = SuperAdmin.Admin.getStudentByID(ID);

                do {
                    System.out.println("Enter your choice: \n 1. Get Marks \n 2. See Attendance \n 3. Show Report Card \n 4. Exit");
                    ch2 = Integer.parseInt(inp.nextLine());
                    if (ch2 == 1) {
                        Sname = inp.nextLine();
                        S1.getMarks(Sname);
                    } else if (ch2 == 2) {
                        System.out.println(SuperAdmin.Admin.displayAttendance(S1));
                    } else if (ch2 == 3) {
                        System.out.println(S1.getName());
                        System.out.println(S1.reportCard());
                    }
                } while (ch2 != 4);

            } else if (ch == 2) {
                int ch3 = 0;
                int ID;
                List<Integer> MarkList = new ArrayList<Integer>();
                System.out.println("Enter your ID:");
                ID = Integer.parseInt(inp.nextLine());
                Teacher t1 = SuperAdmin.Admin.getTeacherByID(ID);
                do {
                    System.out.println("Enter your choice: \n 1. Set Marks \n 2. Show class attendance \n 3. Exit");
                    ch3 = Integer.parseInt(inp.nextLine());
                    if (ch3 == 1) {
                        for (int i = 0; i < t1.getSubject().getClassSize(); i++) {
                            MarkList.add(Integer.parseInt(inp.nextLine()));
                        }
                        t1.setMarks(MarkList);
                    } else if (ch3 == 2) {
                        t1.getSubject().showClassAttendance();
                    }
                } while (ch3 != 3);

            } else if (ch == 3) {
                int ch1 = 0;
                String name = new String();
                int ID;
                int daysAttended;
                List<String> Courses = new ArrayList<String>();
                String Cname = new String();

                do {
                    System.out.println("Enter choice: \n 1. add new student \n 2. add new teacher \n 3. update student detail \n 4. update teacher detail \n 5. add new course \n 6. remove teacher \n 7. remove student \n 8. display attendance \n 9. show all courses \n 10. Exit");
                    ch1 = Integer.parseInt(inp.nextLine());
                    if (ch1 == 1) {
                        System.out.println("Enter Student Name :");
                        name = inp.nextLine();
                        for (int i = 0; i < 5; i++) {
                            System.out.println("Enter Course Name:");
                            Courses.add(inp.nextLine());
                        }
                        System.out.println("Enter Days attended:");
                        daysAttended = Integer.parseInt(inp.nextLine());
                        SuperAdmin.Admin.addStudent(name, Courses, daysAttended);
                        System.out.println("New Student added");
                    } else if (ch1 == 2) {
                        System.out.println("Enter Teacher Name");
                        name = inp.nextLine();
                        System.out.println("Enter Subject ID");
                        int cname = Integer.parseInt((inp.nextLine()));
                        SuperAdmin.Admin.addTeacher(name, cname);
                        System.out.println("New Teacher added");
                    } else if (ch1 == 3) {
                        int originalID;
                        System.out.println("Enter ID to be modified");
                        originalID = Integer.parseInt(inp.nextLine());
                        System.out.println("Enter new Name");
                        name = inp.nextLine();
                        System.out.println("Enter new number of days Attended");
                        daysAttended = Integer.parseInt(inp.nextLine());
                        SuperAdmin.Admin.updateDetails(SuperAdmin.Admin.getStudentByID(originalID), name, originalID, daysAttended);
                        System.out.println("Student updated");
                    } else if (ch1 == 4) {
                        System.out.println("Enter ID to be changed");
                        int originalID;
                        originalID = Integer.parseInt(inp.nextLine());
                        System.out.println("Enter new Name");
                        name = inp.nextLine();
                        SuperAdmin.Admin.updateDetails(SuperAdmin.Admin.getTeacherByID(originalID), originalID, name);
                        System.out.println("Teacher updated");
                    } else if (ch1 == 5) {
                        System.out.println("Enter Course Name :");
                        Cname = inp.nextLine();
                        SuperAdmin.Admin.addCourse(Cname);
                        System.out.println("New Course Added");
                    } else if (ch1 == 6) {
                        ID = Integer.parseInt(inp.nextLine());
                        SuperAdmin.Admin.delete(SuperAdmin.Admin.getTeacherByID(ID));
                        System.out.println("Teacher removed");
                    } else if (ch1 == 7) {
                        ID = Integer.parseInt(inp.nextLine());
                        SuperAdmin.Admin.delete(SuperAdmin.Admin.getStudentByID(ID));
                        System.out.println("Student Removed");
                    } else if (ch1 == 8) {
                        System.out.println("Enter ID of student whose Attendance is required:");
                        ID = Integer.parseInt(inp.nextLine());
                        System.out.println(SuperAdmin.Admin.displayAttendance(SuperAdmin.Admin.getStudentByID(ID)));
                    } else if (ch1 == 9) {
                        SuperAdmin.Admin.showAllCourses();
                    }
                } while (ch1 != 10);

            }
        }while (ch !=4 );
    }
}
