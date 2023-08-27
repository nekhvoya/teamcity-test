# TeamCity Test Automation

This project implements automation of the testing process for the TeamCity server app.

## Prerequisites:
* JDK 17
* IDE (IntelliJ IDEA)
* Gradle 8.3
* TeamCity server running locally on port 8111 (version 2023.05.2)
* TeamCity has a build agent configured running locally (a Win10 machine)
* TeamCity has an admin account with credentials admin/admin

### How to run tests
Tests can be run from the IDE by selecting a test and clicking the Run button or by running the following command: `./gradlew test`

### Reporting

When the test run is finished a test report will be generated.
The report can be found in `/build/reports/tests/test/index.html`.
If a test fails a screenshot will be made. Screenshots can be found in the following directory `/build/reports/tests/`.

### Test cases for automation

1. Log in as admin (Automated)

| Step                             | Expected result                            |
|----------------------------------|--------------------------------------------|
| Open TeamCity Login page         | Login page is opened                       |
| Enter username in Username input | Username is entered                        |
| Enter password in Password input | Password is entered                        |
| Click 'Login'                    | Projects page is opened, User is logged in |

2. Create project manually (Automated)

Preconditions: 
* admin user is logged in

| Step                                                | Expected result                                    |
|-----------------------------------------------------|----------------------------------------------------|
| Open TeamCity Projects page                         | Projects page is opened                            |
| Click 'Create New Project' button (top left corner) | Create Project page is opened                      |
| Enter project name in Name input                    | Project name is entered                            |
| Click 'Create'                                      | Project is created, Edit project page is opened    |
| Open Projects page                                  | Created project is visible in the list of projects |

3. Create build manually (Automated)

Preconditions: 
* admin user is logged in
* test project is created

| Step                                               | Expected result                                           |
|----------------------------------------------------|-----------------------------------------------------------|
| Open TeamCity Projects page                        | Projects page is opened, test project is listed           |
| Find test project, click '+' > 'New Configuration' | Create Build page is opened                               |
| Enter build name in Name input                     | Build name is entered                                     |
| Click 'Create'                                     | Edit VCS Root page is opened                              |
| Open Projects page                                 | Created project is visible in the list of projects        |
| Expand create project                              | Created build is visible under expanded project           |

4. Create build step (Automated)

Preconditions: 
* admin user is logged in
* test project is created
* build is created in test project

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

5. Run build (Automated)

Preconditions: 
* admin user is logged in
* test project is created
* build is created in test project
* build step is configured (e.g., simple command line runner)

| Step                                          | Expected result                                    |
|-----------------------------------------------|----------------------------------------------------|
| Open TeamCity Projects page                   | Projects page is opened, test project is displayed |
| Expand test project                           | Created build is displayed                         |
| Click 'Run' related to created build          | Run is started                                     |
| Wait for run to complete and check its result | Run is completed successfully                      |

6. Configure VCS

* admin user is logged in
* test project is created
* build is created in test project
* there is local test Git repository

| Step                                          | Expected result                                                      |
|-----------------------------------------------|----------------------------------------------------------------------|
| Open TeamCity Projects page                   | Projects page is opened, test project is displayed                   |
| Expand test project                           | Created build is displayed                                           |
| Click created build link                      | Build Configuration page is opened                                   |
| Click 'Edit Configuration'                    | Edit Build page is opened                                            |
| Select 'Version Control Settings' in side bar | VSC Roots page is opened                                             |
| Click 'Attach VCS Root'                       | New VCS Root form is opened                                          |
| Select 'Git' in 'Type of VCS' dropdown        | 'Git' option is selected                                             |
| Enter 'VCS root name'                         | VCS root name is entered                                             |
| Enter 'Fetch URL'                             | Repository url is entered                                            |
| Enter 'Default branch'                        | Default branch is entered                                            |
| Click 'Create'                                | VSC Roots page is opened, VCS root is created and listed on the page |


7. Checkout from VCS

Preconditions:
* admin user is logged in
* test project is created
* build is created in test project
* VSC root is configured
* the repository contains a PowerShell script (e.g., echo <randomString>) 
* build step is configured to run ps script from the VCS

| Step                                          | Expected result                                                |
|-----------------------------------------------|----------------------------------------------------------------|
| Open TeamCity Projects page                   | Projects page is opened, test project is displayed             |
| Expand test project                           | Created build is displayed                                     |
| Click created build link                      | Build Configuration page is opened                             |
| Click 'Run'                                   | Run is started                                                 |
| Wait for run to complete and check its result | Run is completed successfully                                  |
| Click the run number                          | Details of the selected run are displayed                      |
| Click 'Build Log'                             | Build Log tab is opened                                        |
| Verify the build logs                         | The logs contain output of the test script from the repository |


8. Create New User Account

Preconditions:
* admin user is logged in

| Step                                     | Expected result                                              |
|------------------------------------------|--------------------------------------------------------------|
| Open TeamCity Projects page              | Projects page is opened, test project is displayed           |
| Click 'Administration' in header         | Administration page is opened                                | 
| Select 'Users' in the side bar           | Users list is displayed                                      |
| Click 'Create User Account'              | Create User page is opened                                   |
| Enter username in Username input         | Username is entered                                          |
| Enter password in Password input         | Password is entered                                          |
| Enter password in Confirm Password input | Password is entered                                          |
| Click 'Create User'                      | Users list is displayed, created user is present in the list |

9. Log in as newly created user

Preconditions:
* additional non-admin account is created

| Step                                         | Expected result                            |
|----------------------------------------------|--------------------------------------------|
| Open TeamCity Login page                     | Login page is opened                       |
| Enter username of new user in Username input | Username is entered                        |
| Enter password of new user in Password input | Password is entered                        |
| Click 'Login'                                | Projects page is opened, User is logged in |

10. Create build agent

Preconditions:
* Docker instance up and running
* Pull the agent image: docker pull jetbrains/teamcity-agent
* Start a linux container: docker run -e SERVER_URL="<url to TeamCity server>" -v <path to agent config folder>:/data/teamcity_agent/conf jetbrains/teamcity-agent

| Step                                      | Expected result                                                   |
|-------------------------------------------|-------------------------------------------------------------------|
| Open TeamCity Projects page               | Projects page is opened, test project is displayed                |
| Click 'Agents' in header                  | Agents Overview page is opened, 1 unauthorised agent is displayed | 
| Expand Unauthorized list                  | The newly added agent is listed under Unauthorized category       |
| Click agent link                          | Agent Details page is opened                                      |
| Click 'Authorize'                         | Authorize host popup appears                                      | 
| Click 'Authorize' in Authorize host popup | Agent is displayed as Authorized, Connected and Enabled           |

11. Run build on a newly created agent

Preconditions:
* Docker instance up and running
* Pull the agent image: docker pull jetbrains/teamcity-agent
* Start a linux container: docker run -e SERVER_URL="<url to TeamCity server>" -v <path to agent config folder>:/data/teamcity_agent/conf jetbrains/teamcity-agent
* Newly created build agent is enabled and authorized
* admin user is logged in
* test project is created
* build is created in test project
* build step is configured to run linux commands (e.g., simple command line runner)

| Step                                          | Expected result                                    |
|-----------------------------------------------|----------------------------------------------------|
| Open TeamCity Projects page                   | Projects page is opened, test project is displayed |
| Expand test project                           | Created build is displayed                         |
| Click created build link                      | Build Configuration page is opened                 |
| Click 'Run'                                   | Run is started                                     |
| Wait for run to complete and check its result | Run is completed successfully                      |
| Verify build agent used for run               | Run was executed on newly added agent              |