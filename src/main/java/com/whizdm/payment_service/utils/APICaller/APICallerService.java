package com.whizdm.payment_service.utils.APICaller;

import java.io.IOException;

public interface APICallerService {
     String APICall(String url) throws IOException,InterruptedException;
}
