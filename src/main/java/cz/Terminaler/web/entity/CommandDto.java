package cz.Terminaler.web.entity;

import lombok.Builder;
import lombok.Data;

/**
 * reprezentace objektu v databázi
 * s použitím lomboku
 *
 * @author Jana Čižiková
 */

@Data  //vytvoří POJO objekt
@Builder
public class CommandDto {
    private long id;
    private String command;
    private String params;

}
