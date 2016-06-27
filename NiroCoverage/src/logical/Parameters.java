package logical;

public class Parameters
{
  private int redParam;
  private int yellowParam;
  private String inputPathCoverAna;
  private String inputPathVarCaller;
  private String outputPath;
  private final int RED_PARAM_DEF = 5;
  private final int YELLOW_PARAM_DEF = 30;
  
  public int getRED_PARAM_DEF() {
	return RED_PARAM_DEF;
}

public int getYELLOW_PARAM_DEF() {
	return YELLOW_PARAM_DEF;
}

public Parameters()
  {
    this.redParam = 5;
    this.yellowParam = 30;
    this.inputPathCoverAna = null;
    this.inputPathVarCaller = null;
    this.outputPath = null;
  }
  
  public int getRedParam()
  {
    return this.redParam;
  }
  
  public void setRedParam(int redParam)
  {
    this.redParam = redParam;
  }
  
  public int getYellowParam()
  {
    return this.yellowParam;
  }
  
  public void setYellowParam(int yellowParam)
  {
    this.yellowParam = yellowParam;
  }
  
  public String getInputPathCoverAna()
  {
    return this.inputPathCoverAna;
  }
  
  public void setInputPathCoverAna(String inputPathCoverAna)
  {
    this.inputPathCoverAna = inputPathCoverAna;
  }
  
  public String getInputPathVarCaller()
  {
    return this.inputPathVarCaller;
  }
  
  public void setInputPathVarCaller(String inputPathVarCaller)
  {
    this.inputPathVarCaller = inputPathVarCaller;
  }
  
  public String getOutputPath()
  {
    return this.outputPath;
  }
  
  public void setOutputPath(String outputPath)
  {
    this.outputPath = outputPath;
  }
}
