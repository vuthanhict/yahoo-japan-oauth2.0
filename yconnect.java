public class YConnectTokenResponse {
    private String accessToken;

    private String tokenType;

    private Integer expiresIn;

    private String idToken;

    private String refreshToken;
}

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.util.ObjectUtils;

public YConnectTokenResponse yconnectToken(String pCode, String pState) throws BluuBusinessException {
        if (ObjectUtils.isEmpty(request)
                || StringUtils.isEmpty(pCode)) {
            return Optional.empty();
        }
        String url = "https://auth.login.yahoo.co.jp/yconnect/v2/token";
        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");

        StringBuilder builder = new StringBuilder();
        builder.append("grant_type=authorization_code");
        builder.append("&");
        builder.append("client_id={your_client_id}");
        builder.append("&");
        builder.append("client_secret={your_secret}");
        builder.append("&");
        builder.append("redirect_uri={your_redirect_uri}"); // URLENCODE
        builder.append("&");
        builder.append("code=").append(pCode);

        try {
            httpPost.setEntity(new StringEntity(builder.toString(),  "UTF8"));

            HttpResponse httpResponse = httpClient.execute(httpPost);
            String json = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            logger.debug("[YConnect] Body content: {}", json);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                YConnectTokenResponse response = JsonMapper.convertJsonToObject(json, YConnectTokenResponse.class);
                return response;
            }
        } catch (Exception e) {
            // write error message
        }
        return null;
    }
