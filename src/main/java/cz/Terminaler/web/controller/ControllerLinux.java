package cz.Terminaler.web.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import cz.Terminaler.web.entity.*;
import cz.Terminaler.web.serviceCommands.*;


/**
 * obsluha webu s výběrem linuxových příkazů
 *
 * @author Jana Čižiková
 */

@Controller
public class ControllerLinux {

    @Autowired
    private EnumCommandsLinuxService enumCommandsLinuxService;

    @Autowired
    private ExecuteCommand executeCommand;


    List<String> commandLinuxItemsLst;
    List<String> commandLinuxValueLst;
    Map<String, String> commandsValueLinkedByEnumItems;

    /**
     * zobrazení formuláře s výběrem linux příkazů
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView displayIndex(){
        ModelAndView modelAndViewIndex = new ModelAndView("index");
        CommandFormPto commandFormEmpty = new CommandFormPto();
        commandLinuxItemsLst = enumCommandsLinuxService.collectCommandsLinuxItem();

        modelAndViewIndex.addObject("commandForm", commandFormEmpty);
        modelAndViewIndex.addObject("commandLinuxLst",commandLinuxItemsLst);

        return modelAndViewIndex;
    }

    /**
     * vykoná uživatelem vybraný příkaz a zobrazí potvrzení o provedení
     * validuje, zda uživatel zadal do parameter alespoň jeden znak
     * validate, if paramater has one character at least
     */

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView displayResult(@Valid @ModelAttribute("commandForm") CommandFormPto commandForm, Errors validErrors){

        if (validErrors.hasErrors()) {
            ModelAndView modelAndViewIndex = new ModelAndView("index");
            return modelAndViewIndex;
        }

        ModelAndView modelAndViewResult = new ModelAndView("result");

        commandLinuxValueLst = enumCommandsLinuxService.collectCommandsLinuxValue();
        commandsValueLinkedByEnumItems = new HashMap<>();

        for (int i = 0; i < commandLinuxItemsLst.size(); i++) {
            commandsValueLinkedByEnumItems.put(commandLinuxItemsLst.get(i), commandLinuxValueLst.get(i));
        }

        String commandSelecetedByUser = commandsValueLinkedByEnumItems.get(commandForm.getCommand());
        String parameterWritenByUser = commandForm.getParameter();

        executeCommand.runCommand(commandSelecetedByUser,parameterWritenByUser);

        return modelAndViewResult;
    }

}