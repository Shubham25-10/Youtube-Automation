# YouTube Automation Testing

## Overview
This project automates various functionalities of YouTube using Selenium with Java, TestNG, a Wrapper Class, and the Gradle Automation Framework.

## Technologies Used
- **Selenium WebDriver** - For browser automation
- **Java** - Programming language for scripting
- **TestNG** - Test framework for structuring and executing test cases
- **Wrapper Class** - For enhancing reusability and handling web elements efficiently
- **Gradle** - Build and dependency management tool

## Test Cases Implemented

### **Test Case 01**: Verify YouTube URL and About Section
- Navigate to [YouTube.com](https://www.youtube.com/).
- Assert that the URL is correct.
- Click on the "About" link in the sidebar.
- Print the displayed message on the screen.

### **Test Case 02**: Validate Top Selling Movies
- Navigate to the "Films" or "Movies" tab.
- Scroll to the extreme right in the “Top Selling” section.
- Apply a **Soft Assert** to verify whether a movie is marked "A" for mature audiences.
- Soft Assert on the movie category to check if it exists (e.g., "Comedy", "Animation", "Drama").

### **Test Case 03**: Validate Music Playlists
- Navigate to the "Music" tab.
- Identify the name of the playlist located at the extreme right in the first section.
- Print the playlist name.
- Soft Assert that the number of tracks listed is **≤ 50**.

### **Test Case 04**: Extract Latest News
- Navigate to the "News" tab.
- Print the title and body of the first **three "Latest News Posts"**.
- Sum the number of likes across all three posts and display the total.
- If no likes are given, assume **0**.

## How to Run the Tests
1. Clone the repository:
   ```sh
   git clone <repository_url>
   ```
2. Navigate to the project directory:
   ```sh
   cd YouTubeAutomation
   ```
3. Execute tests using Gradle:
   ```sh
   gradle test
   ```

## Prerequisites
- Java (JDK 8+)
- Gradle installed
- ChromeDriver (Ensure compatibility with the Chrome version installed)
- Internet connectivity for accessing YouTube

## Author
- **Shubham Sharma**
