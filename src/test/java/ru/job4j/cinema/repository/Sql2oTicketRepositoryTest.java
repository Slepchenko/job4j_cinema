package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Sql2oTicketRepositoryTest {

    private static Sql2oTicketRepository sql2oTicketRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oTicketRepositoryTest.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        sql2oTicketRepository = new Sql2oTicketRepository(sql2o);
    }

    @AfterEach
    public void delete() {
        var tickets = sql2oTicketRepository.findAll();
        for (var t : tickets) {
            sql2oTicketRepository.delete(t.getId());
        }
    }

    @Test
    public void whenSaveThenGetSame() {
        Ticket ticket = new Ticket(0, 16, 1, 2, 56);
        Optional<Ticket> saveTicket = sql2oTicketRepository.save(ticket);
        Optional<Ticket> findTicket = sql2oTicketRepository.findById(saveTicket.get().getId());
        assertThat(findTicket).usingRecursiveComparison().isEqualTo(saveTicket);
    }


    @Test
    public void whenDontSaveThenNothingFound() {
        assertThat(sql2oTicketRepository.findAll()).isEqualTo(emptyList());
    }

}