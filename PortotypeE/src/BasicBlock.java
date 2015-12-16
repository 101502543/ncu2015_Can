public class BasicBlock {
	private int type;
	private String[] typeImage = { "gress.png", "rocks.png", "road_x.png",
			"road_y.png", "road_cross.png", "corner_lu.png", "corner_ld.png",
			"corner_ru.png", "corner_rd.png", "road_lb.png", "road_rb.png", "road_ub.png", "road_db.png" };

	// type 表示block類型
	// 例如 草原 = 0、岩石 = 1等等
	public BasicBlock(int set_type) {
		// TODO Auto-generated constructor stub
		type = set_type;
	}

	public String getTypeImage() {
		return typeImage[type];
	}
	public Integer getType() {
		return type;
	}
}
