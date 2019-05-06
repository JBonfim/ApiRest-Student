package br.com.jabes.apistudent.adapter;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * @author Jabes
 *Modificando a quantidade de itens retonado ao realizar uma requisição com varias paginações
 *foi definido para retornar 5 itens por requisição
 */
@Configuration
public class ApiStudentAdapter extends WebMvcConfigurerAdapter {

	@Override
	public void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> argumentResolvers) {
		PageableHandlerMethodArgumentResolver argumentResolver = new PageableHandlerMethodArgumentResolver();
		argumentResolver.setFallbackPageable(new PageRequest(0, 5));
		argumentResolvers.add(argumentResolver);
	}
}
