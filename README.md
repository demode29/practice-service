# aegon-practice-service

This project consists of two parts: frontend and backend.

## Backend
Backend is written in Java Spring and uses JPA and H2 embedded database. Backend java codes can be found in 
"/aegon-practice-service/tree/master/src/main/java/com/example/restservice". 

### Controller
controller folder contains RestController for routing requests. How to request to these endpoints will be mentioned below.

### Model
model folder contains objects for saving to database. Model objects are Survey, SurveyAnswer and Topic. 
Topic has One To Many relation with Survey(Survey has Many to One relation with Topic). 
Survey has One to many relation with Survey Answer(Survey Answer has Many to One relation with Survey)

### Interfaces
surveyinterfaces includes DatabaseAccessService which is a logic unit and overrides SurveyInterface interface methods. 
It also includes SurveyRepository and TopicRepository interfaces to do operations on database.

For example addSurvey method checks if topic already exists. If it is not, creates a new topic else it uses the existent topic. 
Then creates a new survey and establishes relation between specified topic and newly created survey. 

SurveyRepository and TopicRepository uses CrudRepository(gives access to methods for updating database,finding objects etc...).

### How to Request to Endpoints
It uses default port which is port 8080 on localhost


## Frontend
Frontend is written with React and uses Material UI for some components. Frontend codes can be found in "/aegon-practice-service/tree/master/frontend/src"
React app can be accessed from local host port 3000. I also added proxy to forward requests to port 8080 (there were cors errors).

MainContainer contains 4 different main components: CreateSurvey, SubmitSurvey, Topics, SurveyAnswers and all are viewed as Tab. 


Note: I did not put any refresh mechanism for
fetching values from our endpoint. We need to refresh our page if we created a survey and wants to see it.
