# :shopping_cart: Amazon Reviews Analyzer

![Custom badge](https://img.shields.io/badge/build-passing-brightgreen)
![Custom badge](https://img.shields.io/badge/build%20tool-maven-brightgreen)
![Custom badge](https://img.shields.io/badge/code%20analysis%20tools-spotbugs,%20checkstyle-orange)
![Custom badge](https://img.shields.io/badge/test%20framework-JUnit-blue)

This application scrapes a product’s Amazon reviews, extracts entities, and reveals sentiment.

## Contents

- [Technologies](#technologies)
- [Implementation details](#implementation-details)
- [Installation](#installation)

## Technologies

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

## Implementation details

### jsoup

This application uses the jsoup library to scrape and parse HTML from a URL 
and extract product reviews using CSS selectors.

### TextRazor NLP API

This application communicates with the TextRazor API to extract entities.

### Google Cloud NL API

This application communicates with the Google Cloud NL API to detect sentiment score and magnitude.

## Installation

### First steps

After cloning the project, you must set up the following two environment variables:
- TEXT_RAZOR_API_KEY: ***
- GOOGLE_APPLICATION_CREDENTIALS: service-account-file.json

For more info, see https://www.textrazor.com/signup & https://cloud.google.com/docs/authentication/production.

You also may need to install Apache Maven (https://maven.apache.org/) on your system.

### How to compile the project

```bash
mvn clean compile
```

### How to create a binary runnable package

```bash
mvn clean compile assembly:single
```

### How to run

```bash
mvn -q clean compile exec:java -Dexec.executable="service.Main" 
```

### How to run all the unit test classes

```bash
mvn clean compile test checkstyle:check  spotbugs:check
```

### How to run spotbugs 

To see bug details using the Findbugs GUI, use the following command "mvn findbugs:gui"

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

For more info see
https://spotbugs.readthedocs.io/en/latest/maven.html


### How to run checkstyle

CheckStyle code styling configuration files are in config/ directory. Maven checkstyle plugin is set to use google code style.

```bash
mvn checkstyle:check
```

Generate a report in XML format:

```bash
target/checkstyle-checker.xml
target/checkstyle-result.xml
```

Generate a report in HTML format:

```bash
mvn checkstyle:checkstyle
```

```bash
target/site/checkstyle.html
```




