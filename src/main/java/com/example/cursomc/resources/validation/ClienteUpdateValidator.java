package com.example.cursomc.resources.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.dto.ClienteDTO;

import com.example.cursomc.repository.ClienteRepository;
import com.example.cursomc.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	@Autowired
	ClienteRepository clieRepo;
	@Autowired
	private HttpServletRequest httpRequest;
	
	@Override
	public void initialize(ClienteUpdate ann) {
		
	}
	@Override
	public boolean isValid(ClienteDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list =new ArrayList<>();
		
		Cliente cliente = clieRepo.findByEmail(value.getEmail());
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) this.httpRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Integer id = Integer.parseInt(map.get("id"));
		
		
		if(cliente != null && !cliente.getId().equals(id)) {
			list.add(new FieldMessage("Email encontrado","Email já cadastrado!"));
		}
		
		for(FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFildName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
	
	
	
}