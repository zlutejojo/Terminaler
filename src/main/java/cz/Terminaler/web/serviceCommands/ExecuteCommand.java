package cz.Terminaler.web.serviceCommands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.springframework.context.annotation.*;

/**
 * provedení linuxového příkazu
 * zprcováno dle: https://crunchify.com/how-to-run-windowsmac-commands-in-java-and-return-the-text-result
 *
 * @author Jana Čižiková
 */

@Configuration
@ComponentScan
public class ExecuteCommand {

        public printOutput getStreamWrapper(InputStream is, String type) {
            return new printOutput(is, type);
        }

        public String runCommand(String command, String params){
            Runtime runtime = Runtime.getRuntime();
            ExecuteCommand executeCommand = new ExecuteCommand();
            printOutput errorReported, outputMessage;

            String commandWithParams = command + " " + params;

            try {
                Process process = runtime.exec(commandWithParams);
                errorReported = executeCommand.getStreamWrapper(process.getErrorStream(), "ERROR");
                outputMessage = executeCommand.getStreamWrapper(process.getInputStream(), "OUTPUT");
                errorReported.start();
                outputMessage.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return commandWithParams;
        }


        private class printOutput extends Thread {
            InputStream is = null;

            printOutput(InputStream is, String type) {
                this.is = is;
            }

            public void run() {
                String s = null;
                try {
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(is));
                    while ((s = br.readLine()) != null) {
                        System.out.println(s);
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
