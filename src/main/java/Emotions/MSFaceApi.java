package Emotions;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Kamil on 20.04.2018.
 */
public class MSFaceApi {

    private static final String uriBase = "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect";
    private HttpClient httpClient;
    private HttpPost request;

    public MSFaceApi() {

        httpClient = new DefaultHttpClient();

        URIBuilder builder = null;
        try {
            builder = new URIBuilder(uriBase);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        // Request parameters. All of them are optional.
        builder.setParameter("returnFaceId", "true");
        builder.setParameter("returnFaceLandmarks", "false");
        builder.setParameter("returnFaceAttributes", "age,gender,headPose,smile,facialHair,glasses,emotion,hair,makeup,occlusion,accessories,blur,exposure,noise");

        // Prepare the URI for the REST API call.
        URI uri = null;
        try {
            uri = builder.build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        request = new HttpPost(uri);
        // Request headers.
        request.setHeader("Content-Type", "application/octet-stream");
        request.setHeader("Ocp-Apim-Subscription-Key", "de966db0a82641319a9c0079dfbdf983");
    }

    public void detect() throws IOException {
        File file = new File("./test.jpg");
        FileEntity reqEntity = new FileEntity(file, ContentType.APPLICATION_OCTET_STREAM);

        request.setEntity(reqEntity);
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();

        if (entity != null)
        {
            // Format and display the JSON response.
            System.out.println("REST Response:\n");

            String jsonString = EntityUtils.toString(entity).trim();
            if (jsonString.charAt(0) == '[') {
                JSONArray jsonArray = new JSONArray(jsonString);
                System.out.println(jsonArray.toString(2));
            }
            else if (jsonString.charAt(0) == '{') {
                JSONObject jsonObject = new JSONObject(jsonString);
                System.out.println(jsonObject.toString(2));
            } else {
                System.out.println(jsonString);
            }
        }
    }
}
