package Utils;

import org.apache.log4j.*;

public class Log {
    //if your client wants log then do otherwise depends on you
    //you dont need sout in it you need log

    public  static  Logger log= Logger.getLogger(Log.class.getName());

    // When the test starts, I should print the logs
    // When my tests stops, I should print the log
    // If I want to print any message in between, I should print the logs

    public static void startTestCase(String testCaseName){
    log.info("******************");// any time you want to print something you need to write this
    log.info("******************");
    log.info("******************");
    log.info("************" +testCaseName+ "************ ");

    }
    public static void endTestCase(String testCaseName){
        log.info("###########################");
        log.info("###########################");
        log.info("############"+ testCaseName +"###############");
    }
    public static void info(String msg){
        log.info(msg);
    }
    public static void warning(String warning){
        log.info(warning);
    }

    // MAVEN LIFE CYCLE: This consists of several phase
    // clean: Removes all the files generated by the previous build
    // validate: Checks the project is correct and all the necessary information is available
    // compile: compiles the source code of the project
    // test: runs the tests for the project
    // package: packages the compiled code into a distributable format, such as JAR or WAR files
    // verify: runs and checks on results of integration tests to ensure quality criteria is met
    // install: installs the packages into local repository (important interview Q -> ans is .M2
    // site: generates documentation for the project (for developers)
    // deploy: copies the final package to remote repository for sharing with other developers or projects
}
