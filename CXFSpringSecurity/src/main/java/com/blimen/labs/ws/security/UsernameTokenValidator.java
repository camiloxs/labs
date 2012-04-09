package com.blimen.labs.ws.security;

import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.handler.RequestData;
import org.apache.ws.security.message.token.UsernameToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class UsernameTokenValidator
        extends org.apache.ws.security.validate.UsernameTokenValidator {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  
  private AuthenticationManager authenticationManager;

  public UsernameTokenValidator(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  protected void verifyPlaintextPassword(UsernameToken usernameToken,
          RequestData data) throws WSSecurityException {
    String user = usernameToken.getName();
    String password = usernameToken.getPassword();
    
    logger.debug("Verficando contraseña usuario: [{}]", user);
    Authentication authentication =
            new UsernamePasswordAuthenticationToken(user, password);
    authentication = authenticationManager.authenticate(authentication);
    if (!authentication.isAuthenticated()) {
      logger.debug(" !usuario no autenticado");
      throw new WSSecurityException(
              WSSecurityException.FAILED_AUTHENTICATION);
    }

    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
