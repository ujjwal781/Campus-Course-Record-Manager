# javaProject
VITYarthi Project 2025-26
## Ujjwal Sinha (24MIM10156)

# Campus Course& Records Manager(CCRM)

Programming in Java (CSE) project for VITYarthi. its basically a console based application made with Java SE that manages student records, courses, enrollments and more for college.

## what this does
This system can:
- add students and update there info
- Create courses and assign teachers to them
- handle student enrollment with grade calculations
- Import and export CSV files 
- some file operations and backup stuff
- simple menu interface for users

## running the Program
need **Java 17 or higher** on your computer.

### compiling:
javac -d out $(find src -name "*.java")

### Running:
java -cp out edu.ccrm.cli.Main

## brief Java History
- **1995** - java 1.0 came out from Sun Microsystems
- **1998** - Java 2 introduced different platforms (J2SE, J2EE, J2ME)  
- **2004** - java 5 got generics, annotations, enums and other stuff
- **2011** - Java 7 brought NIO.2, try-with-resources syntax
- **2014** - java 8 added streams and lambda expressions which are pretty useful
- **2017** - Java 9 introduced modules system
- **2019+** - now they release new versions every 6 months

## different Java Editions

| Edition | used For | Main Features |
|---------|----------|---------------|
| Java ME | mobile and IoT stuff | lightweight, not many APIs |
| Java SE | desktop programs | Core APIs, collections, streams etc |
| Java EE | big enterprise apps | Web services, servlets, JSP and more |

## JDK vs JRE vs JVM
- **JDK** - development kit that has compiler and other tools you need
- **JRE** - runtime environment just for running Java programs  
- **JVM** - the virtual machine that actually runs the bytecode

## how to Install (Windows)

### JDK installation
1. get JDK 17+ from Oracle website or OpenJDK
2. Run the installer file
3. setup environment variables:
   - JAVA_HOME = C:\Program Files\Java\jdk-17
   - Add %JAVA_HOME%\bin to your PATH
4. test if its working:
   ```
   java -version
   javac -version
   ```
<img width="1049" height="293" alt="image" src="https://github.com/user-attachments/assets/d30f9baa-1791-4680-84e5-961d423845e9" />


### Eclipse Setup
1. download Eclipse IDE for Java Developers
2. Make a new Java project and call it "CCRM"
3. add source folders and packages as needed
4. Copy all the source files into src folder
5. right click Main.java and run it

<img width="1091" height="544" alt="image" src="https://github.com/user-attachments/assets/fa6a0640-c3f0-46d3-9ea4-0bcfe68d64b8" />
<img width="638" height="679" alt="image" src="https://github.com/user-attachments/assets/e110769b-bd99-4cb2-9259-b8308bca49db" />


## what Concepts I Used

| concept | Where I implemented it |
|---------|------------------------|
| encapsulation | Student.java has private fields with getter/setter methods |
| Inheritance | person class is parent of Student and Instructor classes |
| abstraction | Person is an abstract class that others extend |
| polymorphism | Different toString() methods in different classes |
| streams API | CourseService uses streams for filtering courses |
| lambda Expressions | Used with streams for searching and filtering |
| nested Classes | Course class has Builder as inner class |
| enums | Made Semester and Grade as enums |
| singleton Pattern | AppConfig follows singleton pattern |
| exception Handling | Custom exceptions for enrollment business rules |
| NIO.2 | file operations using newer Path and Files APIs |
| recursion | folderSize() method walks through folders recursively |

## enabling Assertions
by default assertions are turned off. To turn them on:
```bash
java -ea -cp out edu.ccrm.cli.Main
```

## main Features
- student management (add, update, remove students)
- course creation and assigning instructors 
- enrollment system that checks credit limits
- automatic GPA calculation from grades
- CSV file import/export capabilities
- file backup utilities for data safety
- command line interface with menus

## problems I Found
- GPA calculation might have some bugs in edge cases
- file handling could be better, sometimes crashes
- error messages arent very clear for users
- need to add more validation for inputs

## what I Want to Add Later
- maybe a GUI instead of just console
- database connection instead of just files
- better error handling throughout
- more test cases to check everything works
- user authentication system

## notes
had some trouble with the file I/O operations initially but managed to get it working. The streams API was confusing at first but makes sense now.Overall this project helped me understand OOP concepts much better.

---

Ujjwal Sinha
24MIM10156
*be hungry be foolish*
