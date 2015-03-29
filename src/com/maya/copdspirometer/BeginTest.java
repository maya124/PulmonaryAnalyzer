
package com.maya.copdspirometer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Button;
import android.content.Context;
import android.widget.ListView;
import java.text.DecimalFormat;
import android.widget.Chronometer;
import android.content.Context;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.view.View.OnClickListener;
import android.bluetooth.*;
import android.widget.TextView;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.os.CountDownTimer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.widget.Button;
import android.widget.Toast;


import com.jjoe64.graphview.*;
import com.jjoe64.graphview.GraphView.*;
import com.jjoe64.graphview.GraphViewStyle;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;

public class BeginTest extends Activity {

	TextView mDataField;
	TextView mTextField;
	int receivedData;
	double sumVolume=0;
	GraphViewSeries exampleSeries;
	GraphViewSeries volumeSeries;
	GraphViewSeries boldLine; 
	GraphView graphView;
	double count = 0;
	double pef = 0;
	double fvc =0; 
	double finalValue = 0;
	double fev1 = 0;
	double FVC_expected, FEV1FVC_expected, FEV1_expected;
    @Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.begintesting);
	//	Bundle extras = getIntent().getExtras();
	//	if(extras!=null)
	//	{
	//	    FEV1_expected = extras.getDouble("FEV1");
	//		((TextView) findViewById(R.id.fev1)).setText("Expected FEV1: " + FEV1_expected);
	//	    FVC_expected = extras.getDouble("FVC");
	//	    FEV1FVC_expected = extras.getDouble("FEV1FVC");
	//	} 
		FEV1_expected=SelfQuiz.FEV1;
		FVC_expected=SelfQuiz.FVC;
		FEV1FVC_expected=SelfQuiz.FEV1FVC;

		System.out.println(FVC_expected);
		
		GraphViewSeriesStyle x = new GraphViewSeriesStyle(Color.YELLOW, 4);
		GraphViewSeriesStyle y = new GraphViewSeriesStyle(Color.WHITE, 0);
		GraphViewSeriesStyle z = new GraphViewSeriesStyle(Color.GREEN, 4);


		exampleSeries = new GraphViewSeries("exampleSeries", x, new GraphViewData[] {
			    new GraphViewData(0,0)
			});
		boldLine = new GraphViewSeries("boldLine", y, new GraphViewData[] {
			    new GraphViewData(0,120),
			    new GraphViewData(10, 120)
			});
		volumeSeries = new GraphViewSeries("volumeSeries", z, new GraphViewData[] {
			    new GraphViewData(0,0)
			});

		graphView  = new LineGraphView(this, "Flow Rate vs. Time");
		
/*
		GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
			    new GraphViewData(1, 2.0d)
			    , new GraphViewData(2, 1.5d)
			    , new GraphViewData(3, 2.5d)
			    , new GraphViewData(4, 1.0d)
			});
			 
			GraphView graphView = new LineGraphView(
			    this // context
			    , "Flow Rate vs. Time");
				graphView.getGraphViewStyle().setGridColor(Color.WHITE);
				graphView.getGraphViewStyle().setLegendBorder(10);
				graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.WHITE);
				graphView.getGraphViewStyle().setVerticalLabelsColor(Color.WHITE);
				graphView.getGraphViewStyle().setNumHorizontalLabels(10);
				graphView.getGraphViewStyle().setNumVerticalLabels(10);
			
			graphView.addSeries(exampleSeries); // data
			 
			LinearLayout layout = (LinearLayout) findViewById(R.id.layout1);
			layout.addView(graphView);
			*/
//---This section makes sure that the Bluetooth is on on the device
			// Initializes Bluetooth adapter.
			final BluetoothManager bluetoothManager =
			        (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
			BluetoothAdapter mBluetoothAdapter = bluetoothManager.getAdapter();
			// Ensures Bluetooth is available on the device and it is enabled. If not,
			// displays a dialog requesting user permission to enable Bluetooth.
			 final int REQUEST_ENABLE_BT = 1;
			if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
			    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
			}
//---END SECTION
    
			final Context context = this;
	Button button = (Button) findViewById(R.id.scanButton);
	button.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			System.out.println("scanbuttonclicked");
		    Intent intent = new Intent(context, DeviceScan.class);
          startActivity(intent);   
		}
	});
	
/*	Button button2 = (Button) findViewById(R.id.beginTestButton);
//	final TextView text = (TextView) findViewById(R.id.counter);
	 mTextField = (TextView) findViewById(R.id.countdown);
     mDataField = (TextView) findViewById(R.id.incomingData);

	button2.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			System.out.println("beginTestButtonClicked");
			//text.setVisibility(View.VISIBLE);
			 new CountDownTimer(5000, 1000) {
				    public void onTick(long millisUntilFinished) {
				            mTextField.setText("Breathe into Spirometer in: " + millisUntilFinished / 1000);
				        }

				        public void onFinish() {
				            mTextField.setText("Breathe Now");
				        }
			 }.start();
			 
		} */
	//});
    
	
		    
    }
/*    private void beginActualTest() {
		 System.out.println("Beginning Actual Testing");
/		//10 second timer
		 new CountDownTimer(10000, 1000) {
			    public void onTick(long millisUntilFinished) {
			    	double timeLeft = millisUntilFinished/1000;
			        mTextField.setText("Breathe Now: " + timeLeft);
			        double currTime=timeLeft;
			        }

			        public void onFinish() {
			            mTextField.setText("Test Finished");
			        }
		 }.start();
		//every 1/2 second, get mDataField text as an integer value from 0 to 255
		//send data to graphview function as (time, integer value)
    }
 */   
    private void displaygraph(int[] dataArray, double track)
    {
    	System.out.println("Began DisplayGraph"+ track);
    	if(track>20) {onPause(); endProgram();}
    	/*GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
			    new GraphViewData(track-0.1, dataArray[0])
			    , new GraphViewData(track-0.09, dataArray[1])
			    , new GraphViewData(track-0.08, dataArray[2])
			    , new GraphViewData(track-0.07, dataArray[3])
			    , new GraphViewData(track-0.06, dataArray[4])
			    , new GraphViewData(track-0.05, dataArray[5])
			    , new GraphViewData(track-0.04, dataArray[6])
			    , new GraphViewData(track-0.03, dataArray[7])
			    , new GraphViewData(track-0.02, dataArray[8])
			    , new GraphViewData(track-0.01, dataArray[9])
			}); */
	    	  exampleSeries.appendData(new GraphViewData(track-0.38, dataArray[0]),false, 1000);
		      exampleSeries.appendData(new GraphViewData(track-0.36, dataArray[1]),false, 1000);
		      exampleSeries.appendData(new GraphViewData(track-0.34, dataArray[2]),false, 1000);
		      exampleSeries.appendData(new GraphViewData(track-0.32, dataArray[3]),false, 1000);
		      exampleSeries.appendData(new GraphViewData(track-0.30, dataArray[4]),false, 1000);
		      exampleSeries.appendData(new GraphViewData(track-0.28, dataArray[5]),false, 1000);
		      exampleSeries.appendData(new GraphViewData(track-0.26, dataArray[6]),false, 1000);
		      exampleSeries.appendData(new GraphViewData(track-0.24, dataArray[7]),false, 1000);
		      exampleSeries.appendData(new GraphViewData(track-0.22, dataArray[8]),false, 1000);
		      exampleSeries.appendData(new GraphViewData(track-0.20, dataArray[9]),false, 1000);
    	      exampleSeries.appendData(new GraphViewData(track-0.18, dataArray[10]),false, 1000);
    	      exampleSeries.appendData(new GraphViewData(track-0.16, dataArray[11]),false, 1000);
    	      exampleSeries.appendData(new GraphViewData(track-0.14, dataArray[12]),false, 1000);
    	      exampleSeries.appendData(new GraphViewData(track-0.12, dataArray[13]),false, 1000);
    	      exampleSeries.appendData(new GraphViewData(track-0.10, dataArray[14]),false, 1000);
    	      exampleSeries.appendData(new GraphViewData(track-0.08, dataArray[15]),false, 1000);
    	      exampleSeries.appendData(new GraphViewData(track-0.06, dataArray[16]),false, 1000);
    	      exampleSeries.appendData(new GraphViewData(track-0.04, dataArray[17]),false, 1000);
    	      exampleSeries.appendData(new GraphViewData(track-0.02, dataArray[18]),false, 1000);
    	      exampleSeries.appendData(new GraphViewData(track, dataArray[19]),false, 1000);
   
    	 
    	      for(int a = 0; a<20; a++)
    	      {
    	    	  sumVolume = sumVolume + (dataArray[a]*0.02);
    	      }
    	      volumeSeries.appendData(new GraphViewData(track, sumVolume),false, 1000);


    			graphView.getGraphViewStyle().setGridColor(Color.WHITE);
    			graphView.getGraphViewStyle().setLegendBorder(10);
    			graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.WHITE);
    			graphView.getGraphViewStyle().setVerticalLabelsColor(Color.WHITE);
    			graphView.getGraphViewStyle().setNumHorizontalLabels(11);
    			graphView.getGraphViewStyle().setNumVerticalLabels(6);
    			graphView.setViewPort(0.01, 10);
    			graphView.setManualYAxisBounds(255, 0);
    			graphView.setCustomLabelFormatter(new CustomLabelFormatter() {
    				  @Override
    				  public String formatLabel(double value, boolean isValueX) {
    				    if (isValueX) {
    				      return ""+((int) value);
    				    }
    				    else return ""+(((int) ((value)/25.5))); // let graphview generate Y-axis label for us
    				  }
    				});
    			//graphView.setScrollable(true);
    		LinearLayout layout = (LinearLayout) findViewById(R.id.layout1);
    		graphView.removeAllSeries();
    		layout.removeView(graphView);

    		//exampleSeries.addGraphView(graphView);
    		graphView.addSeries(exampleSeries); // data
    		graphView.addSeries(volumeSeries);
    	//	graphView.addSeries(boldLine);
			layout.addView(graphView);
			
    }
    
    private void endProgram()
    {
    	String shortValue = new DecimalFormat("#.##").format(finalValue); 
    	String shortfev1value = new DecimalFormat("#.##").format(fev1);
    	String shortfvcvalue = new DecimalFormat("#.##").format(fvc);
    	String shortfvcexpec = new DecimalFormat("#.##").format(FVC_expected);
    	String shortfev1expec = new DecimalFormat("#.##").format(FEV1_expected);
    	String peffor = new DecimalFormat("#.##").format(pef);

       ((TextView) findViewById(R.id.fev1)).setText("Expected FEV1: " + shortfev1expec + " L\nCalculated FEV1: " + shortfev1value + " L");     //System.out.printf("%1.2f", 3.14159D)
       ((TextView) findViewById(R.id.fvc)).setText("Expected FVC: " + shortfvcexpec + " L\nCalculated FVC: " + shortfvcvalue + " L");
        if(finalValue>=0.8) ((TextView) findViewById(R.id.textView3)).setText("Results are Normal!  \nRatio: " + shortValue + "         \nPEF: " + peffor + " L/s");
        else if(finalValue>=0.50 && finalValue<0.8) ((TextView) findViewById(R.id.textView3)).setText("Mild Obstruction  \nRatio: " + shortValue + "         \nPEF: " + peffor + " L/s");
        else if(finalValue>=0.30 && finalValue<0.5) ((TextView) findViewById(R.id.textView3)).setText("Moderate Obstruction \nRatio: " + shortValue + "         \nPEF:" + peffor + " L/s");
        else if(finalValue<0.3) ((TextView) findViewById(R.id.textView3)).setText("Severe Obstruction  \nRatio: " + shortValue + "          \nPEF: " + peffor + " L/s");
        else ((TextView) findViewById(R.id.textView3)).setText("Results Inconclusive  \nRatio: " + shortValue + "          \nPEF: " + peffor + " L/s");

        int age = SelfQuiz.age;
        double height = SelfQuiz.height;
        int smokeYears = SelfQuiz.smokeYears;
        int pollution = SelfQuiz.pollution;
        int gender = SelfQuiz.gender;
        double pef_predicted=0;
        //Calculate Peak Expiratory Flow Rate (predicted)
        if(age<18) pef_predicted = (((height-100)*5) + 100);
        else if (gender==1) pef_predicted = Math.pow(Math.E,((0.376*Math.log(age))-(0.012*age)-(58.8/height)+5.63));
        else pef_predicted = Math.pow(Math.E, ((0.544*Math.log(age))-(0.0151*age)-(74.7/height)+5.48));
        System.out.println("PEF VALUE:" + pef_predicted);
        
        
        //NO DIAGNOSIS OF ASTHMA OR COPD
        if(finalValue>=0.8 && fvc>(0.8*FVC_expected)) ((TextView) findViewById(R.id.diseases)).setText("COPD Severity (Predicted): 0%\nAsthma Severity (Predicted): 0%");
        //RESTRICTIVE LUNG DISEASE
        else if(finalValue>=0.8 && fvc<(0.8*FVC_expected)) ((TextView) findViewById(R.id.diseases)).setText("Restrictive Lung Disease Severity (Predicted): " + new DecimalFormat("##.#").format((1-finalValue)*100) + "%");
        //ASTHMA DIAGNOSIS - BASED ON PEF
        else if((pef*60)<(0.7*pef_predicted)) ((TextView) findViewById(R.id.diseases)).setText("Asthma Severity (Predicted): " + new DecimalFormat("##.#").format((1-finalValue)*100) + "%");
        //DIAGNOSIS OF EMPHYSEMA
        else if(age>=60 && smokeYears>=5) ((TextView) findViewById(R.id.diseases)).setText("Emphysema Severity (Predicted): " + new DecimalFormat("##.#").format((1-finalValue)*100) + "%");
        //DIAGNOSIS OF CHRONIC BRONCHITIS
        else if(age>=40 && pollution==1) ((TextView) findViewById(R.id.diseases)).setText("Chronic Bronchitis Severity (Predicted): " + new DecimalFormat("##.#").format((1-finalValue)*100) + "%");
        //DIAGNOSIS OF PURE COPD
        else ((TextView) findViewById(R.id.diseases)).setText("COPD Severity (Predicted): " + new DecimalFormat("##.#").format((1-finalValue)*100) + "%");
    }
    
    static int integraltracker=0;
    int endCount=0;
    int packetCount = 0;
   
    private final BroadcastReceiver mGattUpdateReceiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
        	System.out.println("REACHED 2nd BROADCAST RECEIVER");
            if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                String in = intent.getStringExtra(BluetoothLeService.INT_DATA);
                System.out.println(in);
                String[] splitArray = in.split(" ");
                int [] dataArray = new int[20];
                for(int i=0; i<20; i++)
                {
                   dataArray[i]=Integer.parseInt(splitArray[i], 16);
                   if(dataArray[i]<=12) dataArray[i]=0;
                }
                for(int j=0; j<19; j++)
                {
                	//if(dataArray[j]>125) //might be 140 --> check with logcat
                		{
                			if((dataArray[j]/25.5)>pef) pef = (dataArray[j]/25.5);
                			fvc=fvc + (Math.abs((((dataArray[j]/25.5)+(dataArray[j+1]/25.5))/2)*0.02));
                		}
                }
                fvc = fvc + (((dataArray[19]/25.5)/2) * 0.02);
                System.out.println("PEF = " + pef);
                boolean allEnd=false;
                for(int j=0; j<19; j++)
                {
                	if(dataArray[j]<125 || dataArray[j]>138) allEnd=true;
                }
                if(allEnd=true) endCount++;
                else endCount=0;
                
              
                
                //if(dataArray[10]>140)
                	integraltracker++;
                if(integraltracker<=3) fev1=fvc;
                for(int element: dataArray) {System.out.println(element);}
                System.out.println("Integral Sum: " + fvc);
                System.out.println("FEV1: "+ fev1);
                System.out.println("integraltracker:" + integraltracker);
                finalValue = (double)(fev1)/((double) fvc);
                System.out.println("Ratio:" + finalValue);
                count = count+0.4;
                packetCount++;
                System.out.println("PacketCount: " + packetCount);
                if(packetCount==25){
                	onPause(); endProgram();
                }
                displaygraph(dataArray, count);
            	//displayData(intent.getStringExtra(BluetoothLeService.EXTRA_DATA));
            }
        }
    };
    
    private void displayData(String data) {
        if (data != null) {
			mDataField.setText(data);
        }
    }
    
    private static IntentFilter makeGattUpdate2IntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }
    
    protected void onResume() {
        super.onResume();
        registerReceiver(mGattUpdateReceiver2, makeGattUpdate2IntentFilter());
 //       if (mBluetoothLeService != null) {
 //           final boolean result = mBluetoothLeService.connect(mDeviceAddress);
 //       }
    }
 
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mGattUpdateReceiver2);
    }
    
   
 
}