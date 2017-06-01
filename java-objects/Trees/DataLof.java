
class DataLof implements ILof {
	AbsAttraction data;
	ILof list;  //can be DataLof or MtLof
	
	DataLof(AbsAttraction data, ILof list){
		this.data = data;
		this.list = list;
	}
	
	public ILof filter(ISelect s) {
		
		return null;
	}

	
	public ILof addTo(ILof list2) {
		return new DataLof(data, list2);
	}

}
