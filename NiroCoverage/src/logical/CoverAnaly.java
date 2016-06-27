package logical;

public class CoverAnaly
{
  private String contig_id;
  private long contig_srt;
  private long contig_end;
  private String region_id;
  private String attributes;
  private int gc;
  private int overlaps;
  private int fwd_e2e;
  private int rev_e2e;
  private int total_reads;
  private int fwd_reads;
  private int rev_reads;
  private char flagColour;
  
  public CoverAnaly(String contig_id, long contig_srt, long contig_end, String region_id, String attributes, int gc, int overlaps, int fwd_e2e, int rev_e2e, int total_reads, int fwd_reads, int rev_reads, char flagColour)
  {
    this.contig_id = contig_id;
    this.contig_srt = contig_srt;
    this.contig_end = contig_end;
    this.region_id = region_id;
    this.attributes = attributes;
    this.gc = gc;
    this.overlaps = overlaps;
    this.fwd_e2e = fwd_e2e;
    this.rev_e2e = rev_e2e;
    this.total_reads = total_reads;
    this.fwd_reads = fwd_reads;
    this.rev_reads = rev_reads;
    this.flagColour = flagColour;
  }
  
  public CoverAnaly()
  {
    this.contig_id = null;
    this.contig_srt = 0L;
    this.contig_end = 0L;
    this.region_id = null;
    this.attributes = null;
    this.gc = 0;
    this.overlaps = 0;
    this.fwd_e2e = 0;
    this.rev_e2e = 0;
    this.total_reads = 0;
    this.fwd_reads = 0;
    this.rev_reads = 0;
    this.flagColour = 'N';
  }
  
  public String getContig_id()
  {
    return this.contig_id;
  }
  
  public void setContig_id(String contig_id)
  {
    this.contig_id = contig_id;
  }
  
  public long getContig_srt()
  {
    return this.contig_srt;
  }
  
  public void setContig_srt(long contig_srt)
  {
    this.contig_srt = contig_srt;
  }
  
  public long getContig_end()
  {
    return this.contig_end;
  }
  
  public void setContig_end(long contig_end)
  {
    this.contig_end = contig_end;
  }
  
  public String getRegion_id()
  {
    return this.region_id;
  }
  
  public void setRegion_id(String region_id)
  {
    this.region_id = region_id;
  }
  
  public String getAttributes()
  {
    return this.attributes;
  }
  
  public void setAttributes(String attributes)
  {
    this.attributes = attributes;
  }
  
  public int getGc()
  {
    return this.gc;
  }
  
  public void setGc(int gc)
  {
    this.gc = gc;
  }
  
  public int getOverlaps()
  {
    return this.overlaps;
  }
  
  public void setOverlaps(int overlaps)
  {
    this.overlaps = overlaps;
  }
  
  public int getFwd_e2e()
  {
    return this.fwd_e2e;
  }
  
  public void setFwd_e2e(int fwd_e2e)
  {
    this.fwd_e2e = fwd_e2e;
  }
  
  public int getRev_e2e()
  {
    return this.rev_e2e;
  }
  
  public void setRev_e2e(int rev_e2e)
  {
    this.rev_e2e = rev_e2e;
  }
  
  public int getTotal_reads()
  {
    return this.total_reads;
  }
  
  public void setTotal_reads(int total_reads)
  {
    this.total_reads = total_reads;
  }
  
  public int getFwd_reads()
  {
    return this.fwd_reads;
  }
  
  public void setFwd_reads(int fwd_reads)
  {
    this.fwd_reads = fwd_reads;
  }
  
  public int getRev_reads()
  {
    return this.rev_reads;
  }
  
  public void setRev_reads(int rev_reads)
  {
    this.rev_reads = rev_reads;
  }
  
  public char getFlagColour()
  {
    return this.flagColour;
  }
  
  public void setFlagColour(char flagColour)
  {
    this.flagColour = flagColour;
  }
}
