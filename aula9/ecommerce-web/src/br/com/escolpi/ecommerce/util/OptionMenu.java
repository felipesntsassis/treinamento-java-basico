package br.com.escolpi.ecommerce.util;

public class OptionMenu {

	private String action;
	private String cssColor;
	private String icon;
	private Long id;
	private String label;

	public OptionMenu(Long id, String action, String cssColor, String icon, String label) {
		this.id = id;
		this.action = action;
		this.cssColor = cssColor;
		this.icon = icon;
		this.label = label;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCssColor() {
		return cssColor;
	}

	public void setCssColor(String cssColor) {
		this.cssColor = cssColor;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
