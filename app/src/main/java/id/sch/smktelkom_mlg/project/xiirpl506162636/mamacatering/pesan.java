package id.sch.smktelkom_mlg.project.xiirpl506162636.mamacatering;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class pesan extends ActionBarActivity {

    String myJSON;
    private Button Update;
    private static final String URL = "https://tugasandroid.000webhostapp.com/update.php";
    private static final String TAG_RESULTS="result";
    private static final String TAG_ID = "id_pesan";
    private static final String TAG_USERNAME = "username";
    private static final String TAG_ID_Mak ="id_makanan";
    private static final String TAG_Catatan ="catatan";
    private static final String TAG_Proses ="proses";
    private static final String TAG_No_telp ="no_telp";



    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pesanan_list);
        Update = (Button) findViewById(R.id.btnUpdate);
        list = (ListView) findViewById(R.id.listView);
        personList = new ArrayList<HashMap<String,String>>();
        getData();
    }


    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                String id = c.getString(TAG_ID);
                String name = c.getString(TAG_USERNAME);
                String makanan = c.getString(TAG_ID_Mak);

                String proses = c.getString(TAG_Proses);

                String catatan = c.getString(TAG_Catatan);

                String no_telp = c.getString(TAG_No_telp);


                HashMap<String,String> persons = new HashMap<String,String>();

                persons.put(TAG_ID,id);
                persons.put(TAG_USERNAME,name);
                persons.put(TAG_ID_Mak,makanan);
                persons.put(TAG_Catatan,catatan);
                persons.put(TAG_Proses,proses);
                persons.put(TAG_No_telp,no_telp);

                personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                    pesan.this, personList, R.layout.list_item,
                    new String[]{TAG_ID,TAG_USERNAME,TAG_ID_Mak,TAG_Catatan,TAG_Proses,TAG_No_telp},
                    new int[]{R.id.id_pesan, R.id.username, R.id.id_makanan,R.id.catatan,R.id.proses,R.id.no_telp}
            );

            list.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String>{

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("https://tugasandroid.000webhostapp.com/get_data.php");

                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}