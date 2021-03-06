package com.example.cursomc.resources.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.dto.ClienteDTONew;
import com.example.cursomc.enuns.TipoCliente;
import com.example.cursomc.repository.ClienteRepository;
import com.example.cursomc.resources.exception.FieldMessage;
import com.example.cursomc.resources.validation.util.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteDTONew> {
	@Autowired
	ClienteRepository clieRepo;
	@Override
	public void initialize(ClienteInsert ann) {
		
	}
	@Override
	public boolean isValid(ClienteDTONew value, ConstraintValidatorContext context) {
		List<FieldMessage> list =new ArrayList<>();
		
		if(value.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCpf(value.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "Cpf invalido"));
		}
		if(value.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCnpj(value.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "Cnpj invalido"));
		}
		
		Cliente cliente = clieRepo.findByEmail(value.getEmail());
		
		if(cliente != null) {
			list.add(new FieldMessage("Email encontrado","Email já cadastrado!"));
		}
		
		for(FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFildName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
	
	
	

}
