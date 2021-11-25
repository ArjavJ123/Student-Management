import java.util.ArrayList;
import java.util.List;

public class SuperAdmin {
    static class Admin {
        static final List<Student> students = new ArrayList<Student>();
        static final List<Teacher> faculty = new ArrayList<Teacher>();
        static final List<Course> AllCourses = new ArrayList<Course>();

        public static void addStudent(String Sname, List<String> courseNames, int daysAttended) {
            int SId = Admin.students.size();
            Student s1 = new Student(Sname, SId, daysAttended);
            students.add(s1);

            for (String cname : courseNames) {
                for (Course course : AllCourses) {
                    if(course.getSubjectName().equals(cname)) {
                        course.addStudent(s1);
                        s1.addCourse(course);
                    }
                }
            }

        }
        public static void addTeacher(String Tname,int cid) {
            int TId = Admin.faculty.size();
            Teacher t1 = new Teacher( TId, Tname);
            t1.addCourse(Admin.AllCourses.get(cid));
            Admin.faculty.add(t1);
        }
        public static void addCourse(String name) {
            Course c1  = new Course(name);
            AllCourses.add(c1);
        }
        public static void updateDetails(Teacher t1, int TId, String Tname) {
            Teacher t2 = new Teacher( TId, Tname);
            faculty.set(faculty.indexOf(t1), t2);
        }
        public static void updateDetails(Student s1, String Sname, int SId, int daysAttended) {
            Student s2 = new Student(Sname, SId, daysAttended);

            students.set(students.indexOf(s1), s2);
        }
        public static void delete(Student s1) {
            students.remove(s1);
        }
        public static void delete(Teacher t1) {
            faculty.remove(t1);
        }
        public static double displayAttendance(Student s1) {
            double attendance ;
            int days = s1.getDays();
            attendance = (days/285.0)*100;
            return attendance;
        }
        public static Student getStudentByID(int SId) {
            return Admin.students.get(SId);
        }
        public static Teacher getTeacherByID(int TId) {
            return Admin.faculty.get(TId);
        }

        public static void showAllCourses() {
            for (Course course : AllCourses) {
                System.out.println(course.getSubjectName());
            }
        }
    }
}
