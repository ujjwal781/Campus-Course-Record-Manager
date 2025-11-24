## 1. 🎯 Problem Statement: Why We Need Better Record Management

Right now, most schools handle academic records with a messy mix of systems—and a lot of paperwork. That means stuff gets lost, numbers don’t always match up, and simple things like enrolling in a class can take forever. Auditing the data? Even trickier. To fix this, we really need one clean, automated system that keeps grades, courses, and student info all in one place, easy to manage and always up to date.

---

## 2. 🔍 Project Scope: CCRM Foundation

We’re building CCRM as a basic but solid foundation—a console-based app (CLI only) using Java SE 17+. Its job is to handle the whole academic record process, from start to finish.

<img width="802" height="474" alt="image" src="https://github.com/user-attachments/assets/f656902f-6354-44ce-bc30-e85f1f6dd054" />


*What’s included:*
* *Object Modeling:* Build strong models for Student, Instructor, and Course using OOP principles like inheritance and encapsulation.
* *CRUD Operations:* Add, view, update, or delete student and course records. All the basics, covered.
* *Rules Engine:* Handles enrollment checks and crunches the numbers for GPA automatically.
* *Data Storage:* Uses file handling—think CSV files or Java serialization—to save and load data.
* *User Interaction:* Everything runs through a simple, menu-driven command line interface.

*What’s not included (for now):*
* No graphical interface. It’s all CLI.
* No database connections to MySQL or anything similar.
* Doesn’t support multiple users at the same time or over a network.
* No advanced security—no password hashing, no user roles.

---

## 3. 🧑‍💻 Who’s This For? Academic Staff

CCRM is built for people working behind the scenes at colleges—admin staff and instructors who need fast, reliable access to student and course data.

* *Student Admin Staff:* The folks who keep student records up to date—addresses, status, all that.
* *Course Registrars/Coordinators:* The people setting up courses, managing schedules, and assigning instructors.
* *Faculty/Instructors:* Teachers who need to submit grades and make sure their class lists are right.

---

## 4. 🚀 Key Features: What CCRM Does

Here’s how we’re breaking things down:

*A. Record Management*
* Add, change, and track student info and academic status.
* Create, update, or retire courses—you can even change credits.
* Easily assign instructors to the courses they’re teaching.

*B. Enrollment & Academic Rules*
* Handles new enrollments, checks all the prerequisites and credit limits automatically.
* Lets instructors record grades and calculates each student’s GPA for them.

*C. Data Import/Export*
* Read in CSV files to load data fast or export current records for reports.

*D. System Utilities*
* Basic backup and restore tools to keep your data safe.
* Simple, organized CLI menus to help users get things done without fuss.
