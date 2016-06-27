package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import logical.*;
import screen.ScEditPatient;
import screen.ScInsertPatient;
import screen.StartScreen;

public class ConnectionMySQL {

	private Connection c;
	private String serverName;
	private String dataBase;
	private String port;
	private String url;
	int totalAmount;
	int refinedAmount;
	private ScInsertPatient scIP;
	private ScEditPatient scEP;

	public ConnectionMySQL() {
		openConnection();
	}
	
	public ConnectionMySQL(StartScreen sS) {
		openConnection(sS);
	}
	
	
	public Connection getConn(){
		return c;
	}

	

	public String getURL() {

		JSONObject jO = new JSONObject();
		jO = ConfigNiro.getJson();
		serverName = jO.get("serverName").toString();
		dataBase = jO.get("dataBase").toString();
		port = jO.get("port").toString();
		url = "jdbc:mysql://" + serverName + ":" + port + "/" + dataBase + "?useSSL=false";
		return url;
	}

	public void close() {
		try {

			c.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void openConnection(StartScreen sS) {
		try {
			ConnDataOut cDO = new ConnDataOut();
			c = DriverManager.getConnection(getURL(), cDO.getUser(), cDO.getPassword());
			sS.setConnected(true);
		} catch (Exception e) {
			e.printStackTrace();
			sS.setConnected(false);
		}
	}

	private void openConnection() {
		try {
			ConnDataOut cDO = new ConnDataOut();
			c = DriverManager.getConnection(getURL(), cDO.getUser(), cDO.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ResultSet select(String sql) {
		try {

			Statement stm = c.createStatement();
			ResultSet result = stm.executeQuery(sql);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private boolean connect(String sql) {
		try {

			Statement stm = c.createStatement();
			stm.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Method to insert a new user to data bank
	 */
	public boolean createUser(User u, int code) {
		String sql = "INSERT INTO USER (cod_user, login_user, name_user, type_user, passwd_user) VALUES";
		sql = sql + " (" + code + ", '" + u.getUserName() + "', '" + u.getName() + "', '" + u.getUserType() + "', '"
				+ u.getPasswd() + "');";

		return connect(sql);
	}
	
	public boolean updateUser(User u, int code) {
		
		String sql = "";
		
		if (u.getPasswd().equals("NO_PASSWD")){
			
			sql = "UPDATE USER SET login_user = '" + u.getUserName() + "', name_user = '" + u.getName() + "', type_user = '" + u.getUserType() + "' WHERE cod_user = " + code + ";";
			
			
		}
		
		else{
			
			sql = "UPDATE USER SET login_user = '" + u.getUserName() + "', name_user = '" + u.getName() + "', type_user = '" + u.getUserType() + "', passwd_user = '" + u.getPasswd() + "' WHERE cod_user = " + code + ";";
			
			
		}
		
		

		return connect(sql);
	}
	
	
	
	
	

	/**
	 * Method to insert a new project to data bank
	 */
	public boolean createProject(Project p) {
		String sql = "INSERT INTO PROJECT (cod_project, name_project, USER_cod_user) VALUES";
		sql = sql + " (" + p.getCod() + ", '" + p.getName() + "', '" + p.getCodUser() + "');";

		return connect(sql);
	}
	
	
	public boolean updateProject(Project p) {
		String sql = "UPDATE PROJECT SET name_project = '" + p.getName() + "', USER_cod_user = " + p.getCodUser() + " WHERE cod_project = " + p.getCod() + ";";
		

		return connect(sql);
	}
	
	
	
	

	/**
	 * Method to insert a new family to data bank
	 */
	public boolean createFamily(Family f) {
		String sql = "INSERT INTO FAMILY (cod_family, name_family) VALUES";
		sql = sql + " (" + f.getCod() + ", '" + f.getName() + "');";

		return connect(sql);
	}
	
	
	public boolean updateFamily(Family f) {
		String sql = "UPDATE FAMILY SET name_family = '" + f.getName() + "' WHERE cod_family = " + f.getCod() + ";";
		

		return connect(sql);
	}
	
	
	
	

	/**
	 * Method to insert a new patient, project_patient and patiant-chip to data
	 * bank
	 */
	public boolean createPatient(Patient pa, Chip[] chips, int[] projects, Barcode[] bc, Vcf[] v) {
		String sql = "INSERT INTO PATIENT (cod_patient, name_patient, acro_patient, FAMILY_cod_family) VALUES";
		sql = sql + " (" + pa.getCod() + ", '" + pa.getName() + "', '" + pa.getAcro() + "', '" + pa.getF().getCod()
				+ "');";

		boolean status = connect(sql);

		if (status) {

			for (int i = 0; i < projects.length; i++) {

				sql = "INSERT INTO PROJECT_PATIENT (cod_projectpatient, PROJECT_cod_project, PATIENT_cod_patient) VALUES";
				sql = sql + " (null, '" + projects[i] + "', '" + pa.getCod() + "');";

				if (status) {
					status = connect(sql);
				}

			}

			for (int i = 0; i < bc.length; i++) {

				if (status) {
					status = createBarcode(bc[i]);
				}
			}

			for (int i = 0; i < v.length; i++) {

				if (status) {
					status = createVcf(v[i], bc[i]);

				}
			}

			if (!status) {

				sql = "DELETE FROM PATIENT WHERE cod_patient = " + pa.getCod();

			}

		}

		return status;
	}

	/**
	 * Method to insert a new vcf to data bank
	 */
	public boolean createVcf(Vcf vcf, Barcode bc) {

		String sql = "";

		boolean status = false;

		ArrayList<LineVCF> lVcf = vcf.getLine();

		int current = 0;

		int codNextVar = getMaxCod("VARIATION", "cod_var");

		int codNextBC = getMaxCod("BC_VAR", "cod_bcvar");

		int codVar = 0;

		scIP.setMax(lVcf.size());

		for (int i = 0; i < lVcf.size(); i++) {

			Variation v = new Variation(codNextVar, lVcf.get(i).getChrom(), lVcf.get(i).getPos(), lVcf.get(i).getRef(),
					lVcf.get(i).getAlt(), lVcf.get(i).getInfo());

			codVar = getVarCode(v);

			if (codVar == 0) {

				codNextVar = codNextVar + 1;

				v.setCod(codNextVar);

				sql = "INSERT INTO VARIATION (cod_var, chrom_var, pos_var, refalelle_var, altalelle_var, info_var, ANNOVAR_cod_annovar) VALUES";
				sql = sql + " (" + v.getCod() + ", '" + v.getChrom() + "', '" + v.getPos() + "', '" + v.getRefAlelle()
						+ "', '" + v.getAltAlelle() + "', '" + v.getInfo() + "', null);\n";

				status = connect(sql);

				codVar = codNextVar;

			}

			else
				status = true;

			if (!status)
				return false;

			codNextBC = codNextBC + 1;

			sql = "INSERT INTO BC_VAR (cod_bcvar, quality_bcvar, format_bcvar, data_bcvar, genotype_bcvar, BARCODE_cod_barcode, VARIATION_cod_var) VALUES";
			sql = sql + " (" + codNextBC + ", '" + lVcf.get(i).getQual() + "', '" + lVcf.get(i).getFormat() + "', '"
					+ lVcf.get(i).getData() + "', '" + lVcf.get(i).getData().substring(0, 3) + "', '" + bc.getCod()
					+ "', '" + codVar + "');\n";

			double perc = (double) i / lVcf.size() * 100;

			String percent = String.format("%.2f", perc);

			String progress = current++ + "/" + lVcf.size() + " - " + percent + " %";

			scIP.refreshProgress(i, progress);

			status = connect(sql);

			if (!status)
				return false;

		}

		System.out.println(sql);

		return status;
	}

	/**
	 * Method to insert a new chip to data bank
	 */
	public boolean createChip(Chip c) {
		String sql = "INSERT INTO CHIP (cod_chip, folder_chip, type_chip, resultsCA_chip) VALUES";
		sql = sql + " (" + c.getCod() + ", '" + c.getFolder() + "', '" + c.getType() + "', '" + c.getjO() + "');";

		boolean status = connect(sql);

		return status;
	}
	
	
	public boolean updateChip(Chip c) {
		String sql = "UPDATE CHIP SET type_chip = '" + c.getType() + "' WHERE cod_chip = " + c.getCod() + ";";
		

		boolean status = connect(sql);

		return status;
	}
	
	
	

	/**
	 * Method to insert a new barcode to data bank
	 */
	public boolean createBarcode(Barcode b) {

		String sql = "INSERT INTO BARCODE (cod_barcode, name_barcode, cover20x_barcode, CHIP_cod_chip, PATIENT_cod_patient, header_barcode) VALUES";
		sql = sql + " (" + b.getCod() + ", '" + b.getName() + "', '" + b.getCover20x() + "', '" + b.getC().getCod()
				+ "', '" + b.getPa().getCod() + "', '" + b.getHeader() + "');";

		return connect(sql);
	}

	/**
	 * Method to insert a new variation and bc_var to data bank
	 */
	public boolean createVariation(Variation v) {
		String sql = "INSERT INTO VARIATION (cod_var, chrom_var, pos_var, refalelle_var, altalelle_var, info_var, ANNOVAR_cod_annovar) VALUES";
		sql = sql + " (null, '" + v.getChrom() + "', '" + v.getPos() + "', '" + v.getRefAlelle() + "', '"
				+ v.getAltAlelle() + "', '" + v.getInfo() + "', '" + v.getA() + "');";

		boolean status = connect(sql);

		if (status) {
			for (int i = 0; i < v.getB().length; i++) {

				sql = "INSERT INTO BC_VAR (cod_bcvar, quality_bcvar, format_bcvar, data_bcvar, genotype_bcvar, BARCODE_cod_barcode, VARIATION_cod_var) VALUES";
				sql = sql + " (null, '" + v.getQuality()[i] + "', '" + v.getFormat()[i] + "', '" + v.getData()[i]
						+ "', '" + v.getGenotype()[i] + "', '" + v.getB()[i].getCod() + "', '" + v.getCod() + "');";

				status = connect(sql);

				if (!status)
					return false;

			}

			return status;
		}

		else
			return status;
	}

	/**
	 * Method to insert a new annovar to data bank
	 */
	public boolean createAnnovar(Annovar a) {
		String sql = "INSERT INTO ANNOVAR (cod_annovar, Func.refGene, Gene.refGene, GeneDetail.refGene, ExonicFunc.refGene, AAChange.refGene, num_dbsnp, num_pmid, sift_score, polyphen_score, mutaster_score, pathogenicity                                 ) VALUES";
		sql = sql + " (null, '" + a.getFunc() + "', '" + a.getGene() + "', '" + a.getGeneDetail() + "', '"
				+ a.getExonicFunc() + "', '" + a.getAaChange() + "', '" + a.getNumDBSP() + "', '" + a.getNumPMID()
				+ "', '" + a.getSift() + "', '" + a.getPolyphen() + "', '" + a.getMutTaster() + "', '"
				+ a.getPathogenicity() + "');";

		return connect(sql);
	}

	public ResultSet callBD(String SQL) {
		try {
			return select(SQL);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getMaxCod(String tableName, String colName) {
		try {
			String SQL = "SELECT IFNULL(MAX(" + colName + "),0) from " + tableName;

			ResultSet rs = select(SQL);

			int result = 0;

			rs.next();

			result = Integer.parseInt(rs.getObject(1).toString());

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public User[] getAllUser() {
		try {
			String sql = "SELECT cod_user, name_user, type_user FROM USER ORDER BY cod_user;";
			ResultSet rs = select(sql);

			if (rs != null) {
				int line = 0;
				while (rs.next()) {
					line = rs.getRow();

				}
				User allUser[] = new User[line];

				for (int i = 1; i <= line; i++) {

					rs.absolute(i);

					String codUser = rs.getObject(1).toString();
					String nameUser = rs.getObject(2).toString();

					allUser[i - 1] = new User(codUser, nameUser);

				}

				return allUser;
			} else
				return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Gene[] getAllGenes(String gene) {
		try {
			
			
			
			String sql = "SELECT cod_gene, name_gene FROM GENE WHERE name_gene LIKE '%"+ gene + "%' ORDER BY name_gene;";
			ResultSet rs = select(sql);
			
			
			if (rs != null) {
				
				int line = 0;
				while (rs.next()) {
					
					line = rs.getRow();

				}
				Gene allGene[] = new Gene[line];

				for (int i = 1; i <= line; i++) {
					
					

					rs.absolute(i);

					int codGene = Integer.parseInt(rs.getObject(1).toString());
					String nameGene = rs.getObject(2).toString();

					allGene[i - 1] = new Gene(codGene, nameGene);
					
					

				}

				return allGene;
			} else
				return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	

	public Patient[] getAllPatient() {
		try {
			String sql = "SELECT cod_patient, name_patient FROM PATIENT ORDER BY cod_patient;";
			ResultSet rs = select(sql);

			if (rs != null) {
				int line = 0;
				while (rs.next()) {
					line = rs.getRow();

				}
				Patient allPatient[] = new Patient[line];

				for (int i = 1; i <= line; i++) {

					rs.absolute(i);

					int codPatient = Integer.parseInt(rs.getObject(1).toString());
					String namePatient = rs.getObject(2).toString();

					allPatient[i - 1] = new Patient(namePatient, codPatient);

				}

				return allPatient;
			} else
				return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public GeneList[] getAllGeneList(){
		
		
		try {
			String sql = "SELECT cod_list, name_list from LIST ORDER BY cod_list;";
			ResultSet rs = select(sql);

			if (rs != null) {
				int line = 0;
				while (rs.next()) {
					line = rs.getRow();

				}
				GeneList allGL[] = new GeneList[line];

				for (int i = 1; i <= line; i++) {

					rs.absolute(i);

					int codList = Integer.parseInt(rs.getObject(1).toString());
					String nameList = rs.getObject(2).toString();

			//		allGL[i - 1] = codList + " - " + nameList;
					
					allGL[i - 1] = new GeneList(codList, nameList);
					

				}

				return allGL;
			} else
				return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	

	public Family[] getAllFamily() {
		try {
			String sql = "SELECT cod_family, name_family FROM FAMILY ORDER BY cod_family;";
			ResultSet rs = select(sql);

			if (rs != null) {
				int line = 0;
				while (rs.next()) {
					line = rs.getRow();

				}
				Family allFamily[] = new Family[line];

				for (int i = 1; i <= line; i++) {

					rs.absolute(i);

					int codFamily = Integer.parseInt(rs.getObject(1).toString());
					String nameFamily = rs.getObject(2).toString();

					allFamily[i - 1] = new Family(nameFamily, codFamily);

				}

				return allFamily;
			} else
				return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	

	public Chip[] getAllChip() {
		try {
			String sql = "SELECT cod_chip, folder_chip, type_chip FROM CHIP ORDER BY cod_chip;";
			ResultSet rs = select(sql);

			if (rs != null) {
				int line = 0;
				while (rs.next()) {
					line = rs.getRow();

				}
				Chip allChip[] = new Chip[line];

				for (int i = 1; i <= line; i++) {

					rs.absolute(i);

					int codChip = Integer.parseInt(rs.getObject(1).toString());
					String folderChip = rs.getObject(2).toString();
					String typeChip = rs.getObject(3).toString();

					allChip[i - 1] = new Chip(codChip, folderChip, typeChip);

				}

				return allChip;
			} else
				return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Chip getChip(int code){
		
		try {
			String sql = "SELECT folder_chip, type_chip, resultsCA_chip FROM CHIP WHERE cod_chip = " + code + ";";
			ResultSet rs = select(sql);
			rs.absolute(1);
			
			Chip c = new Chip(code, rs.getObject(1).toString(), rs.getObject(2).toString());
			
			c.setjO(rs.getObject(3).toString());
			
			return c;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	

	public String getChipResults(int code) {

		try {
			String sql = "SELECT resultsCA_chip FROM CHIP WHERE cod_chip = " + code + ";";
			ResultSet rs = select(sql);
			rs.absolute(1);
			String result = rs.getObject(1).toString();

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public int getVarCode(Variation v) {
		try {
			String SQL = "SELECT IFNULL ( (SELECT cod_var FROM VARIATION WHERE chrom_var = '" + v.getChrom()
					+ "' AND pos_var = " + v.getPos() + " AND altalelle_var = '" + v.getAltAlelle() + "'),0);";
			ResultSet rs = select(SQL);

			rs.next();

			int result = Integer.parseInt(rs.getObject(1).toString());

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	
	
	
	

	public Patient[] getAllCodPatient() {
		try {
			String sql = "SELECT cod_patient FROM PATIENT ORDER BY cod_patient;";
			ResultSet rs = select(sql);

			if (rs != null) {
				int line = 0;
				while (rs.next()) {
					line = rs.getRow();

				}
				Patient allPatient[] = new Patient[line];

				for (int i = 1; i <= line; i++) {

					rs.absolute(i);

					int codPatient = Integer.parseInt(rs.getObject(1).toString());

					allPatient[i - 1] = new Patient(codPatient);

				}

				return allPatient;
			} else
				return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Project getProject(int code){
		
		
		

		try {
			String sql = "SELECT name_project, USER_cod_user FROM PROJECT WHERE cod_project = " + code + ";";
			ResultSet rs = select(sql);
			rs.next();
			Project p = new Project(rs.getObject(1).toString(), code, Integer.parseInt(rs.getObject(2).toString()));
			return p;		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	

	public Project[] getProjects(int p[]) {

		try {

			String sql = "";

			Project allProject[] = new Project[p.length];

			for (int j = 0; j < p.length; j++) {

				sql = "SELECT cod_project, name_project FROM PROJECT WHERE cod_project = " + p[j] + ";";

				ResultSet rs = select(sql);

				rs.next();

				int codProject = Integer.parseInt(rs.getObject(1).toString());
				String nameProject = rs.getObject(2).toString();

				allProject[j] = new Project(codProject, nameProject);

			}

			return allProject;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Project[] getAllProject() {
		try {
			String sql = "SELECT cod_project, name_project FROM PROJECT ORDER BY cod_project;";
			ResultSet rs = select(sql);

			if (rs != null) {
				int line = 0;
				while (rs.next()) {
					line = rs.getRow();

				}
				Project allProject[] = new Project[line];

				for (int i = 1; i <= line; i++) {

					rs.absolute(i);

					int codProject = Integer.parseInt(rs.getObject(1).toString());
					String nameProject = rs.getObject(2).toString();

					allProject[i - 1] = new Project(codProject, nameProject);

				}

				return allProject;
			} else
				return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Vcf mountVcf(Patient pa, double cutOff, int list) {

		try {

			String sql = "SELECT header_barcode FROM BARCODE where PATIENT_cod_patient = " + pa.getCod() + ";";
			ResultSet rs = select(sql);
			rs.next();
			String header = rs.getObject(1).toString();

			ArrayList<LineVCF> vcf = getRefinedVcfData(pa, cutOff);
			
			if (list > 0){
				
				ResultSet rsl = getGeneList(list);
				
				for (int i = vcf.size() - 1; i >= 0; i--){
					
					
					
					if (!foundInGeneList(vcf.get(i), rsl, i)){
						vcf.remove(i);
					}
					
					
				}
				
				
				
			}
			
			

			Vcf v = new Vcf(header, vcf);

			v.setTotalAmount(totalAmount);
			v.setRefinedAmount(refinedAmount);

			rs = null;

			return v;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public boolean foundInGeneList(LineVCF lvcf, ResultSet rs, int i){
		
		try {
			while (rs.next()) {
				
		//		System.out.println(i);
				
		//		System.out.println(lvcf.getChrom() + "  " + rs.getObject(1).toString());
				
				if (lvcf.getChrom().equals(rs.getObject(1).toString())){
					
				//	System.out.println(lvcf.toString() + "   " + rs.getObject(2).toString() + " - " + rs.getObject(3).toString());
					
					if (lvcf.getPos() >= Long.parseLong(rs.getObject(2).toString()) && lvcf.getPos() <= Long.parseLong(rs.getObject(3).toString())){
						
						rs.absolute(0);
						
						return true;
						
						
					}
					
				}
				
			}
			
			rs.absolute(0);
			
			return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
	
	public ResultSet getGeneList (int list){
		
		try {
			
			String sql = "SELECT g.chr_gene, g.min_gene, g.max_gene FROM GENE g, LIST l, GENE_LIST gl WHERE l.cod_list = gl.LIST_cod_list AND g.cod_gene = gl.GENE_cod_gene AND l.cod_list = " + list + ";";
			
			return select(sql);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		
		
	}
	

	public ArrayList<LineVCF> getRefinedVcfData(Patient pa, double cutOff) {

		try {

			ArrayList<LineVCF> vcf = new ArrayList<LineVCF>();

			String sql = "SELECT COUNT(cod_bcvar) from BC_VAR WHERE BARCODE_cod_barcode = " + pa.getCod() + ";";

			ResultSet rs = select(sql);

			rs.next();

			totalAmount = Integer.parseInt(rs.getObject(1).toString());

			refinedAmount = 0;

			sql = "SELECT v.chrom_var, v.pos_var, v.refalelle_var, v.altalelle_var, b.quality_bcvar, v.info_var, b.format_bcvar, b.data_bcvar, v.cod_var FROM VARIATION v, BC_VAR b WHERE v.cod_var = b.VARIATION_cod_var AND BARCODE_cod_barcode = "
					+ pa.getCod()
					+ " AND (SELECT COUNT(bv.cod_bcvar) FROM BC_VAR bv, BARCODE bc WHERE bv.BARCODE_cod_barcode = bc.cod_barcode AND bc.cover20x_barcode >= 90 AND bv.VARIATION_COD_VAR = b.VARIATION_cod_var) < (SELECT COUNT(cod_barcode) FROM BARCODE WHERE cover20x_barcode >= 90) * "
					+ cutOff + ";";

			rs = select(sql);

			while (rs.next()) {

				LineVCF lVCF = new LineVCF();

				lVCF.setChrom(rs.getObject(1).toString());
				lVCF.setPos(Integer.parseInt(rs.getObject(2).toString()));
				lVCF.setId(".");
				lVCF.setRef(rs.getObject(3).toString());
				lVCF.setAlt(rs.getObject(4).toString());
				lVCF.setQual(Double.parseDouble(rs.getObject(5).toString()));
				lVCF.setFilter("PASS");
				lVCF.setInfo(rs.getObject(6).toString());
				lVCF.setFormat(rs.getObject(7).toString());
				lVCF.setData(rs.getObject(8).toString());

				refinedAmount++;

				vcf.add(lVCF);

				System.out.println("Adicionou: " + refinedAmount);

			}

			rs = null;

			return vcf;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean delete(int cod, String table) {

		String sql = "DELETE FROM " + table.toUpperCase() + " WHERE cod_" + table.toLowerCase() + " = " + cod + ";";

		boolean status = connect(sql);

		return status;
	}
	
	
	
	public User getUser(int cod){
		
		// String name, String user, String passwd, String type, String cod
		
		try {

			// sql that returns patient data from code cod
			String sql = "SELECT name_user, login_user, passwd_user, type_user, cod_user FROM USER WHERE cod_user = " + cod	+ ";";

			ResultSet rs = select(sql);
			
			rs.next();
			
			String name = rs.getObject(1).toString();
			String user = rs.getObject(2).toString();
			String passwd = rs.getObject(3).toString();
			String type = rs.getObject(4).toString();
			String code = rs.getObject(5).toString();
		
			
			
			User u = new User(name, user, passwd, type, code);
			
			return u;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
			
			
	
	
	
	
	public Family getFamily(int cod){
		
		try {

			// sql that returns patient data from code cod
			String sql = "SELECT cod_family, name_family FROM FAMILY WHERE cod_family = " + cod	+ ";";

			ResultSet rs = select(sql);
			
			rs.next();
			
			Family f = new Family (rs.getObject(2).toString(), Integer.parseInt(rs.getObject(1).toString()));
			
			return f;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}
		
	}
	

	public Patient getPatient(int cod) {

		try {

			// sql that returns patient data from code cod
			String sql = "SELECT name_patient, acro_patient, FAMILY_cod_family FROM PATIENT WHERE cod_patient = " + cod
					+ ";";

			ResultSet rs = select(sql);

			String namePat = "";
			String acroPat = "";
			int familyPat = 0;
			Patient pat;

			while (rs.next()) {

				namePat = rs.getObject(1).toString();
				acroPat = rs.getObject(2).toString();
				familyPat = Integer.parseInt(rs.getObject(3).toString());
				Project p[];
				Chip c[];

				rs.close();

				// sql that returns project information of each project related
				// to the patient code cod
				sql = "SELECT p.cod_project, p.name_project FROM PROJECT p, PROJECT_PATIENT pp WHERE p.cod_project = pp.PROJECT_cod_project AND pp.PATIENT_cod_patient = "
						+ cod + ";";

				rs = select(sql);

				int line = 0;

				while (rs.next()) {
					line = rs.getRow();

				}
				p = new Project[line];

				for (int i = 1; i <= line; i++) {

					rs.absolute(i);

					int codProject = Integer.parseInt(rs.getObject(1).toString());
					String nameProject = rs.getObject(2).toString();

					p[i - 1] = new Project(codProject, nameProject);

				}

				sql = "SELECT c.cod_chip, c.folder_chip, c.type_chip FROM CHIP c, BARCODE bc WHERE c.cod_chip = bc.CHIP_cod_chip AND bc.PATIENT_cod_patient = "
						+ cod + ";";

				rs = select(sql);

				while (rs.next()) {
					line = rs.getRow();

				}
				c = new Chip[line];

				for (int i = 1; i <= line; i++) {

					rs.absolute(i);

					int codChip = Integer.parseInt(rs.getObject(1).toString());
					String folderChip = rs.getObject(2).toString();
					String typeChip = rs.getObject(3).toString();

					c[i - 1] = new Chip(codChip, folderChip, typeChip);

				}

				Family f = new Family(familyPat);

				sql = "SELECT name_family FROM FAMILY WHERE cod_family = " + familyPat + ";";

				rs = select(sql);

				rs.next();

				f.setName(rs.getObject(1).toString());

				pat = new Patient(namePat, acroPat, cod, f, p, c);

				rs.close();

				return pat;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}

		return null;

	}

	public boolean SQL(String sql) {

		return connect(sql);
	}

	public void setScIP(ScInsertPatient scIP) {
		this.scIP = scIP;

	}

	public void setScEP(ScEditPatient scEP) {
		this.scEP = scEP;

	}
	
	public ScEditPatient getScEP(){
		return scEP;
	}

}
