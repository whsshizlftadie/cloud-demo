package com.whs.oj.auth.service.client_details;

import com.whs.oj.auth.api.OauthClientDetailsClient;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("clientServiceImpl")
public class ClientServiceImpl implements ClientDetailsService {

    @Autowired
    private OauthClientDetailsClient clientDetailsClient;

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {

        OauthClientDetails oauthClientDetails = clientDetailsClient.clientDetails(s);

        if(!Objects.isNull(oauthClientDetails)){
            BaseClientDetails clientDetails=new BaseClientDetails(
                    oauthClientDetails.getClientId(),
                    oauthClientDetails.getResourceIds(),
                    oauthClientDetails.getScope(),
                    oauthClientDetails.getAuthorizedGrantTypes(),
                    oauthClientDetails.getAuthorities(),
                    oauthClientDetails.getWebServerRedirectUri()
            );

            clientDetails.setAccessTokenValiditySeconds(oauthClientDetails.getAccessTokenValidity());
            clientDetails.setRefreshTokenValiditySeconds(oauthClientDetails.getRefreshTokenValidity());
            clientDetails.setClientSecret(oauthClientDetails.getClientSecret());
            return clientDetails;
        }else{
            throw new  NoSuchClientException("客户端不存在");
        }


    }


}
