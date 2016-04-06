package eg.edu.guc.yugioh.exceptions;

public class UnknownCardTypeException extends UnexpectedFormatException{

	private String unknownType;
	
	public UnknownCardTypeException(String sourceFile, int sourceLine, String unknownType) {
		super(sourceFile, sourceLine);
		this.unknownType = unknownType;
		// TODO Auto-generated constructor stub
	}
	public String getUnknownType()
	{
		return this.unknownType;
	}

}
