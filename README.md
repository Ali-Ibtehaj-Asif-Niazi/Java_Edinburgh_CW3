# Advanced Software Engineering (F21AS)

## Coursework 2024

### Git commands for collaboration:
1. Check in which branch you are ```git branch```
2. if (create new branch)
     ```git checkout -b branch_name```
   else (switch to existing branch)
     ```git checkout branch_name```
3. Make the changes in your local repo clone
4. Check the files you have changed appear in git by ```git status```
5. If everything looks good the stage the changes using ```git add .```
6. Now commit the changes ```git commit -m "message"```
7. Now push the changes ```git push origin branch_name```
8. Then go to the repo on github and click create pull request and its done
9. Do check your pull request later whether it is merged or not or did I write a comment in it that needs to be fixed
(Note: before making a commit do check your code editor's source control section to make sure no .class file is added)

### Git commands for collaboration:

How to run the code using the terminal:
1. Make sure you are using the updated java JDK. The one I am using is:
   ```
   ╰─λ java --version
   openjdk 21.0.2 2024-01-16
   OpenJDK Runtime Environment (build 21.0.2+13)
   OpenJDK 64-Bit Server VM (build 21.0.2+13, mixed mode, sharing)
   ```
   Older versions might also work, if you face any problem then update.
2. In the local repo clone run this command on the terminal to compile the code:
   ```javac CW.java```
3. Then run this command to execute the code:
   ```java CW.java```
   I might change the name of this main file from CW to something else but for now its fine.

(Note: if you are using Eclipse thats fine but make sure that you dont add stuff to the code that's exclusive to Eclipse like package name in the start e.t.c.)
