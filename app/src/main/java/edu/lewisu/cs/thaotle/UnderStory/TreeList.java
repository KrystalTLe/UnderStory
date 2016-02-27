//Add images to make a list, images on the left and names of trees on the right
package edu.lewisu.cs.thaotle.UnderStory;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TreeList extends Activity {
    //Declare variable
    JSONObject jsonObject;
    JSONArray jsonArray;
    ListView listView;
    ListAdapter listAdapter;
    ProgressDialog progressDialog;
    ArrayList<HashMap<String, String>> arrayList;
    static String TREE_NAME = "Tree_Name";
    static String SCI_NAME = "Scientific_Name";
    static String STORY = "Story";
    static String IMAGE = "Image";
    static String LATITUDES="Latitudes";
    static String LONGITUDES= "Longitudes";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        new DownloadJson().execute();
    }

    //download JsonAsyncTask
    private class DownloadJson extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //create dialog
            progressDialog = new ProgressDialog(TreeList.this);
            //set message
            progressDialog.setMessage("Loading...");
            progressDialog.setIndeterminate(false);
            //display dialog
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            //Create array
            arrayList = new ArrayList<HashMap<String, String>>();
            //Retrieve Json Object from URL
            jsonObject = JSONFunctions.getJSONfromURL("http://cs.lewisu.edu/trees/index.php");
            
            try {
                //Locate JSon array
                jsonArray = jsonObject.getJSONArray("alltree");
                for (int i = 0; i < jsonArray.length(); i++) {
                    HashMap<String, String> tree = new HashMap<String, String>();
                    jsonObject = jsonArray.getJSONObject(i);
                    //Retrieve data
                    tree.put("Tree_Name", jsonObject.getString("Tree_Name"));
                    tree.put("Scientific_Name", jsonObject.getString("Scientific_Name"));
                    tree.put("Image", jsonObject.getString("Image"));
                    tree.put("Story", jsonObject.getString("Story"));
                    tree.put("Latitudes", jsonObject.getString("Latitudes"));
                    tree.put("Longitudes", jsonObject.getString("Longitudes"));
                    // Set the JSON Objects into the array
                    arrayList.add(tree);
                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            listView = (ListView) findViewById(R.id.pictures);
            // Pass the results into ListViewAdapter.java
            listAdapter = new ListAdapter(TreeList.this, arrayList);
            // Set the adapter to the ListView
            listView.setAdapter(listAdapter);
            // Close the progressdialog
            progressDialog.dismiss();
        }
    }


}

