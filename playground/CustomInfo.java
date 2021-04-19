package playpark;

public class CustomInfo {
	private String date ="";
	private int ticketkind = 0; 
	private int age = 0; 
	private int agekind = 0; 
	private int ticketnum = 0; 
	private int ticketprice = 0; 
	private int dc = 0;
	private String agegroup = "";
	private String ticketname = "";
	private String dcname = "";
	
	
	public int getAgekind() {
		return agekind;
	}
	public void setAgekind(int agekind) {
		this.agekind = agekind;
	}
	public String getDcname() {
		return dcname;
	}
	public void setDcname(String dcname) {
		this.dcname = dcname;
	}
	public String getTicketname() {
		return ticketname;
	}
	public void setTicketname(String ticketname) {
		this.ticketname = ticketname;
	}	
	public String getAgegroup() {
		return agegroup;
	}
	public void setAgegroup(String agegroup) {
		this.agegroup = agegroup;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getTicketkind() {
		return ticketkind;
	}
	public void setTicketkind(int ticketkind) {
		this.ticketkind = ticketkind;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getTicketnum() {
		return ticketnum;
	}
	public void setTicketnum(int ticketnum) {
		this.ticketnum = ticketnum;
	}
	public int getTicketprice() {
		return ticketprice;
	}
	public void setTicketprice(int ticketprice) {
		this.ticketprice = ticketprice;
	}
	public int getDc() {
		return dc;
	}
	public void setDc(int dc) {
		this.dc = dc;
	}
}
