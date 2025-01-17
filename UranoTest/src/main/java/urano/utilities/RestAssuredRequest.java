package urano.utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredRequest {
    public static void deleteCompany(String token, String companyId) {
        RestAssured.baseURI = "https://mesule-staging0.westeurope.cloudapp.azure.com/api/company/del";
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .delete("/" + companyId);

        int statusCode = response.getStatusCode();
        if (statusCode == 200 || statusCode == 204) {
            System.out.println("Elemento eliminato con successo. ID: " + companyId);
        } else {
            System.err.println("Errore nell'eliminazione. Stato: " + statusCode);
            System.err.println("Messaggio: " + response.getBody().asString());
        }
    }

}
