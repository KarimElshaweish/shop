package com.example.root.shop;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import java.util.HashMap;

/**
 * Created by root on 1/20/18.
 */

public class Background_Task extends AsyncTask<String,Void,String> {
    Context context;
    String Json;
    View view;
    JSONObject jsonObject;
    JSONArray jsonArray;
    public Background_Task(Context context) {
        this.context = context;
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context,result, Toast.LENGTH_SHORT).show();
    }

    public Background_Task(Context context, String json) {
        this.context = context;
        Json = json;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
        Json="http://192.168.1.10/get_user_info.php";
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... voids) {
        //remember the is ip must change
        String cheap_url="http://192.168.1.10/Insert.php";
        String json_url="http://192.168.1.10/json_get_data.php";
        String Like_url="http://192.168.1.5/like_insert.php";
        String method=voids[0];
        if(method.equals("insert")){
            String Fname=voids[1],
                    SName=voids[2],
                    City=voids[3],
                    State=voids[4],
                    Street=voids[5],
                    post=voids[6],
                    altPhone=voids[7],
                    phone=voids[8],
                    email=voids[9];
            try {
                URL url=new URL(cheap_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data= URLEncoder.encode("FristName","UTF-8")+"="+URLEncoder.
                        encode(Fname,"UTF-8")+"&"+
                        URLEncoder.encode("SecandName","UTF-8")+" = "+URLEncoder.
                        encode(SName,"UTF-8")+"&"+
                        URLEncoder.encode("City","UTF-8")+"="+URLEncoder.
                        encode(City,"UTF-8")+"&"+
                        URLEncoder.encode("State","UTF-8")+"="+URLEncoder.
                        encode(State,"UTF-8")+"&"+
                        URLEncoder.encode("Street","UTF-8")+"="+URLEncoder.
                        encode(Street,"UTF-8")+"&"+
                        URLEncoder.encode("Post","UTF-8")+"="+URLEncoder.
                        encode(post,"UTF-8")+"&"+
                        URLEncoder.encode("altPhone","UTF-8")+"="+URLEncoder.
                        encode(altPhone,"UTF-8")+"&"+
                        URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.
                        encode(phone,"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8")+"="+URLEncoder.
                        encode(email,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                inputStream.close();
                return "success";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(method.equals("insertLike")){
            String item=voids[1],
                    Email=voids[2],
                    cat=voids[3];
            try {
                URL url=new URL(Like_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data= URLEncoder.encode("email","UTF-8")+"="+URLEncoder.
                        encode(Email,"UTF-8")+"&"+
                        URLEncoder.encode("item","UTF-8")+"="+URLEncoder.
                        encode(item,"UTF-8")+"&"+
                        URLEncoder.encode("table","UTF-8")+"="+URLEncoder.encode("user_likes","UTF-8")+"&"+
                        URLEncoder.encode("cat","UTF-8")+"="+URLEncoder.encode(cat,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                inputStream.close();
                return "success";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (method.equals("userjson")){
            try {
                URL url=new URL(json_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                while ((Json=bufferedReader.readLine())!=null){
                        stringBuilder.append(Json+"\n");

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
