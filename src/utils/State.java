package utils;

public enum State {
	RENTABLE("0", "可借"),
	RENTED("1", "已借出");
	
	public String code;
	public String label;
	private State(String code, String label) {
		this.code = code;
		this.label = label;
	}
	public static State getFromCode(String code) {
		
		for (State each : values()) {
			if(each.code.equalsIgnoreCase(code)) {
				return each;
			}
		}
		return null;
	}
}
