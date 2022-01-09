# beFit.
## Problem Statement
To keep ourselves fit, exercising is very important. One of the challenges that 
people face is consistency and regularity in going to the gym and performing the exercises due to lack of 
motivation. Mobile Applications can be used to motivate people to exercise for the prescribed 
amount of time since they are widely used in recent times.
## Proposed solution
The “beFit.” application is designed to provide guidance to perform exercises 
targeting different parts of the body. The main advantage of the app is that it is offline, hence easily 
accessible and is very easy to use. The instructions to perform each exercise is given in a brief and 
precise manner along with graphics. The countdown timer set along with each exercise helps the 
user to perform it for the right amount of time. The attractive and simple design of the app will 
definitely motivate the user to exercise regularly.

![image](https://user-images.githubusercontent.com/34862954/148694728-62639d2b-137c-40cf-9177-b94919d38a25.png)
![image](https://user-images.githubusercontent.com/34862954/148694808-366b76eb-ed1e-423b-8a37-c93675314afc.png)


## Functionality and Concepts Used
The app has a clean and aesthetic UI. The design of this app is made so that it is effortless to use and interact with. The color scheme is consistent throughout the app and follows an excellent visual design.

#### Layout and Components: 
It uses Constraint Layout to arrange the UI elements in an orderly manner and maintain consistency. Here we also implemented some Material UI Design Elements such as CardView, Button, FloatingActionButton(FAB). Linear Layout is also used as a parent layout in the CardView.

#### CountDownTimer: 
This app also contains a countdown timer which shows the time left for a specific exercise. The timer consists of a TextView that gets updated with CountDownTimer Class's help.

#### RecyclerView: 
To handle the list of various activities, RecyclerView is used in this app.

#### SharedPreferences
SharedPreferences is used extensively in this app to keep track of the data of all exercises and their description. A mutable map is used along with the shared preferences to provide a flawless experience.

## Application Link & Future Scope
You can access the app via this link:
https://www.mediafire.com/file/98ut7q0z5gulk1u/beFit-v1.0.1.apk/file
<link>
or explore our GitHub repo

In the future, we aim to add more features to this app to make it more user-friendly and a perfect fitness companion. We will also be adding features like daily progress tracking, diet suggestion, calorie intake calculator, integrating Google Maps API to show Gyms Near Me, and a User Profile System to keep track of their fitness.
