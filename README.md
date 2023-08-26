# TeamCity Test Automation

This project implements automation of the testing process for the TeamCity server app.

## Prerequisites:
* JDK 17
* IDE (IntelliJ IDEA)
* Gradle 8.2
* TeamCity server running locally on port 8111 (version 2023.05.2)
* TeamCity has a user account created with credentials admin/admin

### Test cases for automation

1. Log in with valid credentials (Automated)

| Step                             | Expected result                            |
|----------------------------------|--------------------------------------------|
| Open TeamCity Login page         | Login page is opened                       |
| Enter username in Username input | Username is entered                        |
| Enter password in Password input | Password is entered                        |
| Click 'Login'                    | Projects page is opened, User is logged in |

2. Create project manually (Automated)

Preconditions: User is logged in

| Step                                                     | Expected result                                    |
|----------------------------------------------------------|----------------------------------------------------|
| Open TeamCity Projects page                              | Projects page is opened                            |
| Click 'Create New Project' button at the top left corner | Create Project page is opened                      |
| Enter project name in Name input                         | Project name is entered                            |
| Click 'Create'                                           | Project is created, Edit project page is opened    |
| Open Projects page                                       | Created project is visible in the list of projects |

3. Create build manually

Preconditions: User is logged in, test project is created

| Step                                               | Expected result                                           |
|----------------------------------------------------|-----------------------------------------------------------|
| Open TeamCity Projects page                        | Projects page is opened, test project is listed           |
| Find test project, click '+' > 'New Configuration' | Create Build page is opened                               |
| Enter build name in Name input                     | Build name is entered                                     |
| Click 'Create'                                     | Edit VCS Root page is opened                              |
| Open Projects page                                 | Created project is visible in the list of projects        |
| Expand create project                              | Created build is visible under expanded project           |

4. Create build step

Preconditions: User is logged in, test project is created, build is created in test project

| Step                                          | Expected result                                                                                          |
|-----------------------------------------------|----------------------------------------------------------------------------------------------------------|
| Open TeamCity Projects page                   | Projects page is opened, test project is listed                                                          |
| Expand test project, click created build link | Build Configuration page is opened                                                                       |
| Click 'Edit Configuration'                    | Edit Build page is opened                                                                                |
| Select 'Build Steps' in side bar              | Edit Build Runner page is opened                                                                         |
| Click '+ Add Build Step'                      | New Build Step page is opened                                                                            |
| Select 'Command Line' in the list of runners  | New Build Step: Command Line form is opened                                                              |    
| Enter step name                               | Step name is entered                                                                                     |
| Enter simple test script                      | Script is entered                                                                                        |
| Click 'Save'                                  | Edit Build Runner page is opened, build settings are saved, build step is displayed in the list of steps |                       
