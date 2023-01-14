##Rate-Limited Notification Service
###Project developed by Mario Ortega

### Try to run SolutionTest.java to see some testing about the rules and project

#Solution.java
Principal class of the project

# Notification Service
This class provides a solution for a notification service that limits the number of messages sent to a user based on a set of time rules.

# Usage
You can run the main method in the Solution class to test the implementation. The send method in the NotificationServiceImpl class takes in a TIME_RULE enumeration, a user ID, and a message. It checks if the user has exceeded the limit for the given time rule and, if not, sends the message through the Gateway class.

# Enumerations
The class uses an enumeration TIME_RULE to set the rules for the different types of notifications, this enumeration contains the quantity and the time to evaluate the limit of messages per user.

# Limitations
The current implementation only checks the limits based on the time since the last message was sent for a given user and type. More complex rules, such as checking limits based on the time of day or the user's account status, could be added in the future.

# Class NotificationServiceImpl
This class implements the main logic of the solution, in this class you can find the following methods:

# send: 
This method is in charge of verifying the time and the quantity of messages sent to a user, if the user has not exceeded the limit of messages for the given time rule, it sends the message through the Gateway class.

# Class Gateway
This class is in charge of sending the message to the user, it has an implemented method send that receive the userId and the message.