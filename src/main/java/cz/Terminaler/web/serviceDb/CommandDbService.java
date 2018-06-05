package cz.Terminaler.web.serviceDb;

import java.util.List;
import cz.Terminaler.web.entity.CommandDto;

/**
 * předpis metod pro práci s databází
 *
 *  @author Jana Čižiková
 */

public interface CommandDbService {

    /**
     * vytvoří nový příkaz dle zadaného objektu
     * @param newCommand nový příkaz
     */
    void create(CommandDto newCommand);

    /**
     * vrátí seznam všech existujících příkazů
     * @return seznam příkazů
     */
    List<CommandDto> readAll();

    /**
     * vrátí příkaz dle zadého id
     * @param idCommand id hledaného příkazu
     * @return příkaz s daným id
     */
    CommandDto read(long idCommand);

    /**
     * smaže příkaz dle zadého id
     * @param idCommand id hledaného příkazu
     */
    void delete(long idCommand);
}
