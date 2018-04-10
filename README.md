# ssdi-team-4-v1

## Installing this repository

### Recommended tools
* [cmder](http://cmder.net/) - A console emulator for Windows

### Cloning the repository and linking with Eclipse
1. In a terminal window, navigate to your Eclipse workspace directory. For example:

   `cd C:\Users\<user>\eclipse-workspace\`

2. Create an empty workspace folder for the project:

   `mkdir winevault`

3. Clone the repository (not its containing folder) using:

   `git clone https://github.com/hpratt2/ssdi-team-4-v1.git .`
   
   *Note: Don't forget the dot (.) at the end of this command.*
   
4. Open Eclipse and navigate to **File > Import > Projects from Folder or Archive**. Select the folder **winevault** you've just created and cloned the repository into.

5. If you have any errors show up in the project, it's most likely that Apache Tomcat needs to be linked to the project. Navigate to the project **Properties > Project Facets**. Under the **Runtimes** tab, select "Apache Tomcat v8.5" if it exists; otherwise, click "New" and add Tomcat. When finished, click "Apply and Close".
