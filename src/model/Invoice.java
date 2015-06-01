package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "invoices")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="invoice")
public class Invoice {

	@Id
	@GeneratedValue
	private long id;
	
	private String num;
	@OneToMany(fetch = FetchType.LAZY, mappedBy="invoice")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Set<Row> row = new HashSet<Row>();
	
	public Invoice(String num) {
		super();
		this.num = num;
	}
	
	public Invoice() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public Set<Row> getRow() {
		return row;
	}
	public void setRow(Set<Row> row) {
		this.row = row;
	}
}
