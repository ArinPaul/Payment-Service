package com.springrest.demo.utils.APICaller;

import java.io.IOException;

public interface APICallerService {
     String APICall(String url) throws IOException,InterruptedException;
}
