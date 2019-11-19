package cz.Terminaler.web.entity;

import java.util.stream.Stream;

/**
 * přehled linuxových příkazů s metodou na získání hodnoty příkazu
 *
 * @author Jana Čižiková
 */

public enum CommandsLinux {

    CREATEFOLDER("mkdir"),
    DELETEFOLDER("rmdir"),
    PING("ping -c 3");

    private String value;
    
    public String getCommandValue(){
        return value;
    }

    CommandsLinux(String value){
        this.value = value;
    }

    public static Stream<CommandsLinux> stream() {
        return Stream.of(CommandsLinux.values());
    }
}
