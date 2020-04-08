package com.example.myapplicationbroadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class LowLevelReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.v("brad", "low action ---> " + intent.getAction());

        Bundle resultExtras = getResultExtras( true );
        String content = resultExtras.getCharSequence( "content" ).toString();
        Log.v( "brad","content ---> " + content  );


    }
}
