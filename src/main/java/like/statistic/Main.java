package like.statistic;


import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.ServiceClientCredentialsFlowResponse;
import com.vk.api.sdk.queries.likes.LikesGetListQuery;
import com.vk.api.sdk.queries.likes.LikesType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Component
public class Main {


    static int client_id = 6206738;
    static String client_secret = "DY0dOr0iEqRboC4G7O05";
    static String version = "5.68";
    static String grant_type = "client_credentials";

    public static void main(String[] args) throws IOException, URISyntaxException {

        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);

        ServiceClientCredentialsFlowResponse authResponse = null;
        try {
            authResponse = vk.oauth()
                    .serviceClientCredentialsFlow(client_id, client_secret)
                    .execute();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
//
//        ServiceActor actor = new ServiceActor(
//                client_id, client_secret, authResponse.getAccessToken());
//        LikesGetListQuery query = vk.likes().getList(ServiceActor, LikesType.POST).itemId();
//        vk.users().search(ServiceActor);

//        String URL = "https://oauth.vk.com/access_token";
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL)
//                .queryParam("client_id",client_id)
//                .queryParam("client_secret",client_secret)
//                .queryParam("grant_type",grant_type)
//                .queryParam("v",version);
//
//        URL = builder.build().toUri().toString();
//        AuthorizeResponse responseEntity = restTemplate.postForObject(URL,null,AuthorizeResponse.class);

  //      System.out.println(responseEntity);

    }
}
