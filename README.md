# Banking API

## _Project Description_
This banking app authorizes specified users to deposit, withdraw, transfer, update and search the database.  The authorization levels of each user is determined by their assigned role.  The roles implemented are standard, premium, employee and admin.  Admins have the ability to access and update any users' account information, while employees only have the authority to access users' account information.  Each standard user can access their personal account.

## _Technologies Used_
- [Apache Tomcat] - version 9.0
- [Java] - version 1.8
- [Spring Tool Suite] - version 4.0
- [DBeaver] - version 21.0
- [Postman] - version 8.0
## _Features_

List of features ready :
- All users have the ability to register, login and update their accounts.
- All users must be able to update their personal information, such as username, password, first and last names, as well as email.
- All users can have either checking or savings accounts.
- Transfer of funds should be allowed between accounts owned by the same user, as well as between accounts owned by different users.
- Account status is also tracked throughout the lifespan, status possibilities are pending, open, closed, or denied.
- Pass time function that simulates the passing of time for savings Accounts to accrue interest.

To-do list :
- Seperate my business logic from my servlets.
- Allow users to upgrade account role from standard to premium, as well as implement premium specific actions.
- Password hashing
## _Getting Started_

- Install Java SE Development Kit 8
>Verify that you have installed Java by using "java -version" in command prompt.

- Install Spring Tool Suite 4
- Install Maven
- Install DBeaver
- Install Apache Tomcat
You can use Postman through the web-browser application.
## _Usage_
After project installation and relational-database setup, run the project on your appropriate server.  Use Postman to send requests (GET, POST, and PULL) to access and update your DBeaver database.  Postman has a simple and easy to use interface to test RESTful endpoints.
## _License_
Free software used throughout.


[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [Spring Tool Suite]: <https://spring.io/tools>
   [DBeaver]: <https://dbeaver.io/>
   [Java]: <https://www.oracle.com/java/technologies/javase-downloads.html>
   [Apache Tomcat]: <http://tomcat.apache.org/>
   [Postman]: <https://www.postman.com/>
   
