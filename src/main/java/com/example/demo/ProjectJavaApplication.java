package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Cidade;
import com.example.demo.domain.Cliente;
import com.example.demo.domain.Endereco;
import com.example.demo.domain.Estado;
import com.example.demo.domain.Pagamento;
import com.example.demo.domain.PagamentoComBoleto;
import com.example.demo.domain.PagamentoComCartao;
import com.example.demo.domain.Pedido;
import com.example.demo.domain.Produto;
import com.example.demo.domain.enums.EstadoPagamento;
import com.example.demo.domain.enums.TipoCliente;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.CidadeRepository;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.EnderecoRepository;
import com.example.demo.repositories.EstadoRepository;
import com.example.demo.repositories.PagamentoRepository;
import com.example.demo.repositories.PedidoRepository;
import com.example.demo.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjectJavaApplication implements CommandLineRunner {
	
	@Autowired
	 CategoriaRepository categoriaRepository;

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	PagamentoRepository pagamentoRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(ProjectJavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 =  new Produto(null, "Computaddor", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));


		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		
		Cliente cliente1 = new Cliente(null, "João Silva", "joao.silva@example.com", "123.456.789-00", TipoCliente.PESSOAFISICA);
		Cliente cliente2 = new Cliente(null, "Maria Oliveira", "maria.oliveira@example.com", "987.654.321-00", TipoCliente.PESSOAJURIDICA);
		Cliente cliente3 = new Cliente(null, "Carlos Pereira", "carlos.pereira@example.com", "456.789.123-00", TipoCliente.PESSOAFISICA);
		
		cliente1.getTelefones().addAll(Arrays.asList("27363323", "*938383983"));
		cliente2.getTelefones().addAll(Arrays.asList("12345678", "912345678"));
		cliente3.getTelefones().addAll(Arrays.asList("87654321", "998765432"));
		

		Endereco endereco1 = new Endereco(1, "Rua das Palmeiras", "123", "Apto 201", "Centro", "12345-678", cliente1, c1);
		Endereco endereco2 = new Endereco(2, "Avenida Brasil", "456", "Casa", "Jardim Europa", "23456-789", cliente2, c2);
		Endereco endereco3 = new Endereco(3, "Travessa da Paz", "789", "Bloco B", "Jardim das Flores", "34567-890", cliente3, c3);

		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
		cliente2.getEnderecos().addAll(Arrays.asList(endereco3, endereco2));

		clienteRepository.saveAll(Arrays.asList(cliente1, cliente2, cliente3));
		enderecoRepository.saveAllAndFlush(Arrays.asList(endereco1, endereco2, endereco3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente1, endereco1 );
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 10:32"), cliente1, endereco2 );

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cliente1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
	}

}
