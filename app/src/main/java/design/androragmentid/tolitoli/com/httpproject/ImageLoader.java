package design.androragmentid.tolitoli.com.httpproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class ImageLoader {



    private static final String tag = ImageLoader.class.getName();
    // Creates Bitmap from InputStream and returns it
    public static Bitmap downloadImageAsBitmap(String url) {

        Bitmap bitmap = null;
        InputStream stream = null;
        byte[] byteArrayForBitmap = new byte[17*1024];
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inTempStorage =  byteArrayForBitmap;
        bmOptions.inSampleSize = 1;

        try {
            stream = getHttpConnection(url);
            if(stream != null) {
                bitmap = BitmapFactory.decodeStream(stream, null, bmOptions);
                stream.close();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return bitmap;
    }

    // Makes HttpURLConnection and returns InputStream
    private static InputStream getHttpConnection(String urlString)
            throws IOException {
        InputStream stream = null;
        if(TextUtils.isEmpty(urlString)) return stream;

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {

            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();
            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                stream = httpConnection.getInputStream();
            }
        } catch (Exception ex) {
            Log.e(tag, ex.getMessage(), ex);
        }
        return stream;
    }
    public static byte[] toBytesArray(Bitmap bitmap) {
        if(bitmap == null) return  new byte[]{};
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public static Bitmap toBitmap(byte[] bytes) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bitmap;
    }

    private static byte[] readStream(InputStream stream) throws IOException {
        // Copy content of the image to byte-array
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = stream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();
        byte[] temporaryImageInMemory = buffer.toByteArray();
        buffer.close();
        stream.close();
        return temporaryImageInMemory;
    }

    public static byte[] downloadImageAsByte(String url) {
        Bitmap bitmap = null;
        InputStream stream = null;


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        byte[] byteArrayForBitmap = new byte[17*1024];

        try {
            stream = getHttpConnection(url);
            if(stream != null) {
                byteArrayForBitmap = readStream(stream);
                stream.close();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return byteArrayForBitmap;
    }
}
