public class BasicBlock {
	private int type;
	private String[] typeImage = { "landscape_28.png", "rocks_5.png"};

	// type 表示block類型
	// 例如 草原 = 0、岩石 = 1等等
	public BasicBlock(int set_type) {
		// TODO Auto-generated constructor stub
		type = set_type;
	}

	public String getType() {
		return typeImage[type];
	}
}
