public class BasicBlock {
	private int type;
	private String[] typeImage = { "landscape_28.png", "rocks_5.png"};

	// type ���block����
	// �Ҧp ��� = 0�B���� = 1����
	public BasicBlock(int set_type) {
		// TODO Auto-generated constructor stub
		type = set_type;
	}

	public String getType() {
		return typeImage[type];
	}
}
