package eg.edu.guc.yugioh.exceptions;

public class UnknownSpellCardException extends UnexpectedFormatException{

	private String unknownSpell;
	public UnknownSpellCardException(String sourceFile, int sourceLine, String unknownSpell) {
		super(sourceFile, sourceLine);
		this.unknownSpell = unknownSpell;
		// TODO Auto-generated constructor stub
	}
	public String getUnknownSpell()
	{
		return this.unknownSpell;
	}
	

}
