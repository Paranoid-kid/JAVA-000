package io.github.kimmking.netty.server;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

public class OKHttpClient {
    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        }
    }

    public static void main(String[] args) throws IOException {
        OKHttpClient example = new OKHttpClient();
        String response = example.run("http://127.0.0.1:8801");
        System.out.println(response);
    }
}
