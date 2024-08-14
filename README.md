Quiz App
This is a simple Quiz application built using Kotlin, Jetpack Compose, MVVM architecture, and Room Database. The app provides users with a smooth and intuitive quiz experience, featuring light and dark themes.

Features
Quiz Creation: Users can create their own quizzes with custom questions.
Taking Quizzes: Users can take quizzes, answer multiple-choice questions, and receive immediate feedback.
Local Data Storage: All quizzes and results are stored locally using Room Database.
Dark and Light Themes: The app supports both dark and light modes for an enhanced user experience.
Intuitive UI: Designed with Jetpack Compose for a modern and responsive user interface.
Technologies Used
Kotlin: The programming language used for the entire application.
Jetpack Compose: Used for building the UI components.
MVVM Architecture: The architectural pattern followed to separate concerns and manage UI-related data in a lifecycle-conscious way.
Room Database: Used for local data persistence, ensuring quizzes and results are saved even when the app is closed.
Coroutines: For managing background tasks and ensuring smooth UI operations.
Project Structure
The project is structured to follow the MVVM (Model-View-ViewModel) architecture:

Model: Represents the data and business logic of the application. This includes the database entities and data access objects (DAO) using Room.
View: The UI components created using Jetpack Compose.
ViewModel: Holds the UI-related data and communicates with the Model layer. It also manages the UI state and handles user interactions.
Setup and Installation
To run the application locally:

Clone the repository:

bash
Sao chép mã
git clone https://github.com/thinguyendinh04/QuizApplication.git
cd QuizApplication
Open the project in Android Studio.

Build and run the application on an emulator or physical device.

How It Works
Data Storage: The quizzes and results are stored in a local Room Database.
Creating a Quiz: Users can create new quizzes by adding questions and answers.
Taking a Quiz: Users select a quiz, answer the questions, and receive feedback on their performance.
Results Storage: The results are stored locally and can be viewed anytime.
Future Enhancements
Online Sync: Ability to sync quizzes and results with an online database.
Leaderboards: Implementing leaderboards to show top performers.
More Question Types: Supporting more types of questions (e.g., True/False, Fill-in-the-blank).
License
This project is licensed under the MIT License - see the LICENSE file for details.
