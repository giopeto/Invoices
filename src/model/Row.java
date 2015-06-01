package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="rows")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="invoice")
public class Row {


	@Id @GeneratedValue
	@Column(nullable=false)
	private long id;
	
	private Long item_id;
	
	@ManyToOne
	@JoinColumn(name="invoice_id")
	@JsonIgnore
	private Invoice invoice;
	
	
	public Row(Long item_id, Invoice invoice) {
		super();
		this.invoice = invoice;
		this.item_id = item_id;
	}
	public Row() {
		super();
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Long getItemId() {
		return item_id;
	}

	public void seItemId(Long item_id) {
		this.item_id = item_id;
	}
	
	
	
	
		
		
		
}
