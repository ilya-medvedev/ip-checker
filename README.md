IP Checker
==============

Development Environment
-----------------------
1. Install Git ([Git SCM](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git))
2. Install Java Standard Edition Development Kit  8 ([Oracle](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or [OpenJDK](http://openjdk.java.net/install/))
3. Install Maven 3 ([Apache Maven Project](https://maven.apache.org/download.cgi))

Download
--------
    git clone git@github.com:imedvedko/ip-checker.git

Build
-----
    cd ip-checker
    mvn clean package

Run
---
    java -jar target/ip-checker.jar ${properties}

Properties
----------
|           Property           | Required | Default value  |             Description             |
|:----------------------------:|:--------:|:--------------:|:-----------------------------------:|
|     --ip.checker.timeout     |   True   |      300       |        Timeout (in seconds)         |
|      --spring.mail.host      |   True   | smtp.gmail.com |          SMTP server host           |
|      --spring.mail.port      |   True   |      465       |          SMTP server port           |
|    --spring.mail.username    |   True   |                |    Login user of the SMTP server    |
|    --spring.mail.password    |   True   |                |  Login password of the SMTP server  |
| --notification.email.subject |   True   |     Server     | Subject of the notification message |

See:
1. [Spring Boot Externalized Configuration](http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html)
2. [Spring Boot Common application properties](http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)
