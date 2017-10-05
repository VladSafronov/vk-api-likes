package like.statistic;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static like.statistic.Main.client_id;

public class UserMain {

    private static String scope = "messages";
    private static String redirect_uri = "http://oauth.vk.com/blank.html";
    private static String display = "popup";
    private static String response_type = "token";
    private static String email = "artem.safronov87@mail.ru";//тут должен быть прописан email
    private static String pass = "220898Vlad";

    public static void main(String[] args) throws IOException, URISyntaxException {
        HttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();
// Делаем первый запрос
        HttpPost post = new HttpPost("http://oauth.vk.com/authorize?" +
                "client_id="+client_id+
                "&scope="+scope+
                "&redirect_uri="+redirect_uri+
                "&display="+display+
                "&response_type="+response_type);
        HttpResponse response;
        response = httpClient.execute(post);
        post.abort();
//Получаем редирект
        String HeaderLocation = response.getFirstHeader("location").getValue();
        URI RedirectUri = new URI(HeaderLocation);
//Для запроса авторизации необходимо два параметра полученных в первом запросе
//ip_h и to_h
        String ip_h= RedirectUri.getQuery().split("&")[2].split("=")[1];
        String to_h=RedirectUri.getQuery().split("&")[4].split("=")[1];
// Делаем запрос авторизации
        post = new HttpPost("https://login.vk.com/?act=login&soft=1"+
                "&q=1"+
                "&ip_h="+ip_h+
                "&from_host=oauth.vk.com"+
                "&to="+to_h+
                "&expire=0"+
                "&email="+email+
                "&pass="+pass);
        response = httpClient.execute(post);
        post.abort();
// Получили редирект на подтверждение требований приложения
        HeaderLocation = response.getFirstHeader("location").getValue();
        post = new HttpPost(HeaderLocation);
// Проходим по нему
        response = httpClient.execute(post);
        post.abort();
// Теперь последний редирект на получение токена
        HeaderLocation = response.getFirstHeader("location").getValue();
// Проходим по нему
        post = new HttpPost(HeaderLocation);
        response = httpClient.execute(post);
        post.abort();
// Теперь в след редиректе необходимый токен
        HeaderLocation = response.getFirstHeader("location").getValue();
// Просто спарсим его сплитами
        String access_token = HeaderLocation.split("#")[1].split("&")[0].split("=")[1];
        System.out.println(access_token);
    }
}

