package cz.Terminaler.web.serviceDb;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.zaxxer.hikari.HikariDataSource;
import cz.Terminaler.web.entity.CommandDto;

/**
 * frameworky pro práci s SQL příkazy
 *
 * @author Jana Čižiková
 */

@Configuration
@ComponentScan
public class DbSpringFramework {


    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate;
        jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    @Bean
    public RowMapper<CommandDto> commandDtoRowMapper(){

        RowMapper<CommandDto> commandDtoRowMapper = (rs, rowNum) ->
                CommandDto.builder()
                        .id(rs.getLong("id"))
                        .command(rs.getString("command"))
                        .params(rs.getString("params"))
                        .build();

        return commandDtoRowMapper;
    }

}
