package eg.edu.guc.yugioh.exceptions;

public class UnexpectedFormatException extends Exception{
	
	private String sourceFile;
	private int sourceLine;
	private String message = "Unexpected format please try again" ;
	
	public UnexpectedFormatException(String sourceFile,int sourceLine)
	{
		this.sourceFile = sourceFile;
		this.sourceLine = sourceLine;
	}
	public int getSourceLine()
	{
		return this.sourceLine;
	}
	public String getSourceFile()
	{
		return this.sourceFile;
	}
	public String getMessage()
	{
		return this.message;
	}
	public void setMessage( String newmessage)
	{
		this.message = newmessage;
	}

}
