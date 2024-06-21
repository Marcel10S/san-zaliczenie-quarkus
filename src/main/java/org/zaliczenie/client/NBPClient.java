package org.zaliczenie.client;

import org.zaliczenie.model.Rate;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import javax.enterprise.context.ApplicationScoped;

public class NBPClient {
    static String NBPRatesRoute = "/api/exchangerates/tables/{table}";

    public Rate[] getRates(String table) throws JsonbException {
        try (Jsonb jsonb = JsonbBuilder.create()) {
            Response response = RestAssured.given()
                    .when().get(NBPClient.NBPRatesRoute, table)
                    .then().statusCode(200)
                    .extract().response();

            String jsonResponse = response.getBody().asString();
            Rate[] rates = jsonb.fromJson(jsonResponse, Rate[].class);
            return rates;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred", e);
        }
    }
}