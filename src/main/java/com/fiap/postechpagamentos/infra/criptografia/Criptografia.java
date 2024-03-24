package com.fiap.postechpagamentos.infra.criptografia;

import com.fiap.postechpagamentos.view.form.CriptografiaException;
import com.fiap.postechpagamentos.view.form.DescriptografiaException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.*;

import java.nio.charset.StandardCharsets;

@Component
public class Criptografia {
	@Value("${aplicacao.kmsID}")
	private String kmsID;

	private final Region regiao = Region.US_EAST_1;

	private KmsClient iniciar() {
		return KmsClient
			.builder()
			.region(regiao)
			.build();
	}

	public String criptografar(String valorDescriptografado){
		SdkBytes bytes = SdkBytes.fromByteArray(valorDescriptografado.getBytes(StandardCharsets.UTF_8));
		EncryptRequest criarCriptografia = EncryptRequest
			.builder()
			.keyId(kmsID)
			.plaintext(bytes)
			.build();

		try {
			EncryptResponse dadosCriptografados = this.iniciar().encrypt(criarCriptografia);

			SdkBytes encryptedData = dadosCriptografados.ciphertextBlob();

			return Base64.encodeBase64String(encryptedData.asByteArray());
		} catch (KmsException exception) {
			throw new CriptografiaException("Nao foi possivel criptografar a informacao solicitada");
		}
	}

	public String descriptografar(String valorCriptografado) {
		try {
			Base64.decodeBase64(valorCriptografado);
			SdkBytes bytes = SdkBytes.fromByteArray(Base64.decodeBase64(valorCriptografado));

			DecryptRequest decryptRequest = DecryptRequest.builder()
				.ciphertextBlob(bytes)
				.keyId(kmsID)
				.build();

			DecryptResponse decryptResponse = this.iniciar().decrypt(decryptRequest);
			return new String(decryptResponse.plaintext().asByteArray());

		} catch (KmsException e) {
			throw new DescriptografiaException("Nao foi possivel descriptografar a informacao solicitada");
		}
	}
}
