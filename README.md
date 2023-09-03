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
Tests can be run from the IDE by selecting a test and clicking the Run button or by running the following command: `./gradlew test`.

### Reporting
When the test run is finished a test report will be generated.
The report can be found in `/build/reports/tests/test/index.html`.
If a test fails a screenshot will be made. Screenshots can be found in the following directory: `/build/reports/tests/`.

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

| Step                                                 | Expected result                                                   |
|------------------------------------------------------|-------------------------------------------------------------------|
| Open TeamCity Projects page                          | Projects page is opened                                           |
| Click 'Create New Project' button (top right corner) | Create Project page is opened                                     |
| Enter project name in Name input                     | Project name is entered                                           |
| Click 'Create'                                       | Project is created, Edit project page is opened                   |
| Click 'Create build configuration                    | Create Build page is opened                                       |
| Enter build name in Name input                       | Build name is entered                                             |
| Click 'Create'                                       | Edit VCS Root page is opened                                      |
| Select 'Git' in 'Type of VCS' dropdown               | 'Git' option is selected                                          |
| Enter 'VCS root name'                                | VCS root name is entered                                          |
| Enter 'Fetch URL'                                    | Repository url is entered                                         |
| Enter 'Default branch'                               | Default branch is entered                                         |
| Click 'Create'                                       | VSC Roots page is opened, VCS root is created                     |
| Select 'Build Steps' in side bar                     | Edit Build Runner page is opened                                  |
| Click '+ Add Build Step'                             | New Build Step page is opened                                     |
| Select 'PowerShell' in the list of runners           | New Build Step: PowerShell form is opened                         |  
| Enter path to script file in Script File input       | File path is entered                                              |
| Click 'Create'                                       | Edit Build Runner page is opened, build step created              |
| Click 'Run'                                          | Build Configuration page is opened, Build Overview is displayed   |
| Wait for run to complete                             | Run is executed successfully                                      |
| Open Build Log tab                                   | Build Log tab is opened                                           |
| Expand and verify step logs                          | Step logs contain output of the test script from VCS              |


3. Create New User Account and login as newly created user

Preconditions:
* admin user is logged in

| Step                                         | Expected result                                              |
|----------------------------------------------|--------------------------------------------------------------|
| Open TeamCity Projects page                  | Projects page is opened, test project is displayed           |
| Click 'Administration' in header             | Administration page is opened                                | 
| Select 'Users' in the side bar               | Users list is displayed                                      |
| Click 'Create User Account'                  | Create User page is opened                                   |
| Enter username in Username input             | Username is entered                                          |
| Enter password in Password input             | Password is entered                                          |
| Enter password in Confirm Password input     | Password is entered                                          |
| Click 'Create User'                          | Users list is displayed, created user is present in the list |
| Log out                                      | Login page is opened                                         |
| Enter username of new user in Username input | Username is entered                                          |
| Enter password of new user in Password input | Password is entered                                          |
| Click 'Login'                                | Projects page is opened, User is logged in                   |

4. Create build agent

Preconditions:
* Docker up and running on localhost with a Hyper-V engine, exposed on port 2375, temp dir is configured as allowed for sharing
* Pull agent image: docker pull jetbrains/teamcity-agent
* Start linux container: docker run -e SERVER_URL="<url to TeamCity server>" -v <path to agent config folder>:/data/teamcity_agent/conf jetbrains/teamcity-agent
* admin user is logged in
* test project is created
* build is created in test project
* build step is configured to run linux commands (e.g., simple command line runner)

| Step                                          | Expected result                                                   |
|-----------------------------------------------|-------------------------------------------------------------------|
| Open TeamCity Projects page                   | Projects page is opened, test project is displayed                |
| Click 'Agents' in header                      | Agents Overview page is opened, 1 unauthorised agent is displayed | 
| Expand Unauthorized list                      | Newly added agent is listed under Unauthorized category           |
| Click agent link                              | Agent Details page is opened                                      |
| Click 'Authorize'                             | Authorize host popup appears                                      | 
| Click 'Authorize' in Authorize host popup     | Agent is displayed as authorized, connected and enabled           |
| Click 'Administration'                        | Administration page is opened                                     |
| Expand test project in projects list          | Created build is displayed                                        |
| Click created build link                      | Edit Build page is opened                                         | 
| Click 'Agent Requirements' in sidebar         | Agent Requirements page is opened                                 |
| Click 'Add New Requirement'                   | Edit Requirement dialog is opened                                 |
| Enter parameter                               | Parameter is entered                                              |                                   
| Select condition                              | Condition is selected                                             |                                   
| Enter value                                   | Value is entered                                                  |                             
| Click 'Save'                                  | Edit Requirement dialog is closed, requirement is updated         |
| Click 'Projects' in header                    | Projects page is opened, test project is displayed                |
| Expand test project                           | Created build is displayed                                        |
| Click 'Run'                                   | Run is started                                                    |
| Wait for run to complete and check its result | Run is completed successfully                                     |
| Verify build agent used for run               | Run was executed on newly added agent                             |


5. Configure build from VCS

Preconditions:
* There is test public github repository with simple maven project and teamcity build config (settings.kts)
* admin user is logged in

| Step                                                 | Expected result                                                   |
|------------------------------------------------------|-------------------------------------------------------------------|
| Open TeamCity Projects page                          | Projects page is opened, test project is displayed                |
| Click 'Create New Project' button (top right corner) | Create Project page is opened                                     |
| Select 'From Repository URL' tab                     | Create From VCS form is displayed                                 |
| Enter github repo URL                                | Repo URL is entered                                               |
| Click 'Proceed'                                      | Create Project From URL form is opened                            |
| Verify VCS settings radio buttons                    | 'Import settings from .teamcity/settings.kts' is selected         |
| Enter unique project name                            | Project name is entered                                           |
| Click 'Proceed'                                      | Edit Project page is opened, build configurations are displayed   |
| Open build configuration                             | Edit Build page is opened                                         |
| Click 'Run'                                          | Build Configuration page is opened, Build Overview is displayed   |
| Wait for run to complete                             | Run is executed successfully                                      |
| Open Build Log tab                                   | Build Log tab is opened                                           |
| Expand and verify step logs                          | Step logs contain output of the test script from VCS              |

6. Configure VCS triggers

Preconditions:
* admin user is logged in
* test project is created
* build is configured in test project
* build step is configured
* VCS is configured

| Step                                        | Expected result                                             |
|---------------------------------------------|-------------------------------------------------------------|
| Open TeamCity Projects page                 | Projects page is opened, test project is displayed          |
| Click 'Administration'                      | Administration page is opened                               |
| Expand test project in projects list        | Created build is displayed                                  |
| Click created build link                    | Edit Build page is opened                                   | 
| Select 'Triggers' in sidebar                | Edit Triggers page is opened                                |
| Click 'Add New Trigger'                     | Add Trigger dialog is opened                                |
| Select 'VCS Trigger'                        | VCS Trigger is selected                                     |
| Click 'Save'                                | Trigger is saved and listed on Edit Triggers page           |
| Commit changes in configured VCS repo       | Changes are committed                                       |
| Click 'Projects'                            | Projects page is opened                                     |
| Expand created project, click created build | Build Configuration page is opend, successful run is listed |
| Open Build Log tab                          | Build Log tab is opened                                     |
| Expand and verify step logs                 | Step logs contain output of the test script from VCS        |

7. Configure deployment


8. Configure pipeline

