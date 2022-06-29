package io.github.andreycostaalves.Criptosenhas.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.andreycostaalves.Criptosenhas.data.DetalheUsuarioData;
import io.github.andreycostaalves.Criptosenhas.model.UsuarioModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**Responsavel por Autenticar o usuario e geral o token jwt.*/

public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TOKEN_EXPIRATION = 600_000;
    //senha apenas para desenvolvimento, ela deveria estar em um arquivo protegido.
    public static final String TOKEN_SENHA = "c4f2c893-42e2-4f20-a25a-b626298841df";

    private final AuthenticationManager authenticationManager;

    public JWTAutenticarFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /** Metodo que executa nossa autenticação (Ctrl + o = attemptAuthentication) */

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            UsuarioModel usuario = new ObjectMapper().readValue(request.getInputStream(), UsuarioModel.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usuario.getLogin(),
                    usuario.getPassword(),
                    new ArrayList<>()
            ));
        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar Usuário:", e);
        }
    }

    /**Metodo que traca quando receber SUCESSO na autenticação.( Ctrl + o = successfulAuthentication)*/
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        DetalheUsuarioData usuarioData = (DetalheUsuarioData) authResult.getPrincipal();

        //Gerando token
        String token = JWT.create()
                .withSubject(usuarioData.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION)) //Soma a data atual com o tempo de expiração do token.
                .sign(Algorithm.HMAC512(TOKEN_SENHA));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
