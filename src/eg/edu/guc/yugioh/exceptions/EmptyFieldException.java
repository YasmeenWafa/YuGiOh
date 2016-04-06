package eg.edu.guc.yugioh.exceptions;

public class EmptyFieldException extends UnexpectedFormatException{

	private int sourceField;
	
	public EmptyFieldException(String sourceFile, int sourceLine, int sourceField) {
		super(sourceFile, sourceLine);
		this.sourceField = sourceField;
		// TODO Auto-generated constructor stub
	}
	public int getSourceField()
	{
		return this.sourceField;
	}

}
