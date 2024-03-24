package com.fiap.postechpagamentos.infra.criptografia;

import jakarta.persistence.AttributeConverter;
import org.springframework.stereotype.Component;

@Component
public class ConverterDados implements AttributeConverter<String,String> {

	private Criptografia criptografia;

	ConverterDados(Criptografia criptografia) {
		this.criptografia = criptografia;
	}

	@Override
	public String convertToDatabaseColumn(String valor) {
		if(valor != null) {
			return this.criptografia.criptografar(valor);
		}
		return null;
	}

	@Override
	public String convertToEntityAttribute(String valor) {
		if (valor != null) {
			return this.criptografia.descriptografar(valor);
		}
		return null;
	}
}