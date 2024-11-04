package com.kindsonthegenius.inventoryms_springboot_api.mailing;

import com.kindsonthegenius.inventoryms_springboot_api.models.User;
import org.springframework.web.util.UriComponentsBuilder;

public class AccountVerificationEmailContext extends AbstractEmailContext {

    private String token;

    @Override
    public <T> void init(T context) {
        User user = (User) context;
        put("firstName", user.getFirstName());
        setTemplateLocation("mailing/email-verification");
        setSubject("Completer Your Registration");
        setFrom("no-reply@kttpro.com");
        setTo(user.getEmail());
    }

    public void setToken(String token){
        this.token = token;
        put("token", token);
    }

    public void buildVerificationUrl(final String baseURL, final String token){
        final String url = UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/register/verify").queryParam("token", token).toUriString();
        put("verificationURL", url);
    }

}
