package dk.cachet.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION_CODES;
import androidx.annotation.RequiresApi;
import io.flutter.plugin.common.EventChannel.EventSink;
import java.util.HashMap;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel.Result;
/**
 * Receives events from @NotificationListener
 * */

public class NotificationReceiver extends BroadcastReceiver {

  private EventSink eventSink;
   private MethodChannel methodChannel;

  public NotificationReceiver(EventSink eventSink, MethodChannel methodChannel) {
    this.eventSink = eventSink;
    this.methodChannel = methodChannel;
  }

  @RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR2)
  @Override
  public void onReceive(Context context, Intent intent) {
    /// Unpack intent contents
    String packageName = intent.getStringExtra(NotificationListener.NOTIFICATION_PACKAGE_NAME);
    String title = intent.getStringExtra(NotificationListener.NOTIFICATION_TITLE);
    String message = intent.getStringExtra(NotificationListener.NOTIFICATION_MESSAGE);

    /// Send data back via the Event Sink
    HashMap<String, Object> data = new HashMap<>();
    data.put("packageName", packageName);
    data.put("title", title);
    data.put("message", message);

   


    //eventSink.success(data);
              //throw new RuntimeException(title);
              if (methodChannel != null) {
                   
                   try{
      methodChannel.invokeMethod("onNotificationReceived", packageName + " - " + title + ": " + message);
      }catch(Exception e) {
                
                throw RuntimeException("NU a mers-------------");
                }

  }
}
