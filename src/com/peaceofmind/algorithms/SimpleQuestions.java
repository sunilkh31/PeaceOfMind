/**
 * 
 */
package com.peaceofmind.algorithms;

import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.spi.CurrencyNameProvider;

/**
 * @author StarLord
 *
 */
public class SimpleQuestions {

    public static void main(String[] args) throws Exception {
        SimpleQuestions ques = new SimpleQuestions();
        ExecutorService service = new ScheduledThreadPoolExecutor(200);
        int x = 200;
        while(x>=0) {
            service.submit((Runnable) () -> {
                Date time = new Date();
                System.out.println(ques.getConversionRate(1, "USD", "INR") + " " + (new Date().getTime()- time.getTime()) + " ");
            });
            x--;
        }
        service.shutdown();
        while(!service.isShutdown());
    }

    private float getConversionRate(int amount, String from, String to) {
        try {
            URL url = new URL(String.format("https://www.google.com/finance/converter?a=%d&from=%s&to=%s",amount,from,to));
            URLConnection con = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String input;
            while((input=reader.readLine()) != null) {
                if(input.contains("currency_converter_result")) {
                    return Float.parseFloat(input.split("<span class=bld>")[1].split("</span>")[0].split(" ")[0]);
                }
            }
            throw new RuntimeException("Cannot find the conversion rate");
        } catch(IOException ex) {
            throw new RuntimeException(("exception"));
        }
    }

    /* Houses with Gold and non two consecutive houses can be chosen, maximize the output */
    public static int maxGoldCollection(List<Integer> goldInHouse) {
        int[] gold = new int[goldInHouse.size() + 2];

        for (int i = 2; i <= goldInHouse.size(); i++) {
            gold[i] = Math.max(gold[i - 2] + goldInHouse.get(i - 2), gold[i - 1]);
        }
        return gold[goldInHouse.size() + 1];
    }
}
