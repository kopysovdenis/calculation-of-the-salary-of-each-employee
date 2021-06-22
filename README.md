# calculation-of-the-salary-of-each-employee

an algorithm for calculating the salary of each employee at an arbitrary point in time (as well as calculating the total
salary of all employees of the company as a whole)

# **Task**

Given:

There is a company, a company may have employees. An employee is characterized by name, date of employment, base rate (
to simplify calculations, let this default value be the same for all types of employees).

There are 3 types of employees - Employee, Manager, Sales.

Each employee can have a boss.

Each employee, except Employee, can have subordinates.

Employee's salary is the base rate plus 3% for each year of work in the company, but not more than 30% of the total
allowance

Manager's salary is the base rate plus 5% for each year of work in the company (but not more than 40% of the total bonus
for seniority) plus 0.5% of the salary of all first-level subordinates

Salary of Sales employee is the base rate plus 1% for each year of work in the company (but not more than 35% of the
total allowance for seniority) plus 0.3% of the salary of all subordinates of all levels plus 1.0% bonus for each
subordinate of all levels

Employees (except Employee) can have any number of subordinates of any kind.

Required:

Create the architecture of the classes describing this model, as well as implement the algorithm for calculating the
salary of each employee at an arbitrary point in time (as well as calculating the total salary of all employees of the
company as a whole) (a simple user interface of your choice, in fact, only for starting calculations - it is not
essential for of this task).

The system should be unit-tested (not necessarily full coverage, but there should be exemplary tests to validate the
business logic).

In addition, you need to write (in English) a brief overview of your solution to the test problem, describing the
architecture, its pros and cons (what can be improved or changed, or some other considerations for using the solution
for real purposes).

# Get start

1. download and install jdk 11
2. download and install maven
3. build a project using mavens
4. enjoy

# JDK 11

**Installation:**

_Windows_

- [download](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- Install

_Linux_

- [download](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- Edit `~/.bashrc`
- `gedit ~/.bashrc`
- Add lines:
  `JAVA_HOME={\путь\к\sdk}`
  `export JAVA_HOME`
  `PATH=$PATH:$JAVA_HOME`
  `export PATH`

_MacOS_

- [install guid](https://docs.oracle.com/javase/9/install/installation-jdk-and-jre-macos.htm#JSJIG-GUID-2FE451B0-9572-4E38-A1A5-568B77B146DE)

_Checking the installation:_

- Run on command line `java - v`
- Make sure the `JAVA_HOME` environment variable points to the correct version.
- If it does not help, watch what the Maven wrapper hooks with the command: `mvnw.cmd -v`

# **Maven**

Installation:
_WIndows:_

- [download](http://maven.apache.org/download.cgi)
- Unzip
- Open system options (`Win` +` Pause Break`)
- Go to Advanced Environment Settings
- Add the variable `MAVEN_HOME` which will contain the path to the Maven distribution
- Add variable `MAVEN_HOME` to variable` Path`

_Linux_

- [download](http://maven.apache.org/download.cgi)
- Edit `~/.bashrc`
- `gedit ~/.bashrc`
- Add lines:
  `MAVEN_HOME={\path\to\sdk}`
  `export MAVEN_HOME`
  `PATH=$PATH:$MAVEN_HOME`
  `export PATH`

_MacOS_

- `brew install maven`

**Checking the installation:**

Execute on the command line `java - v`

The console should display similar text with the version, depending on the operating system

# **Project**

**build**

In the project folder, run: `mvn install`

to tests run: `mvn test`
