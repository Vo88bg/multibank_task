# Test automation framework for https://trade.multibank.io/

A basic test framework built in JAVA with Selenium and TestNG. Also includes Slf4j and Logback classic for logging actions

## Key implementations:
- Cross-browser testing
- Parallel test execution
- Responsive tests

# Prerequisites
- Java 17
- Maven
- Download webdrivers for all browser needed for tests and include their directory to the PATH variable of the system

Note: All dependencies and plugins are included in pom.xml

### Installation
- clone with the following command <code> git clone git@gitlab.com:vo13/entain_task.git </code>
- or download from https://gitlab.com/vo13/entain_task > Code > Download ZIP

# Running tests
- via Maven with the following command:<code> mvn test -DsuiteXmlFile=testng.xml -Dbrowser=firefox </code>
- via IDE configuration by adding <code>-Dbrowser=chrome parameter</code>

### -Dbrowser options
- chrome
- firefox
- safari

## Project structure

<pre> <code>src
└── test
    └── java
        └── org
            └── example
                └── tests
                    ├── desktop
                    └── mobile
</code> </pre>

## Project expansion and possible future refactors
- All page classes must be populated with selectors of HTML elements corresponding to the respective web page
- Methods related to actions for the respective web page, should be added to the corresponding page class
- If navigation selectors are used in other pages, move them and their corresponding methods to a common Page class
- All new page classes must extend the Page class and implement PageInterface to reuse common selectors and methods
- In case of more tests related to a component in a page, create separate class named after the component and make it extend its page's class
- If user input is to be compared to the output of the platform (i.e. register new user and check that the information is saved properly), data model classes should be introduced to the framework