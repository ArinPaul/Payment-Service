package com.whizdm.payment_service.utils.APICaller;

import java.io.IOException;
import java.util.HashMap;

public interface APICallerService {
     String getAPICall(String url) throws IOException,InterruptedException;
     String postAPICall(String url, String id, String message) throws IOException,InterruptedException;
}
