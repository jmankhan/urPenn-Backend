package api;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;


public class Yelp {

    private static final String API_HOST = "api.yelp.com";
    private static final String SEARCH_PATH = "/v2/search";
    private static final String BUSINESS_PATH = "/v2/business";
    private static final int SEARCH_LIMIT = 3;


    private static final String consumerKey = "mE1KfEt8iY1-_0NOyh_lIA";
    private static final String consumerSecret = "8nvy7BCpkyOdgTwIyesb_VNc24c";
    private static final String token = "IKluprwi9bIYxJjIJ8zfdkSXDU4JU6x3";
    private static final String tokenSecret = "vzCfFekF7aXza2OuZm-zpMOoHaY";

    private static final String location = "Phildelphia, PA";
    private OAuthService service;
    private Token accessToken;
    
    public Yelp() {
    	this.service = new ServiceBuilder().provider(YelpAuth.class).apiKey(consumerKey).apiSecret(consumerSecret).build();
    	this.accessToken = new Token(token, tokenSecret);
    }
    
    public String searchForBusiness(String term) {
        OAuthRequest request = createOAuthRequest(SEARCH_PATH);
        request.addQuerystringParameter("term", term);
        request.addQuerystringParameter("location", location);
        request.addQuerystringParameter("limit", String.valueOf(SEARCH_LIMIT));
        return sendRequestAndGetResponse(request);
    }
    
    private String sendRequestAndGetResponse(OAuthRequest request) {
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }
    
    private OAuthRequest createOAuthRequest(String path) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://" + API_HOST + path);
        return request;
    }

}
