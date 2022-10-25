package br.com.thiers.mvc.W_Thiers_loja.Model;

import javax.persistence.Embeddable;

@Embeddable
public class Authority {
	@SuppressWarnings("unused")
	private String authority;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public Authority(String string) {
		this.authority = string;
	}
	
	public Authority() {
		
	}
}
