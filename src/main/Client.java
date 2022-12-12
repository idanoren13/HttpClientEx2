package main;

import com.google.gson.Gson;
import main.models.PostJSONObject;
import main.models.ResponseJSONObject;
import main.models.PutJSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;

public class Client {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();
        String BASE_URL = "http://localhost:8989";
        String response1 = "";
        ResponseJSONObject response2 = new ResponseJSONObject("");
        ResponseJSONObject response3 = new ResponseJSONObject("");

        // 1. GET
        Calendar calendar = Calendar.getInstance();
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        String finalUrl =
                HttpUrl
                        .parse(BASE_URL + "/test_get_method")
                        .newBuilder()
                        .addQueryParameter("hour", hour)
                        .addQueryParameter("minute", minute)
                        .build()
                        .toString();
        Request request = new Request.Builder()
                .url(finalUrl)
                .build();
        Call call1 = client.newCall(request);
        try {
            response1 = Objects.requireNonNull(call1.execute().body()).string();
            System.out.println("Response: " + response1);
        } catch (IOException e) {
            System.out.println("Oopsy... something went wrong..." + e.getMessage());
        }
        // 2. POST
        finalUrl =
                HttpUrl
                        .parse(BASE_URL + "/test_post_method")
                        .newBuilder()
                        .build()
                        .toString();
        Request request2 = new Request.Builder()
                .url(finalUrl)
                .post(RequestBody.create(new PostJSONObject(Integer.parseInt(hour), Integer.parseInt(minute), response1).toString(), MediaType.parse("application/json")))
                .build();
        Call call2 = client.newCall(request2);
        try {
            response2 = gson.fromJson(Objects.requireNonNull(call2.execute().body()).string(), ResponseJSONObject.class);
            System.out.println("Response: " + response2.getMessage());
        } catch (IOException e) {
            System.out.println("Oopsy... something went wrong..." + e.getMessage());
        }

        // 3. PUT
        finalUrl =
                HttpUrl
                        .parse(BASE_URL + "/test_put_method")
                        .newBuilder()
                        .addQueryParameter("id", response2.getMessage())
                        .build()
                        .toString();
        Request request3 = new Request.Builder()
                .url(finalUrl)
                .put(RequestBody.create(new PutJSONObject(
                        (Integer.parseInt(hour) + 21) % 24,
                        (Integer.parseInt(minute) + 13) % 60)
                        .toString(), MediaType.parse("application/json")))
                .build();
        Call call3 = client.newCall(request3);
        try {
            response3 = gson.fromJson(Objects.requireNonNull(call3.execute().body()).string(), ResponseJSONObject.class);
            System.out.println("Response: " + response3.getMessage());
        } catch (IOException e) {
            System.out.println("Oopsy... something went wrong..." + e.getMessage());
        }

        // 4. DELETE
        finalUrl =
                HttpUrl
                        .parse(BASE_URL + "/test_delete_method")
                        .newBuilder()
                        .addQueryParameter("id", response3.getMessage())
                        .build()
                        .toString();
        Request request4 = new Request.Builder()
                .url(finalUrl)
                .delete()
                .build();
        Call call4 = client.newCall(request4);
    }
}
