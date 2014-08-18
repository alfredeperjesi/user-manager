User Manager REST API
---------------------

Requirements:

1. The API should provide functionality to create, retrieve, update and delete users
2. A users consists of the following information:
    a. Type (Subscriber, Administrator, Super User)
    b. First and last name
    c. Title
    d. Date of birth
    e. Email address
    f. Password
    g. Home address (Subscriber only)
    h. Billing address (Subscriber only)
3. A Subscriber must be able to register (create an account) and retrieve his own details
4. An Administrator should be able to create, retrieve and update any Subscribers
5. A Super User should be able to create, retrieve, update and delete any Subscribers or Administrators

Assumptions:

- date of birth cannot be in the future
- email address must be valid
- password must be at least 5 length
- update sends the whole user resource (including password)
- service user authenticated outside (e.g. Oauth2)
- service user id is sent in the header as serviceUserId

Implementation:
- Camel CXS-RS JAX-RS implementation with embedded Jetty camel component
- API exposed by Swagger
- one super user automatically registered at start time with id 1

Possible improvements
- add OAuth2 authentication

Build
1. Goto the user-manager folder
2. Execute mvn clean install

Run
1. Goto the user-manager/user-manager-system folder
2. Execute mvn camel:run

API documentation
http://127.0.0.1:8091/docs/index.html

Exposed API
http://127.0.0.1:8091/UserManager/users