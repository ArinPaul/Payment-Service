package com.whizdm.payment_service.utils.APICaller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//Singleton Class
public class APICaller implements APICallerService{
  private static APICaller call = null;
  private APICaller(){}
    public static APICaller getInstance(){
      if (call == null) return new APICaller();
      return call;
    }
  public String APICall(String url) throws IOException,InterruptedException{
      var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
      var client = HttpClient.newBuilder().build();
      var response = client.send(request, HttpResponse.BodyHandlers.ofString());
      return response.body();
  }
}
