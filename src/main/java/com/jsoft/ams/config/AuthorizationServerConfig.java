package com.jsoft.ams.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.jsoft.ams.config.component.CustomClaimAccessTokenConverter;
import com.jsoft.ams.master.repository.OauthClientDetailsServiceImpl;
import com.jsoft.ams.user.service.UserService;

@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private UserService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationProperties applicationProperties;

    /*@Autowired
    private AuthTokenEnhancer authTokenEnhancer;*/

    @Autowired
    private CustomClaimAccessTokenConverter customClaimAccessTokenConverter;

    @Autowired
    private DataSource dataSource;


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
        configurer
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenServices(tokenServices())
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(new OauthClientDetailsServiceImpl(dataSource));
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(applicationProperties.getAuth().getKfName()),
                applicationProperties.getAuth().getKsPass().toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("demo"));
        converter.setAccessTokenConverter(customClaimAccessTokenConverter);
        return converter;
    }

    @Primary
    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        //tokenEnhancerChain.setTokenEnhancers(Arrays.asList(authTokenEnhancer, accessTokenConverter()));
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);

        defaultTokenServices.setReuseRefreshToken(false);
        defaultTokenServices.setAccessTokenValiditySeconds(applicationProperties.getAuth().getDefaultAccessTokenTimeout());
        defaultTokenServices.setRefreshTokenValiditySeconds(applicationProperties.getAuth().getDefaultRefreshTokenTimeout());
        defaultTokenServices.setClientDetailsService(new OauthClientDetailsServiceImpl(dataSource));

        defaultTokenServices.setAuthenticationManager(authentication -> {
            UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

            PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(
                    authentication.getName(), authentication.getCredentials(), userDetails.getAuthorities());
            token.setDetails(userDetails);
            return token;
        });

        return defaultTokenServices;
    }
}