package course.labs.asynctasklab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;

public class DownloaderTaskFragment extends Fragment {

	private DownloadFinishedListener mCallback;
	private Context mContext;
	
	@SuppressWarnings ("unused")
	private static final String TAG = "Lab-Threads";

    /*
    This is the host. THREADIND RULEs
     1) ASynchTask instance must be created on the UI thread.
     2) execute(Params...) must be invoked on the UI thread.
     3) Do not call onPreExecute(), onPostExecute(Result), doInBackground(Params...), onProgressUpdate(Progress...) manually.

     */
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Preserve across reconfigurations
		setRetainInstance(true);
		
		// TODO1 : Create new DownloaderTask that "downloads" data
        DownloaderTask downloaderTask = new DownloaderTask(this);

		// TODO 2: Retrieve arguments from DownloaderTaskFragment
        // todo unpk bundle
        
        
		// TODO3: Start the DownloaderTask
		downloaderTask.execute(MainActivity.sRawTextFeedIds);



	}

	// Assign current hosting Activity to mCallback
	// Store application context for use by downloadTweets()
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		mContext = activity.getApplicationContext(); 

		// Make sure that the hosting activity has implemented
		// the correct callback interface.
		try {
			mCallback = (DownloadFinishedListener) activity;  //handle to MainActivity
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement DownloadFinishedListener");
		}
	}

	// Null out mCallback
	@Override
	public void onDetach() {
		super.onDetach();
		mCallback = null;
	}


	// TODO: Implement an AsyncTask subclass called DownLoaderTask.
	// This class must use the downloadTweets method (currently commented
	// out). Ultimately, it must also pass newly available data back to 
	// the hosting Activity using the DownloadFinishedListener interface.

	private class DownloaderTask extends AsyncTask<List<Integer> /*in*/, Void/*progress*/, String[]/*out*/> {
		private DownloaderTaskFragment parent;
        private String[] feeds;

		public DownloaderTask(DownloaderTaskFragment downloaderTaskFragment) {
			parent=downloaderTaskFragment;

		}


        // See Rules -- DO NOT INVOKE these manuall
        //onPreExecute(), invoked on the UI thread before the task is executed. This step is normally used to setup the task, for instance by showing a progress bar in the user interface.
		@Override/*in -- NOTE matches params type */
		protected String[] doInBackground(List<Integer>... params) {
			System.out.println( "in 99 doInBkground -- about to load tweets");
			feeds = parent.downloadTweets(params[0].toArray(new Integer[params[0].size()]));
			System.out.println( "DONE 99 doInBkground -- a"+feeds[0].length());

			return feeds;
		}

		@Override/*out*/
		protected void onPostExecute(String[] result) {

			//invoked on the UI thread after the background computation finishes.
			//Typically updates a UI component ,eg myView.setText(result);
			System.out.println( "99 in onPostExecute --  loaded tweets,mcallbak: "+mCallback);

            mCallback.notifyDataRefreshed(feeds);

		}

	}


		// Simulates downloading Twitter data from the network
         private String[] downloadTweets(Integer resourceIDS[]) {
			final int simulatedDelay = 2000;
			String[] feeds = new String[resourceIDS.length];
			try {
				for (int idx = 0; idx < resourceIDS.length; idx++) {
					InputStream inputStream;
					BufferedReader in;
					try {
						// Pretend downloading takes a long time
						Thread.sleep(simulatedDelay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					inputStream = mContext.getResources().openRawResource(
							resourceIDS[idx]);
					in = new BufferedReader(new InputStreamReader(inputStream));

					String readLine;
					StringBuffer buf = new StringBuffer();

					while ((readLine = in.readLine()) != null) {
						buf.append(readLine);
					}

					feeds[idx] = buf.toString();

					if (null != in) {
						in.close();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			return feeds;
		}
    
    
    
    
    

}