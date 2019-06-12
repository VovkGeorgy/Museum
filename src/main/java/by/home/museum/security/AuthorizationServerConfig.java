package by.home.museum.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * Authorization configuration class
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final int TEN_DAYS = 60 * 60 * 24 * 10;
    private static final int ONE_DAY = 60 * 60 * 24;
    private static final int THIRTY_DAYS = 60 * 60 * 24 * 30;

    private final TokenStore tokenStore;
    private final JwtAccessTokenConverter jwtTokenEnhancer;
    private final UserApprovalHandler userApprovalHandler;
    private final AuthenticationManager authenticationManager;
    private final MuseumUserDetailsService museumUserDetailsService;

    @Value("${security.oauthServer.realm}")
    private String realm;

    @Value("${security.oauthServer.clientId}")
    private String clientId;

    @Value("${security.oauthServer.clientSecret}")
    private String clientSecret;

    /**
     * Method create inMemory client details for authorization
     *
     * @param clients - clients
     * @throws Exception exp
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientId)
                .secret(clientSecret)
                .authorizedGrantTypes("password", "refresh_token")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .scopes("read", "write", "trust")
                .accessTokenValiditySeconds(ONE_DAY)
                .refreshTokenValiditySeconds(THIRTY_DAYS);
    }

    /**
     * Configure the properties and enhanced functionality of the Authorization Server endpoints.
     *
     * @param endpoints AuthorizationServerEndpointsConfigurer
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore)
                .tokenEnhancer(jwtTokenEnhancer)
                .userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager)
                .userDetailsService(museumUserDetailsService);
    }

    /**
     * Config authorization server realm
     *
     * @param oauthServer AuthorizationServerSecurityConfigurer
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.realm(realm);
    }
}
