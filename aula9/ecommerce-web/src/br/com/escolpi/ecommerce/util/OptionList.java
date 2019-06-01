package br.com.escolpi.ecommerce.util;

public class OptionList {

	private Long value;
	private String label;

	public OptionList(Long value, String label) {
		this.value = value;
		this.label = label;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
