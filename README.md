# Selenium_plug_play
Selenium Automation Framework
---------------
This is a ready-to-use Selenium-based automation framework designed for easy adaptation across different projects with minimal alteration.

🚀 Features
---------------
* Cross-browser testing (Chrome, Firefox, Edge)
* Multiple user login via Excel (username/password driven tests)
* Automatic screenshot capture on test failure
* Extent HTML reporting
* Centralized config management via config.properties
* Supports modular, scalable TestNG structure

🛠️ Tech Stack
---------------
* Java
* Selenium WebDriver
* TestNG
* Maven
* ExtentReports

🧰 Prerequisites:
---------------
* Java JDK 8 or higher,
* Eclipse IDE for Java Developers,
* TestNG plugin for Eclipse (or any IDE),
* Browsers: Chrome, Firefox, Edge,
* Browser drivers (in case automatic online download fails),
* Good Internet connection

⚙️ Installation
---------------
* Clone this repository: git clone https://github.com/Vimal096/Selenium_plug_play.git
* Open it as a Maven project in your IDE (Eclipse, IntelliJ, etc.).
* Ensure all required drivers/browsers are installed and available.

🧪 Running Tests
---------------
* Update config/config.properties as per your setup.
* Add user credentials in excel/LoginData.xlsx.
* Run the tests via TestNG XML or directly from test classes.
* Or use Maven: bash mvn clean test
* Reports will be generated under /test-output folder.

📸 Screenshots & Reports
---------------
* Failed tests trigger automatic screenshot capture.
* HTML reports are generated using ExtentReports.

👥 Contributors
---------------
* Vimal Kushwaha
* Vedant Sachdeva
