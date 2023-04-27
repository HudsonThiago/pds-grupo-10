package com.sip.sip.dao;

import com.sip.sip.dto.FiltroDTO;
import com.sip.sip.model.Cargo;
import com.sip.sip.model.Mensagem;
import com.sip.sip.model.Tecnologia;
import com.sip.sip.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface MensagemDAO {

	List<Mensagem> listarMensagens();

	Mensagem buscarMensagemPorId(Long id);

	List<Mensagem> listarMensagemPorDestinatario(Usuario destinatario);

	Mensagem criarMensagem(Mensagem mensagem);

	Mensagem atualizarMensagem(Mensagem mensagem);

	void excluirMensagem(Long id);


}
