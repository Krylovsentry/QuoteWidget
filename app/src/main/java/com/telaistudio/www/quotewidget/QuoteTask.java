package com.telaistudio.www.quotewidget;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Anton on 24.12.2016.
 */

public class QuoteTask extends AsyncTask<Void, Void, Quote> {

    private String urlString = "http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=%s";
    private String locale;
    private Quote quote;
    private Context context;

    public QuoteTask(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {
        locale = context.getResources().getString(R.string.locale);
    }

    @Override
    protected Quote doInBackground(Void... voids) {

        try {
            URL url = new URL(String.format(urlString, locale));
            //Create connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            //Check if connection is ok
            if (isConnectionOK(connection)) {
                InputStream inputStream = connection.getInputStream();
                StringBuilder strBuilder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                //Bracers first
                while ((line = reader.readLine()) != null) {
                    strBuilder.append(line);
                }

                JSONObject jsonObject = new JSONObject(strBuilder.toString());
                quote = extractQuote(jsonObject);
            } else {
                quote = new Quote(
                        context.getResources().getString(R.string.initial_author),
                        context.getResources().getString(R.string.initial_quote)
                );
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return quote;
    }

    @Override
    protected void onPostExecute(Quote quote) {
        super.onPostExecute(quote);
    }

    private boolean isConnectionOK(HttpURLConnection connection) throws IOException {
        return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    private Quote extractQuote(JSONObject jsonObject) throws JSONException {
        String author = jsonObject.getString("quoteAuthor");
        String quote = jsonObject.getString("quoteText");

        if (TextUtils.isEmpty(author)) {
            return new Quote(quote);
        } else {
            return new Quote(author, quote);
        }
    }
}

