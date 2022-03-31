package com.whizdm.payment_service.utils.APICaller;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

//Singleton Class
public class APICaller implements APICallerService{
  private static APICaller call = null;
  private APICaller(){}
    public static APICaller getInstance(){
      if (call == null) return new APICaller();
      return call;
    }
  public String getAPICall(String url) throws IOException,InterruptedException{
      var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
      var client = HttpClient.newBuilder().build();
      var response = client.send(request, HttpResponse.BodyHandlers.ofString());
      return response.body();
  }
  public String postAPICall(String url,String id, String message) throws IOException,InterruptedException{

      var objectMapper = new ObjectMapper();
      var values = new HashMap<String,String>(){{
          put(id,message);
      }};
      String requestBody = objectMapper.writeValueAsString(values);
      var client = HttpClient.newBuilder().build();
      var request = HttpRequest.newBuilder()
              .uri(URI.create(url))
              .POST(HttpRequest.BodyPublishers.ofString(requestBody))
              .build();
      var response = client.send(request,
              HttpResponse.BodyHandlers.ofString());
      return response.body();

  }
    public String postAPICallComm(String url,String req_type,String user_id, String loan_id,String bank_acc,String body) throws IOException,InterruptedException{

        var objectMapper = new ObjectMapper();
        var details = new HashMap<String,String>(){{
            put("loanId",loan_id);
            put("reason",body);
            put("userId",user_id);
            put("bankAccount",bank_acc);
        }};
        var requestDetails = new HashMap<String,String>(){{
            put("requestType",req_type);
        }};
        var values = new HashMap<String,HashMap<String,String>>(){{
            put("requestType",requestDetails);
            put("details",details);
        }};
        String requestBody = objectMapper.writeValueAsString(values);
        var client = HttpClient.newBuilder().build();
        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        var response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        return response.body();

    }
}
