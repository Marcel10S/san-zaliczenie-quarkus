package org.zaliczenie;

import org.zaliczenie.client.NBPClient;
import org.zaliczenie.model.Rate;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import java.util.Arrays;

@Path("/api")
public class CurrencyController {
    @Inject
    NBPClient nbpClient;

    static String[] allowedTables = {"a", "b", "c"};

    @GET
    @Path("/currency/{table}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCurrencyTables(@PathParam("table") String table) throws Exception {
        if (Arrays.stream(CurrencyController.allowedTables).noneMatch(element -> element.equals(table))) {
            return "{\"error\": \"invalid_table\", \"message\": \"Podana tabela nie istnieje: " + table + "\"}";
        }

        Rate[] rates = nbpClient.getRates(table);
        return "{\"rates\": " + rates + "}";
    }
}