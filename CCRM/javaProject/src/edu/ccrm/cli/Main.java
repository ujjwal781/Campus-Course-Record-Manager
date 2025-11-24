package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Semester;
import edu.ccrm.domain.Student;
import edu.ccrm.exceptions.DuplicateEnrollmentException;
import edu.ccrm.exceptions.MaxCreditLimitExceededException;
import edu.ccrm.io.FileUtil;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.StudentService;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
   private static final Scanner sc;
   private static final StudentService studentService;
   private static final CourseService courseService;
   private static final EnrollmentService enrollmentService;

   public Main() {
   }

   public static void main(String[] args) throws Exception {
      System.out.println("CCRM starting...");
      System.out.println("App data folder: " + String.valueOf(AppConfig.getInstance().getDataFolder()));
      seedSampleData();
      loopMenu();
   }

   private static void seedSampleData() {
      studentService.addStudent("S1", "24BCE10327", "Ujjawal", "ujjawal@vit.ac.in");
      studentService.addStudent("S2", "24bce11367", "ichhit", "ichhit@vit.ac.in");
      studentService.addStudent("S3", "24BCE10643", "Nrishant", "Nrishant@vit.ac.in");
      studentService.addStudent("S4", "24BCE10554", "Vaani", "Vaani@vit.ac.in");
      studentService.addStudent("S5", "24BCE10798", "omkar", "omkar@vit.ac.in");
      Instructor ins = new Instructor("I1", "Dr. Rao", "rao@uni.edu", "CS");
      Course c1 = (new Course.Builder("CS101")).title("Intro to CS").credits(4).semester(Semester.FALL).department("CS").instructor(ins).build();
      Course c2 = (new Course.Builder("MA101")).title("Calculus").credits(3).semester(Semester.FALL).department("Math").build();
      courseService.add(c1);
      courseService.add(c2);
   }

   private static void loopMenu() {
      boolean run = true;

      while(run) {
         System.out.println("\n--- CCRM MENU ---");
         System.out.println("1. Manage Students\n2. Manage Courses\n3. Enroll/Grade\n4. Export CSV\n5. Backup data folder\n6. Reports (GPA)\n0. Exit");
         System.out.print("Choose: ");
         switch (sc.nextLine().trim()) {
            case "1":
               manageStudents();
               break;
            case "2":
               manageCourses();
               break;
            case "3":
               enrollMenu();
               break;
            case "4":
               doExport();
               break;
            case "5":
               doBackup();
               break;
            case "6":
               doReports();
               break;
            case "0":
               run = false;
               break;
            default:
               System.out.println("Invalid");
         }
      }

      System.out.println("Goodbye!");
   }

   private static void manageStudents() {
      System.out.println("-- Students --");
      System.out.println("1. List\n2. Add\n3. Deactivate");
      String id;
      switch (sc.nextLine().trim()) {
         case "1":
            List<Student> students = studentService.listAll();
            PrintStream ps = System.out;
            Objects.requireNonNull(ps);
            students.forEach(ps::println);
            break;
         case "2":
            System.out.print("id: ");
            id = sc.nextLine().trim();
            System.out.print("regNo: ");
            String reg = sc.nextLine().trim();
            System.out.print("name: ");
            String name = sc.nextLine().trim();
            System.out.print("email: ");
            String email = sc.nextLine().trim();
            studentService.addStudent(id, reg, name, email);
            System.out.println("Added.");
            break;
         case "3":
            System.out.print("id: ");
            id = sc.nextLine().trim();
            studentService.deactivate(id);
            System.out.println("Deactivated.");
            break;
         default:
            System.out.println("back");
      }

   }

   private static void manageCourses() {
      System.out.println("-- Courses --");
      System.out.println("1. List\n2. Add");
      switch (sc.nextLine().trim()) {
         case "1":
            List<Course> var10000 = courseService.listAll();
            PrintStream var10001 = System.out;
            Objects.requireNonNull(var10001);
            var10000.forEach(var10001::println);
            break;
         case "2":
            System.out.print("code: ");
            String code = sc.nextLine().trim();
            System.out.print("title: ");
            String title = sc.nextLine().trim();
            System.out.print("credits: ");
            int cr = Integer.parseInt(sc.nextLine().trim());
            System.out.print("dept: ");
            String dept = sc.nextLine().trim();
            Course c = (new Course.Builder(code)).title(title).credits(cr).department(dept).build();
            courseService.add(c);
            System.out.println("Added.");
            break;
         default:
            System.out.println("back");
      }

   }

   private static void enrollMenu() {
      System.out.println("-- Enroll/Grade --");
      System.out.println("1. Enroll\n2. Unenroll\n3. Record Marks\n4. Show Transcript");
      String ch = sc.nextLine().trim();

      try {
         String sid;
         String cc;
         switch (ch) {
            case "1":
               System.out.print("studentId: ");
               sid = sc.nextLine().trim();
               System.out.print("courseCode: ");
               cc = sc.nextLine().trim();
               enrollmentService.enroll(sid, cc);
               System.out.println("Enrolled.");
               break;
            case "2":
               System.out.print("studentId: ");
               sid = sc.nextLine().trim();
               System.out.print("courseCode: ");
               cc = sc.nextLine().trim();
               enrollmentService.unenroll(sid, cc);
               System.out.println("Unenrolled.");
               break;
            case "3":
               System.out.print("studentId: ");
               sid = sc.nextLine().trim();
               System.out.print("courseCode: ");
               cc = sc.nextLine().trim();
               System.out.print("marks (0-100): ");
               int m = Integer.parseInt(sc.nextLine().trim());
               enrollmentService.recordMarks(sid, cc, m);
               System.out.println("Marks recorded.");
               break;
            case "4":
               System.out.print("studentId: ");
               sid = sc.nextLine().trim();
               studentService.findById(sid).ifPresent((s) -> {
                  System.out.println(s);
                  Collection<?> var10000 = s.getEnrollments().values();
                  PrintStream var10001 = System.out;
                  Objects.requireNonNull(var10001);
                  var10000.forEach(var10001::println);
                  System.out.printf("GPA: %.2f\n", enrollmentService.computeGPA(sid));
               });
               break;
            default:
               System.out.println("back");
         }
      } catch (MaxCreditLimitExceededException | DuplicateEnrollmentException var6) {
         System.out.println("ERROR: " + var6.getMessage());
      }

   }

   private static void doExport() {
      try {
         Path base = AppConfig.getInstance().getDataFolder();
         List<String> headerS = Arrays.asList("id,regNo,fullName,email,active");
         List<List<String>> rowsS = new ArrayList<>();
         Iterator<Student> var3 = studentService.listAll().iterator();

         while(var3.hasNext()) {
            Student s = var3.next();
            rowsS.add(Arrays.asList(s.getId(), s.getRegNo(), s.getFullName(), s.getEmail(), String.valueOf(s.isActive())));
         }

         FileUtil.exportSimpleCsv(base.resolve("students.csv"), headerS, rowsS);
         System.out.println("Exported students to " + String.valueOf(base.resolve("students.csv")));
         List<String> headerC = Arrays.asList("code,title,credits,department,semester,instructor");
         List<List<String>> rowsC = new ArrayList<>();
         Iterator<Course> var5 = courseService.listAll().iterator();

         while(var5.hasNext()) {
            Course c = (Course)var5.next();
            rowsC.add(Arrays.asList(c.getCode(), c.getTitle(), String.valueOf(c.getCredits()), c.getDepartment(), c.getSemester().name(), c.getInstructor() == null ? "" : c.getInstructor().getFullName()));
         }

         FileUtil.exportSimpleCsv(base.resolve("courses.csv"), headerC, rowsC);
         System.out.println("Exported courses to " + String.valueOf(base.resolve("courses.csv")));
      } catch (Exception var7) {
         var7.printStackTrace();
      }

   }

   private static void doBackup() {
      try {
         Path base = AppConfig.getInstance().getDataFolder();
         Path bk = FileUtil.backupFolder(base);
         Files.createDirectories(base);
         String[] var2 = new String[]{"students.csv", "courses.csv"};
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            String p = var2[var4];
            Path src = base.resolve(p);
            if (Files.exists(src, new LinkOption[0])) {
               Files.copy(src, bk.resolve(p));
            }
         }

         System.out.println("Backup created at: " + String.valueOf(bk));
         System.out.println("Size (bytes): " + FileUtil.folderSize(bk));
      } catch (IOException var7) {
         var7.printStackTrace();
      }

   }

   private static void doReports() {
      System.out.println("-- GPA Report --");
      Iterator<Student> var0 = studentService.listAll().iterator();

      while(var0.hasNext()) {
         Student s = var0.next();
         double gpa = enrollmentService.computeGPA(s.getId());
         System.out.printf("%s : GPA=%.2f\n", s.getFullName(), gpa);
      }

   }

   static {
      sc = new Scanner(System.in);
      studentService = new StudentService();
      courseService = new CourseService();
      enrollmentService = new EnrollmentService(studentService, courseService);
   }
}

