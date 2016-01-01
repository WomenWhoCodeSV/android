# Top Level README WWC - android jan2016

Android study material for WWC-SV Android MeetUp is at WWC's site https://github.com/WomenWhoCodeSV/android.git

## Git Workflow
A single remote repo at WWC's site https://github.com/WomenWhoCodeSV/android.git allows read only access and provides all the labs. Each lab is an Android Studio project.

You can submit pull requests to admin (Trish/Irene/Punam).  You can fork it via Github, but you probably want to clone it so you can pull periodic updates.  


## Android testing

In 2015 the tool and framework support for Android applications was hugely improved.
Take care that you're not looking at old documenation, for eg, some documents say that only JUnit3 is supported but that is no longer true.

 
Here are some starting points: http://www.vogella.com/tutorials/AndroidTesting/article.html
https://google.github.io/android-testing-support-library/

Best practice:  public void testPreconditions() 


 
## Misc

Know diff Android application package names vs. Java package identifiers

## (Optional) Simple Remote Site Data

Uses Google App Engine to store read only data.  These are instructions for a java based remote site.
-  install maven 3.1 or higher
- browse to https://console.cloud.google.com/start and select Try App Engine (sandbox environment) then type in a project name.   NOTE YOUR Project ID, you will need it. 
- mvn archetype:generate -Dappengine-version=1.9.30 -Dapplication-id=your-app-id -Dfilter=com.google.appengine.archetypes:
- Put your files under ./src/main/webapp/
- test your tiny site locally:
    - export JAVA_HOME=$(/usr/libexec/java_home)  # for osx
    -  mvn appengine:devserver  (result: [INFO] INFO: Dev App Server is now running)
    - Browse to your data http://localhost:8080/file.json
- Push your site to appspot
- edit app-id in pom.xml 
- mvn appengine:update (result: it will launch a browser and ask for your Project ID)
- visit the url https://<project-ID>.appspot.com for example https://readonlystorage.appspot.com/file.json