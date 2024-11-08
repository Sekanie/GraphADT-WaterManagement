public class WaterReservoir {
	private double PI=3.14159265358;
	private String name;
	private String region;
	private double baseRadius;
	private double height;
	private double waterVolume;
	private double percentageFull;
	
	//parameterless constructor
	WaterReservoir()
	{
		name="";
		region="";
		baseRadius=0.00;
		height=0.00;
		waterVolume=0.00;
		percentageFull=0.00;
		
	}
	WaterReservoir(String n,String re,double r,double h,double w)
	{
		name=n;
		region=re;
		baseRadius=r;
		height=h;
		if(w>getCapacity())
		{
			waterVolume=getCapacity();
		}else	
		{
			waterVolume=w;
		}
		percentageFull=(waterVolume/(getCapacity()))*100;
	}
	public double getPI() {
		return PI;
	}
	public void setPIE(double pI) {
		PI = pI;
	}
	public String getName() {
		return name;
	}
	public void setName(String nameOfReservoir) {
		this.name = nameOfReservoir;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String nameOfRegion) {
		this.region = nameOfRegion;
	}
	public double getBaseRadius() {
		return baseRadius;
	}
	public void setBaseRadius(double baseRadius) {
		this.baseRadius = baseRadius;
		//recalculate percentageFull
		if(waterVolume<=getCapacity())
		{
			percentageFull=(waterVolume/(getCapacity()))*100;
		}else
		{
			percentageFull=100.00;
		}
		
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
		//recalculate percentageFull
		if(waterVolume<=getCapacity())
		{
			percentageFull=(waterVolume/(getCapacity()))*100;
		}else
		{
			percentageFull=100.00;
		}
	}
	public double getWaterVolume() {
		return waterVolume;
	}
	public void setWaterVolume(double waterVolume) {
		this.waterVolume = waterVolume;
		//recalculate percentageFull
		if(waterVolume<=getCapacity())
		{
			percentageFull=(waterVolume/(getCapacity()))*100;
		}else
		{
			percentageFull=100.00;
		}
	}
	public double getPercentageFull() {
		return percentageFull;
	}
	public double getCapacity()
	{
		return PI*baseRadius*baseRadius*height;
	}
	
	
}

