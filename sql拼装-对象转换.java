public class Utils {
/**
	 * 方法名 ：getObjtoStr<br>
	 * 方法描述 ：把对象参数转换为字符串,逗号分隔<br>
	 * 创建者 ：Gavin<br>
	 * 创建时间 ：2014-11-27下午06:09:08 <br>
	 * @param obj
	 * @return
	 * 返回类型 ：String
	 */
	public static String getObjecttoStr(Object... obj){
		StringBuffer sbf =new StringBuffer();
		for(int i=0;i<obj.length;i++){
			if(i != obj.length-1){
				sbf.append(obj[i]+",");
			}
		   else sbf.append(obj[i]);
		}
		return sbf.toString();
	}
	/**
	 * 方法名 ：getOrigStr<br>
	 * 方法描述 ：根据逗号","拼装sql条件<br>
	 * 创建者 ：Gavin<br>
	 * 创建时间 ：2014-11-27下午06:41:05 <br>
	 * @param qType
	 * @param deptWard
	 * @param deptCode
	 * @return
	 * 返回类型 ：String
	 */
	public static String getOrigStr(String qType,String deptWard,String deptCode){
		StringBuffer sql=new StringBuffer();
		if (deptCode.indexOf(",") != -1) {
			sql.append(" "+qType+" "+deptWard+" in (");
			String deptCodes[] = deptCode.split(",");
			for (int i = 0; i < deptCodes.length; i++) {
				if(i != deptCodes.length-1){
							sql.append("?,");
				}
			   else sql.append("?) ");
			}
		}else{
			sql.append(" "+qType+" "+deptWard+" = ? ");
		}
		return sql.toString();
	}
	
	/**
	 * 方法名 ：getObjectArrays<br>
	 * 方法描述 ：把以逗号分隔的字符串,拆分成对象数组<br>
	 * 创建者 ：Gavin<br>
	 * 创建时间 ：2014-11-28上午09:25:50 <br>
	 * @param deptCode
	 * @return
	 * 返回类型 ：Object[]
	 */
	public static Object[] getObjectArrays(String deptCode){
		Object[] objs =null;
		if (deptCode.indexOf(",") != -1) {
			String deptCodes[] = deptCode.split(",");
			objs = new Object[deptCodes.length];
			for (int i = 0; i < deptCodes.length; i++) {
					objs[i] = deptCodes[i];
			}
		}
		else objs = new Object[]{deptCode};
		
		return objs;
	}
	
	/**
	 * 方法名 ：getObjectArrays<br>
	 * 方法描述 ：合并对象数组对象参数<br>
	 * 创建者 ：Gavin<br>
	 * 创建时间 ：2014-11-28上午11:39:20 <br>
	 * @param afterArgs
	 * @param deptCode "用逗号分隔的字符串"
	 * @param afterObj 对象数组
	 * @return
	 * 返回类型 ：Object[]
	 */
	public static Object[] getObjectArrays(String deptCode,Object...afterObj){
		Object[] objs =null;
		if (deptCode.indexOf(",") != -1) {
			String deptCodes[] = deptCode.split(",");
			String afterObjs[] = Utils.getObjecttoStr(afterObj).split(",");
			objs = new Object[deptCodes.length+afterObj.length];
			for (int i = 0; i < objs.length; i++) {
					if(i < deptCodes.length){
						objs[i] = deptCodes[i];
					}
					else objs[i] = afterObjs[i-deptCodes.length];
			}
		}
		else {
			objs = new Object[afterObj.length+1];
			if(afterObj.length>0){
				for(int j=0;j<objs.length;j++){
					if(j ==0){
						objs[j]=deptCode;
					}
					else objs[j]=afterObj[j-1];
				}
			}
			else objs = new Object[]{deptCode};
		}
		
		return objs;
	}
}