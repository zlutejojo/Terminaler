package cz.Terminaler.web.serviceDb;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import cz.Terminaler.web.entity.CommandDto;

/**
 * metody pro práci s databází
 *
 * @author Jana Čižiková
 */

@Repository
@Configuration
@ComponentScan
public class CommandDbServiceImpl implements CommandDbService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<CommandDto> commandDtoRowMapper;


    /**
     * vloží řádek do databáze dle zadaného objektu, id příkazu je automaticky generováno
     * @param newCommand nový příkaz
     */
    @Override
    public void create(CommandDto newCommand) {
        jdbcTemplate.update("INSERT INTO commands (command, params) VALUES(?, ?)",
                newCommand.getCommand(),
                newCommand.getParams()
        );
    }

    /**
     * vrátí seznam všech příkazů v databázi
     * @return seznam příkazů
     */
    @Override
    public List<CommandDto> readAll() {
        return jdbcTemplate.query(
        "SELECT id, command, params FROM commands",commandDtoRowMapper
        );
    }

    /**
     * vrátí příkaz z databáze dle zadého id
     * @param idCommand id hledaného příkazu
     * @return příkaz s daným id
     */
    @Override
    public CommandDto read(long idCommand) {
        return jdbcTemplate.queryForObject(
                "SELECT id, command, params FROM commands WHERE id = ?",
                commandDtoRowMapper,
                idCommand
        );
    }

    /**
     * smaže příkaz z databáze dle zadého id
     * @param idCommand id hledaného příkazu
     */
    @Override
    public void delete(long idCommand) {
        jdbcTemplate.update("DELETE FROM commands WHERE id = ?", idCommand);
    }

    /**
     * provedení DDL operace
     * @param DDLOperation DDL příkaz (př. vytvoření tabulky)
     */
    public void executeDDLOperation(String DDLOperation) {
        jdbcTemplate.execute(DDLOperation);
    }

}
