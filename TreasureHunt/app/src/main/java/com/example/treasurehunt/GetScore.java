package com.example.treasurehunt;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class GetScore extends AsyncTask<String , Void , String> {

    public AsyncResponse score = null;

    private final Context context;

    public GetScore(Context context) {
        this.context = context;
    }


    @Override
    protected String doInBackground(String... strings) {

        String score_url = "https://hunttreasure.000webhostapp.com/getscore.php";

        try {
            URL url = new URL(score_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            Log.i("logtag" , "before o/p stream");
            OutputStream outputStream = httpURLConnection.getOutputStream();
            Log.i("logtag" , "after o/p stream");
//                OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(DemoData.loggedInPlayer.username,"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line="";
            while((line = bufferedReader.readLine())!= null) {
                result += line;
                Log.i("line" , line);
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute(){
//        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
//        Toast.makeText(context ,result ,Toast.LENGTH_SHORT).show();
        Log.e("get score",result);
        score.processFinish(result);

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
