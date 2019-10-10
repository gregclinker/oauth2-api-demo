package com.essexboy;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.junit.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AppTest {

    @Test
    public void test1() throws Exception {
        assertTrue(true);

        final InputStream resourceAsStream = getClass().getResourceAsStream("/essexboy-2a97c4f3d0b1.json");

        GoogleCredentials credentials = ServiceAccountCredentials.fromStream(resourceAsStream);
        final AccessToken accessToken = credentials.getAccessToken();
        System.out.println(accessToken.getExpirationTime());
    }

    private static final List SCOPE = Arrays.asList("https://www.googleapis.com/auth/drive");

    @Test
    public void test2() throws Exception {

        HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
        JsonFactory JSON_FACTORY = new JacksonFactory();

        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(HTTP_TRANSPORT)
                .setJsonFactory(JSON_FACTORY)
                .setServiceAccountId("api-simulator@essexboy.iam.gserviceaccount.com")
                .setServiceAccountScopes(SCOPE)
                .setServiceAccountPrivateKeyFromP12File(getClass().getResourceAsStream("/essexboy-d299f7ecaaae.p12"))
                .build();

        credential.refreshToken();
        final String accessToken = credential.getAccessToken();
        System.out.println(accessToken);
        assertNotNull(accessToken);
    }
}
