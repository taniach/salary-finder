# Storing Salaries

A Java + Maven + Spring Boot web application with REST endpoints that stores user names and salaries in H2 database using Java Persistence API. Seed data is uploaded through `data.sql`.

## Description

It has the following HTTP endpoints and params:

### GET /users

* min - minimum salary. Optional,
  defaults to 0.0..
* max - maximum salary. Optional,
  defaults to 4000.0.
* offset - first result among set to be
  returned. Optional, defaults to 0.
* limit - number of results to include.
  Optional, defaults to no limit.
* sort - NAME or SALARY, non-case
  sensitive. Optional, defaults to no
  sorting. Sort only in ascending
  sequence.

#### Sample Output

Return list of users that match specified
criteria and ordering. in JSON form:

```
{
  “results”: [
    {
      “name”: “John”,
      “salary”: 3000.0
    },
    {
      “name”: “John 2”,
      “salary”: 3500.0
    }
  ]
}
```

### POST /upload

* Content type: multipart/form-data
* Form field name: file
* Contents: CSV data
* Two columns NAME and SALARY. 
* Name is text.
* Salary is a floating point number, You do not need to ensure a specific number of
  decimal points.
* Salary must be >= 0.0. All rows with salary < 0.0 are ignored.

#### Sample Output

Return success or failure. 1 if successful
and 0 if failure. If failure, HTTP status
code should not be HTTP_OK.
File upload is an all-or-nothing operation.
The entire file’s changes are only applied
after the whole file passes validation. If
the file has an error, none of its rows
should be updated in the database.

```
{
  “success”: 1
}
```

## How To Run

* Clone this repository
* Navigate to the folder `salaryfinder`

* Build the project and run the tests with the command
```
mvn clean install
```
* A jar file will be created in the `target` folder. Run the jar file with the command
```
java -jar target/salaryfinder-0.0.1-SNAPSHOT.jar
```

Once the application runs, you will see something like this when it is running on Tomcat.

```
2022-12-06T00:43:25.884+08:00  INFO 61142 --- [main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-12-06T00:43:25.908+08:00  INFO 61142 --- [main] c.e.s.SalaryFinderApplication            : Started SalaryFinderApplication in 6.507 seconds (process running for 7.139)
```
## Viewing H2 Database

* When the Tomcat server starts, look for the following line
```
o.s.b.a.h2.H2ConsoleAutoConfiguration : H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:xxx'
```
* Copy the database URL which is of the format `jdbc:h2:mem:xxx`
* Navigate to `http://localhost:8080/h2-console`.
* Paste the DB URL. Username is sa and password is blank.
* Click Connect.

## Send Requests

You may use Postman to send HTTP GET and POST requests to test the APIs.

Create a CSV file to test POST requests. Here is a sample:

```
Name,Salary
Alice,3290.80
Bob,4000.00
Elaine,0.0
Fiona,810
Helen,900
```