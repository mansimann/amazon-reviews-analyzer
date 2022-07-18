# :star2: Customer Review Analysis

![Custom badge](https://img.shields.io/badge/build-passing-brightgreen)
![Custom badge](https://img.shields.io/badge/build%20tool-maven-brightgreen)
![Custom badge](https://img.shields.io/badge/code%20analysis%20tools-spotbugs,%20checkstyle-orange)
![Custom badge](https://img.shields.io/badge/test%20framework-JUnit-blue)

This application parses customer review for adjectives, assesses sentiment, and outputs an appropriate response. 

## Contents

- [Technologies](#technologies)
- [Implementation details](#implementation-details)
- [Installation](#pinstallation)

## Technologies

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

## Implementation Details

### TextRazor NLP API

This application communicates with the TextRazor API to parse for adjectives.
More specifically, it uses TextRazor to extract Words (tokens) and get part of speech. 
It then uses a HashMap<String, Integer> to store mention count for every adjective.

### Google Cloud NL API

This application communicates with the Google Cloud NL API to assess sentiment.
It uses google.cloud.language.v1 package to get sentiment score and magnitude.

## Installation

### How to compile the project

You must first set up the following two environment variables:
- TEXT_RAZOR_API_KEY: ***
- GOOGLE_APPLICATION_CREDENTIALS: service-account-file.json

For more info, see https://www.textrazor.com/signup & https://cloud.google.com/docs/authentication/production.

We use Apache Maven to compile and run this project.

You need to install Apache Maven (https://maven.apache.org/) on your system.

Type on the command line:

```bash
mvn clean compile
```

### How to create a binary runnable package

```bash
mvn clean compile assembly:single
```

### How to run

```bash
mvn -q clean compile exec:java -Dexec.executable="service.service.Main" 
```

### Run all the unit test classes.

```bash
mvn clean compile test checkstyle:check  spotbugs:check
```

### Using Spotbugs to find bugs in your project

To see bug detail using the Findbugs GUI, use the following command "mvn findbugs:gui"

Or you can create a XML report by using

```bash
mvn spotbugs:gui 
```

or

```bash
mvn spotbugs:spotbugs
```

```bash
mvn spotbugs:check 
```

check goal runs analysis like spotbugs goal, and make the build failed if it found any bugs.

For more info see
https://spotbugs.readthedocs.io/en/latest/maven.html

SpotBugs https://spotbugs.github.io/ is the spiritual successor of FindBugs.

### Run Checkstyle

CheckStyle code styling configuration files are in config/ directory. Maven checkstyle plugin is set to use google code style.
You can change it to other styles like sun checkstyle.

To analyze this example using CheckStyle run

```bash
mvn checkstyle:check
```

This will generate a report in XML format

```bash
target/checkstyle-checker.xml
target/checkstyle-result.xml
```

and the following command will generate a report in HTML format that you can open it using a Web browser.

```bash
mvn checkstyle:checkstyle
```

```bash
target/site/checkstyle.html
```




