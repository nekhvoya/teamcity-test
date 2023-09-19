# TeamCity Test Automation

This project implements automation of the testing process for the TeamCity server app.

## Prerequisites:
* JDK 17
* IDE (IntelliJ IDEA)
* Gradle 8.3
* TeamCity server running locally on port 8111 (version 2023.05.2)
* TeamCity has a build agent configured running locally (a Win10 machine)
* TeamCity has an admin account with credentials admin/admin
* Docker instance is up and running locally

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
| Verify user permissions          | User is admin                              |

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
| Verify user permissions                      | User doesn't have admin permissions                          |

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
* There is test public Github repository with simple maven project and teamcity build config (settings.kts)
* admin user is logged in

| Step                                                 | Expected result                                                   |
|------------------------------------------------------|-------------------------------------------------------------------|
| Open TeamCity Projects page                          | Projects page is opened, test project is displayed                |
| Click 'Create New Project' button (top right corner) | Create Project page is opened                                     |
| Select 'From Repository URL' tab                     | Create From VCS form is displayed                                 |
| Enter Github repo URL                                | Repo URL is entered                                               |
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

| Step                                         | Expected result                                    |
|----------------------------------------------|----------------------------------------------------|
| Open TeamCity Projects page                  | Projects page is opened, test project is displayed |
| Click 'Administration'                       | Administration page is opened                      |
| Expand test project in projects list         | Created build is displayed                         |
| Click build configuration link               | Edit Build page is opened                          | 
| Select 'Version Control Settings' in sidebar | Version Control Settings page is opened            |
| Verify that VCS was polled at least once     | Date of latest check for changes is displayed      |
| Select 'Triggers' in sidebar                 | Edit Triggers page is opened                       |
| Click 'Add New Trigger'                      | Add Trigger dialog is opened                       |
| Select 'VCS Trigger'                         | VCS Trigger is selected                            |
| Click 'Save'                                 | Trigger is saved and listed on Edit Triggers page  |
| Click 'Projects'                             | Projects page is opened                            |
| Expand created project                       | Created build is displayed                         |
| Commit changes in configured VCS repo        | Changes are committed                              |
| Wait for run to start and complete           | Run is executed successfully                       |

7. Publish artifacts

* admin user is logged in
* project with build configuration and build step is configured, VCS is configured
* there is project in Github with code producing relevant files (e.g., a building jar)

| Step                                                | Expected result                                                 |
|-----------------------------------------------------|-----------------------------------------------------------------|
| Open TeamCity Projects page                         | Projects page is opened, test project is displayed              |
| Click 'Administration'                              | Administration page is opened                                   |
| Expand test project in projects list                | Created build is displayed                                      |
| Click build configuration link                      | Edit Build page is opened                                       | 
| Enter path to produced file in Artifacts Path input | Path is entered                                                 |
| Click 'Save'                                        | Changes are saved                                               |
| Click 'Run'                                         | Build Configuration page is opened, Build Overview is displayed |
| Wait for run to complete                            | Run is executed successfully                                    |
| Open Artifacts tab                                  | Artifacts tab is opened, configured artifact is listed          |
| Click 'Download all'                                | Zip archive is downloaded, archive contains configured artifact |


8. Configure dependencies

Preconditions:
* admin user is logged in
* project is configured in TeamCity with at least 2 build configurations, 1st produces artifacts

| Step                                                                  | Expected result                                                                                 |
|-----------------------------------------------------------------------|-------------------------------------------------------------------------------------------------|
| Open TeamCity Projects page                                           | Projects page is opened, test project is displayed                                              |
| Click 'Administration'                                                | Administration page is opened                                                                   |
| Expand test project in projects list                                  | 2 build configurations are displayed                                                            |
| Click 2d build configuration                                          | Edit Build page is opened                                                                       | 
| Click 'Dependencies' in sidebar                                       | Edit Dependencies page is opened                                                                |
| Click 'Add new snapshot dependency'                                   | Add Snapshot Dependency dialog is opened                                                        |
| Select 1st build configuration of test project in dependency selector | Build configuration is selected                                                                 |
| Click 'Save'                                                          | Snapshot dependency is created and listed on Edit Dependencies page                             | 
| Click 'Add new artifact dependency'                                   | Add Artifact Dependency dialog is opened                                                        |
| Select 1st build configuration in Depend On dropdown                  | Build configuration is selected                                                                 |
| Enter rule for copying in Artifacts Rules input                       | Rule is entered                                                                                 |
| Click 'Save'                                                          | Artifact dependency is created and listed on Edit Dependencies page                             |
| Click 'Run'                                                           | Build Configuration page is opened, Build Overview is displayed                                 |
| Wait for run to complete                                              | Run is executed successfully                                                                    |
| Open Dependencies tab                                                 | Dependencies tab is opened                                                                      |
| Select 'Snapshot Dependencies' tab > 'List' tab                       | Successful run of 1st build configuration is listed                                             |
| Select 'Downloaded Artifacts' tab                                     | Successful run of 1st build configuration is listed, Downloaded Artifacts dropdown is displayed |
| Expand 'Downloaded Artifacts' dropdown                                | Artifact from 1st build is available                                                            |
| Click expanded artifact                                               | Zip archive is downloaded, archive contains artifact                                            |


9. Configure deployment

Preconditions:
* admin user is logged in
* there is Github repo with test docker file
* project is configured in TeamCity with configured VCS and 1 build configuration building a docker image
* there is test dockerhub account

| Step                                                                  | Expected result                                                             |
|-----------------------------------------------------------------------|-----------------------------------------------------------------------------|
| Open TeamCity Projects page                                           | Projects page is opened, test project is displayed                          |
| Click 'Administration'                                                | Administration page is opened                                               |
| Click test project in projects list                                   | Edit project page is opened                                                 |
| Click 'Connections' in sidebar                                        | Connections form is opened                                                  |
| Click 'Add Connection'                                                | Add Connection dialog is opened                                             |
| Select 'Docker Reguistry' as Connection Type                          | Docker Registry is selected                                                 |
| Enter username                                                        | Username is entered                                                         |
| Enter password                                                        | Password is entered                                                         |
| Click 'Save'                                                          | Add Connection dialog is closed, connection is created                      | 
| Click 'General Settings' in sidebar                                   | General Settings form is opened                                             |
| Click 'Create build configuration'                                    | Create Build page is opened                                                 |
| Enter build name in Name input                                        | Build name is entered                                                       |
| Click 'Show advanced options'                                         | Build configuration type option is displayed                                | 
| Expand 'Build configuration type' > Select 'Deployment'               | Deployment type is selected                                                 |
| Click 'Create'                                                        | Edit Build page is opened                                                   |
| Select 'Build Steps' in side bar                                      | Edit Build Runner page is opened                                            |
| Click '+ Add Build Step'                                              | New Build Step page is opened                                               |
| Select 'Command Line'                                                 | New Build Step: Command Line form is opened                                 |  
| Enter name in Step name input                                         | Step name is entered                                                        |
| Enter docker push command in Custom script input                      | Command is entered                                                          |
| Click 'Save'                                                          | Edit Build Runner page is opened, build step is created                     |
| Click 'Build Features' in sidebar                                     | Edit Build Features page is opened                                          |
| Click 'Add Build Feature'                                             | Add Build Feature dialog is opened                                          |
| Select 'Docker Support' build feature                                 | Docker Support is selected                                                  |
| Click 'Add Registry Connection'                                       | Add Registry Connection dialog is opened                                    |
| Select created docker connection                                      | Docker connection is selected                                               |
| Click 'Add'                                                           | Add Registry Connection dialog is closed, docker connection is added        |
| Click 'Save'                                                          | Add Registry Connection dialog is closed, docker support feature is created |
| Click 'Dependencies' in sidebar                                       | Edit Dependencies page is opened                                            |
| Click 'Add new snapshot dependency'                                   | Add Snapshot Dependency dialog is opened                                    |
| Select 1st build configuration of test project in dependency selector | Build configuration is selected                                             |
| Click 'Save'                                                          | Snapshot dependency is created and listed on Edit Dependencies page         | 
| Click 'Projects' in header                                            | Projects page is opened, test project is displayed                          |
| Expand test project                                                   | Created build is displayed                                                  |
| Click regular build configuration link                                | Build configuration page is opened                                          |
| Click 'Run'                                                           | Run is started                                                              |
| Wait for run to complete                                              | Run is executed successfully                                                |
| Click build run                                                       | Build Configuration page is opened, Build Overview is displayed             |
| Expand 'Deployments'                                                  | Deployment build is listed                                                  |
| Click 'Deploy'                                                        | Run is started                                                              |
| Wait for run to complete                                              | Run is executed successfully                                                |
| Verify deployment                                                     | Image is available in Dockerhub                                             |


10. Viewing test results

Preconditions:
* admin user is logged in
* there is Github repo with test projects with unit tests (one test failing)
* project is configured in TeamCity with configured VCS

| Step                          | Expected result                                                                  |
|-------------------------------|----------------------------------------------------------------------------------|
| Open TeamCity Projects page   | Projects page is opened, test project is displayed                               |
| Expand test project           | Created build is displayed                                                       |
| Click build link              | Build configuration page is opened                                               |
| Click 'Run'                   | Run is started                                                                   |
| Wait for run to complete      | Run completed with failed tests                                                  |
| Click build run               | Build Configuration page is opened, Build Overview is displayed                  |
| Open Tests tab                | Tests tab is opened                                                              |
| Verify test list              | All tests are listed, results are displayed (all successful and 1 failed)        |
| Click failed test             | Test details are expanded, stacktrace is displayed                               |
| Click 'Open Build Log'        | Build Log popup is opened, build logs are displayed with summary of test results |


11. Configuring SSO authentication modules

Preconditions:
* admin user is logged in
* there is test account on Github, TeamCity app is registered on Github

| Step                                                                   | Expected result                                                         |
|------------------------------------------------------------------------|-------------------------------------------------------------------------|
| Open TeamCity Projects page                                            | Projects page is opened, test project is displayed                      |
| Click 'Administration'                                                 | Administration page is opened                                           |
| Click root project                                                     | Edit project page is opened                                             |
| Click 'Connections' in sidebar                                         | Connections form is displayed                                           |
| Click 'Add Connection'                                                 | Add Connection dialog is opened                                         |
| Enter Github client ID                                                 | Client ID is entered                                                    |
| Enter Github client secret                                             | Client secret is added                                                  |
| Click 'Save'                                                           | Add Connection dialog closed, connection is created                     |
| Click 'Administration'                                                 | Administration page is opened                                           |
| Click 'Authentication' in sidebar                                      | Authentication form is displayed                                        |
| Click 'Add Module'                                                     | Add Module dialog is opened                                             |
| Select 'Github.com' in New Module dropdown                             | Github.com option is selected                                           |
| Verify Allow creating new users on the first login checkbox is checked | Allow creating new users on the first login checkbox is checked         |
| Click 'Add'                                                            | Add Module dialog is closed, new SSO module is created                  |
| Log out                                                                | Login page is opened                                                    |
| Click Github icon                                                      | Github Authorization page is opened                                     | 
| Click 'Authorise user'                                                 | Projects page is opened, User is logged in with Github account          |
| Verify user permissions                                                | User doesn't have admin permissions                                     |
| Log out                                                                | Login page is opened                                                    |
| Log in as Admin                                                        | Projects page is opened                                                 |
| Click 'Administration'                                                 | Administration page is opened                                           |
| Click 'Users' in sidebar                                               | Users form is opened                                                    |
| Verify list of users                                                   | New user is created with info from Github account, user is not an admin |