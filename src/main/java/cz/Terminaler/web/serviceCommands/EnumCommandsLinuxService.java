package cz.Terminaler.web.serviceCommands;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import cz.Terminaler.web.entity.CommandsLinux;

/**
 * metody pro práci s přehledem linuxových příkazů (enum CommandsLinux)
 *
 * @author Jana Cizikova
 */

@Configuration
@ComponentScan
public class EnumCommandsLinuxService {

    /**
     * vrátí kolekci linuxových příkazů (hodnota položky enumu)
     * @return kolekce linuxových příkazů
     */
    public List<String> collectCommandsLinuxValue() {

        List<String> commandLst;

        return commandLst = CommandsLinux.stream()
                .map(CommandsLinux::getCommandValue)
                .collect(Collectors.toList());
    }

    /**
     * vrátí kolekci položek linuxových příkazů
     * @return kolekce položek linuxových příkazů
     */
    public List<String> collectCommandsLinuxItem()  {

        List<String> commandsLinuxLst;

        return commandsLinuxLst = CommandsLinux.stream()
                .map(CommandsLinux::name)
                .collect(Collectors.toList());
    }
}
