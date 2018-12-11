# spring-boot-chat-client

The chat client essentially is a Spring JPA (hibernate) based chat client.

It has the following 4 operations:

1) (POST) /createUser
This is a post operation to create a user in the system. Sample body of the payload:
{
  "username": "test",
  "password": "test"
}

Sample response:
{
    "createdAt": "2018-12-11T03:47:33.600+0000",
    "updatedAt": "2018-12-11T03:47:33.600+0000",
    "user_id": 211,
    "username": "test11",
    "password": "past11"
}

2) (POST) /login
This is a post operation to login and obtain a authentication token. Sample body of the payload:
{
  "username": "test",
  "password": "test"
}

Sample response:
{
    "createdAt": "2018-12-11T04:27:29.420+0000",
    "updatedAt": "2018-12-11T04:27:29.420+0000",
    "userId": 211,
    "sessionId": "48IJPVMI8C0NTWEIZ9",
    "expiresAt": 1544588849277,
    "active": false
}

3) (POST) /sendChat/{userId}/{toUserId}/{text,voice,image}
This is a post method to send a chat of either text/voice/image types. 
This requires the "Authorization" header to be set with the token obtained from login.

Sample payload for text message:
{
	"text": "Sample test message",
	"length": 39,
	"encoding": "UTF-8"
}

Sample response: 
{"status" : "Success"}

4) (GET) /getChats/{userId}
This is a get method to get all of the users chats. 
This requires the "Authorization" header to be set with the token obtained from login.

Sample response:
[
    {
        "createdAt": "2018-12-11T05:04:26.833+0000",
        "updatedAt": "2018-12-11T05:04:26.833+0000",
        "id": 61,
        "user_id": 211,
        "text": "Sample test message",
        "length": 39,
        "encoding": "UTF-8"
    }
]

To build the jar file run:

mvn package

To run the server:

java --add-modules java.xml.bind -jar .\target\challenge-0.1.0.jar

The server should be running on localhost:8080. 
