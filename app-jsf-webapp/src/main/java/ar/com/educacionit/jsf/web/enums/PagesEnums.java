package ar.com.educacionit.jsf.web.enums;

public enum PagesEnums {

	LOGIN_SUCCESS("login-success"),
	LOGIN("login"),
	LOGIN_ERROR("login-error");
	
	private String page;
	
	private PagesEnums(String page) {
		this.page = page;
	}

	public String getPage() {
		return page;
	}
	
}

