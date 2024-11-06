package com.example.demo.domain;

import java.io.Serializable;
import java.util.Objects;

public class ItemPedido implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ItemPedidoPk id = new ItemPedidoPk();
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	public ItemPedido() {
		
	}
	
	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidadde, Double preco) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidadde;
		this.preco = preco;
	}
	
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	public Produto getProduto() {
		return id.getProduto();
	}

	public ItemPedidoPk getId() {
		return id;
	}

	public void setId(ItemPedidoPk id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidadde() {
		return quantidade;
	}

	public void setQuantidadde(Integer quantidadde) {
		this.quantidade = quantidadde;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}
