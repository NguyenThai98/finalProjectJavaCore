package Dice;
import java.io.Serializable;

public class Dto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String NAME;
	private String PASSWORD;
	private String MONEY;
	private String typeOfUser;
	private String info;
	private int id;
	private int resultGame; 
	private int idGame;
	
	public int getIdGame() {
		return idGame;
	}

	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}

	public int getResultGame() {
		return resultGame;
	}

	public void setResultGame(int resultGame) {
		this.resultGame = resultGame;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Dto() {
		super();
	}
	
	public Dto(String NAME, String PASSWORD, String MONEY, String typeOfUser) {
		this.NAME = NAME;
		this.PASSWORD = PASSWORD;
		this.MONEY = MONEY;
		this.typeOfUser = typeOfUser;
	}
	

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String NAME) {
		this.NAME = NAME;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String PASSWORD) {
		this.PASSWORD = PASSWORD;
	}

	public String getMONEY() {
		return MONEY;
	}

	public void setMONEY(String MONEY) {
		this.MONEY = MONEY;
	}

	public String getTypeOfUser() {
		return typeOfUser;
	}

	public void setTypeOfUser(String typeOfUser) {
		this.typeOfUser = typeOfUser;
	}

	
	
}
