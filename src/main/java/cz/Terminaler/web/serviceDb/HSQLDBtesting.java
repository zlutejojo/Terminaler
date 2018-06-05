package cz.Terminaler.web.serviceDb;

import javax.sql.DataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariDataSource;
import cz.Terminaler.web.entity.*;
import lombok.SneakyThrows;

/**
 *  připojení k HSQLDB
 *  slouží pro testování
 */

@Configuration
@ComponentScan
public class HSQLDBtesting {


    @Bean
    public DataSource dataSource() {

        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:hsqldb:mem:command");
        hikariDataSource.setUsername("ja");
        hikariDataSource.setPassword("tajne");

        return hikariDataSource;
    }


    @SneakyThrows
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(HSQLDBtesting.class);

        CommandDbServiceImpl commandServiceDb = applicationContext.getBean(CommandDbServiceImpl.class);

// TESTOVÁNÍ CommandDto (níže několik příkladů)

// VYTVOŘENÍ TABULKY
        String createTableCommand = "CREATE TABLE commands (id INTEGER IDENTITY PRIMARY KEY, command VARCHAR(60), params VARCHAR(100))";
        commandServiceDb.executeDDLOperation(createTableCommand);

// DVA TESTOVACÍ OBJEKTY
        CommandDto commandFirst = CommandDto.builder()
                .command("ping")
                .params("seznam.cz")
                .build();

        CommandDto commandSecond = CommandDto.builder()
                .command("ping")
                .params("google.com")
                .build();


// VLOŽENÍ OBJEKTU DO TABULKY
        commandServiceDb.create(commandFirst);
        commandServiceDb.create(commandSecond);

// ČTENÍ vŠECH VLOŽENÝCH OBJEKTŮ
        commandServiceDb.readAll().forEach(System.out::println);

// ČTENÍ VLOŽENÉHO OBJEKTU PODLE ID
        System.out.println(commandServiceDb.read(0).toString());

// SMAZÁNÍ OBJEKTU PODLE ID
        commandServiceDb.delete(0);
        commandServiceDb.readAll().forEach(System.out::println);
    }
}
