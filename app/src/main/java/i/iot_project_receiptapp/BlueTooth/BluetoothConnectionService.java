package i.iot_project_receiptapp.BlueTooth;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by tsaiyunyun on 2017/6/2.
 */

public class BluetoothConnectionService {
    private static final String TAG = "BluetoothConnectionServ";
    private static final String MY_APP = "ReceiPet";
    private static final UUID MY_UUID = UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66");
    ProgressDialog mProgressDialog;
    private final BluetoothAdapter mBluetoothAdapter;
    Context mContext;

    private AcceptThread mInsecureAcceptThread;
    private ConnectThread mConnectThread;
    private BluetoothDevice mmDevice;
    private UUID deviceUUID;

    public BluetoothConnectionService(Context context){
        mContext = context;
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    private class AcceptThread extends Thread{
        //the local server socket
        private final BluetoothServerSocket mServerSocket;
        public AcceptThread(){
            BluetoothServerSocket tmp = null;
            //create a new listening server socket
            try {
                tmp = mBluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord(MY_APP, MY_UUID);
                Log.d(TAG,"AcceptThread: setting up Server using "+MY_UUID);
            }
            catch(IOException e){

            }
            mServerSocket = tmp;
        }

        public void run(){
            Log.d(TAG,"RUN: AcceptThread running");
            BluetoothSocket socket = null;
            try {
                //this is a blocking call and will only return on a succeccful connection or an exception
                Log.d(TAG, "RUN: RFCOM server socket start......");
                socket =  mServerSocket.accept();
                Log.d(TAG, "RUN: RFCOM server socket accepted connettion......");
            }
            catch(IOException e){
                Log.e(TAG,"Accept：IOException:"+e.getMessage());
            }
            if(socket != null){
                //connected(socket,mmDevice);
            }
            Log.i(TAG,"END , mAcceptThread");
        }

        public void cancel(){
            Log.d(TAG,"cancel: Canceling AcceptThread");
            try{
                mServerSocket.close();
            }catch(IOException e){
                Log.e(TAG,"cancel: Close of AcceptThread ServerSocket failed. "+e.getMessage());
            }
        }

    }

    private class ConnectThread extends Thread{
        private  BluetoothSocket mSocket;

        public ConnectThread(BluetoothDevice device,UUID uuid){
            Log.d(TAG,"ConnectThread: started.");
            mmDevice = device;
            deviceUUID = uuid;
        }
        public void run(){
            BluetoothSocket tmp = null;
            Log.i(TAG,"RUN mConnectThread ");

            try{
                Log.d(TAG,"ConnectThread: Trying to create InsecureRFcommSocket using UUID");
                tmp = mmDevice.createRfcommSocketToServiceRecord(deviceUUID);
            }catch(IOException e){
                Log.e(TAG,"ConnectThread: Could not create InsecureRFcommSocket"+e.getMessage());
            }
            mSocket = tmp;
            mBluetoothAdapter.cancelDiscovery();//avoid slow down

            try {
                mSocket.connect();
                Log.d(TAG,"RUN: ConnectThread connected");
            }catch(IOException e){
                //close the socket
                try{
                    mSocket.close();
                    Log.d("TAG","RUN: Closed Socket");
                }catch(IOException e1) {
                    Log.e(TAG,"mConnectThread: run: Unable to close connection in socket"+e1.getMessage());
                }
                Log.d(TAG,"RUN ConnectThread: could not connect to UUID: "+ MY_UUID);
            }
            //connected(mSocket,mmDevice);
        }

        public void cancel(){
            Log.d(TAG,"cancel: Closing Client Socket");
            try{
                mSocket.close();
            }catch(IOException e){
                Log.e(TAG,"cancel: Close() of mSocket in ConnectThread failed. "+e.getMessage());
            }
        }
    }

    public synchronized  void start(){
        Log.d(TAG,"start");

        if(mConnectThread!=null){
            mConnectThread.cancel();
            mConnectThread = null;
        }
        if(mInsecureAcceptThread==null){
            mInsecureAcceptThread = new AcceptThread();
            mInsecureAcceptThread.start();
        }
    }

    public void startClient(BluetoothDevice Device,UUID uuid){
        Log.d(TAG,"startClient: Started");

        mProgressDialog = ProgressDialog.show(mContext,"Connecting bluetooth"," Please wait.... ",true);
        mConnectThread = new ConnectThread(Device,uuid);
        mConnectThread.start();
    }

    //finally the connected thread which is responsible for maintaining the bluetooth connection
    //sending the data and receiving the data through input output stream
    public class ConnectedThread extends Thread{
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket){
            Log.d(TAG,"Connected Thread start");
            mmSocket = socket;
            InputStream TMPin = null;
            OutputStream TMPout = null;
            //dismiss thr prgress dialog when connection is established
            mProgressDialog.dismiss();

            try{
                TMPin = mmSocket.getInputStream();
                TMPout = mmSocket.getOutputStream();
            }
            catch(IOException e){
                e.printStackTrace();;
            }
            mmInStream = TMPin;
            mmOutStream = TMPout;
        }

        public void run(){
            byte[] buffer = new byte[1024];
            int bytes;
            while(true){
                try {
                    bytes = mmInStream.read(buffer);
                    String incomingMessage = new String(buffer, 0, bytes);
                    Log.d(TAG, "InputStream: " + incomingMessage);
                }
                catch(IOException e){
                    e.printStackTrace();
                    break;
                }
            }
        }


    }
}
