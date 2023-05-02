package LinkedWebsite_app;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.stream.JsonReader;
import com.google.auth.Credentials;
import com.google.api.services.blogger.Blogger;
import com.google.api.services.blogger.BloggerScopes;
import com.google.api.services.blogger.model.Post;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.util.Collections;

public class WebsitePoster {
  private static final String APPLICATION_NAME = "Your Application Name";
  private static final String BLOG_ID = "Your Blog ID";
  private static final String API_KEY = "Your API Key";

  public static void main(String[] args) throws IOException {
	HttpTransport transport = new UrlFetchTransport();   
    GsonFactory jsonFactory = new GsonFactory();
    GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("D:\\Users\\elfik\\eclipse-workspace\\LinkedWebsite\\resources\\json\\famous-cogency-385315-55b839f6605b.json"))
    	    .createScoped(Collections.singleton(BloggerScopes.BLOGGER));

    Blogger blogger = new Blogger.Builder(transport, jsonFactory, (HttpRequestInitializer) credentials)
    	    .setApplicationName(APPLICATION_NAME)
    	    .build();


    byte[] fileContent = Files.readAllBytes(Paths.get("/path/to/your/textfile.txt"));
    ByteArrayContent content = new ByteArrayContent("text/plain", fileContent);

    Post post = new Post();
    post.setTitle("Your post title");
    post.setContent("Your post content. <a href=\"http://www.example.com/yourfile.txt\">Link to your text file</a>");

    blogger.posts().insert(BLOG_ID, post).execute();
  }
}