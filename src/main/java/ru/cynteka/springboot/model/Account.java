package ru.cynteka.springboot.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="ACCOUNT_TABLE")
public class Account implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name="ACCOUNT", nullable=false)
	private String account;

	@NotEmpty
	@Column(name="PROJECT", nullable=false)
	private String project;

	@NotEmpty
	@Column(name="SUPPLIER", nullable=false)
	private String supplier;

	@NotEmpty
	@Column(name="PAYER", nullable=false)
	private String payer;

	@NotNull
	@Column(name="SUM", nullable=false,scale = 4)
	private BigDecimal sum;

	@NotEmpty
	@Column(name="STATUS", nullable=false)
	private String status;

	public Account() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Account account = (Account) o;

		if (id != null ? !id.equals(account.id) : account.id != null) return false;
		if (account != null ? !account.equals(account.account) : account.account != null) return false;
		if (project != null ? !project.equals(account.project) : account.project != null) return false;
		if (supplier != null ? !supplier.equals(account.supplier) : account.supplier != null) return false;
		if (payer != null ? !payer.equals(account.payer) : account.payer != null) return false;
		if (sum != null ? !sum.equals(account.sum) : account.sum != null) return false;
		return status != null ? status.equals(account.status) : account.status == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (account != null ? account.hashCode() : 0);
		result = 31 * result + (project != null ? project.hashCode() : 0);
		result = 31 * result + (supplier != null ? supplier.hashCode() : 0);
		result = 31 * result + (payer != null ? payer.hashCode() : 0);
		result = 31 * result + (sum != null ? sum.hashCode() : 0);
		result = 31 * result + (status != null ? status.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Account{" +
				"id=" + id +
				", account='" + account + '\'' +
				", project='" + project + '\'' +
				", supplier='" + supplier + '\'' +
				", payer='" + payer + '\'' +
				", sum=" + sum +
				", status='" + status + '\'' +
				'}';
	}
}
