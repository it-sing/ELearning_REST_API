
# ELearning REST API

This project is an **ELearning REST API**, built as a mini-project for our Full Stack Web Development course. Our team developed this API using **Spring Boot**, **PostgreSQL**, and **Gradle** to manage and support e-learning functionality. The API includes features for managing courses, instructors, students, user profiles, and enrollments.

## Project Setup

### Prerequisites
- **Java** (JDK 17 or above recommended)
- **PostgreSQL**
- **Gradle**

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd ELearning-REST-API

	2.	Set up PostgreSQL database
	•	Create a new database named elearning_db.
	•	Update the database connection properties in src/main/resources/application.properties.
	3.	Build and run the project

./gradlew bootRun



### Features

Instructor Management

	•	Instructor Creation and Profile Management: Administrators can add new instructors to the platform and manage their profiles. Profiles include instructor details such as name, expertise, and biography. Profile updates are also supported to keep information current.
	•	Instructor Retrieval and Pagination: List all instructors, with support for pagination to manage large datasets and provide efficient browsing for administrators and users.

Student Management

	•	Student Creation and Profile Management: New students can be added to the platform, with each student having a dedicated profile including details like name, contact information, and enrollment history. Profiles can be updated to ensure accurate records.
	•	Student Retrieval and Pagination: Retrieve all students with pagination to handle large numbers of records and streamline the search process for administrators.

Course Management

	•	Category and Subcategory Management: Define various course categories and subcategories, enabling structured organization of courses. Categories can be updated or disabled as needed to maintain a curated set of offerings.
	•	Course Creation and Updates: Create new courses with detailed information such as title, description, and duration. Courses can be updated, including changes to categories, thumbnails, and other details to keep content relevant.
	•	Course Availability and Organization: Courses can be organized by category and subcategory, and administrators have the option to disable courses temporarily or permanently as needed.

Enrollment Management

	•	Enrollment Creation: Students can enroll in courses, with each enrollment capturing details like enrollment date and course information.
	•	Enrollment Progress Tracking: Track each student’s progress within their enrolled courses, including milestones and completion status.
	•	Certification and Completion: Once a student reaches 100% progress, their enrollment can be certified, marking their completion of the course.
	•	Filtering and Sorting Enrollments: Enrollments can be sorted by enrollment date and filtered based on criteria such as course title, category, and student username, allowing for streamlined record management.

City and Country Management

	•	City and Country Listings: Maintain a catalog of cities and countries that students and instructors can reference for location data. Cities and countries can be sorted or filtered by name for easy searching.
	•	Country-Specific City Retrieval: List all cities associated with a particular country, which can be useful for location-based sorting and filtering of users.

User, Role, and Authorities Management

	•	User Profile and Role Management: Manage user profiles for both students and instructors, including details such as name, email, national ID, phone number, and gender. Each user can be assigned roles (such as student or instructor) to define their access levels.
	•	Account Status Management: Administrators can disable, enable, or permanently delete user accounts, ensuring control over platform access and maintaining security.
	•	Role and Authority Management: Manage platform roles with defined authorities, allowing for precise access control and ensuring that users have the appropriate permissions for their roles.

Technologies Used

	•	Spring Boot: Backend framework
	•	PostgreSQL: Relational database
	•	Gradle: Build and dependency management tool
