package cz.Terminaler.web.entity;

import javax.validation.constraints.Size;

/**
 * reprezentace formulářového objektu
 * bez použití lomboku
 *
 * @author Jana Čižiková
 */

public class CommandFormPto {

    private String command;

    @Size(min = 1, message = "Zadejte parametr.")
    private String parameter;


    public CommandFormPto() {
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String newValue) {
        command = newValue;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String newValue) {
        parameter = newValue;
    }
}
