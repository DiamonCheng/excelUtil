package com.ed.excel.util;
import com.dc.excel.util.ExcelHead;

@ExcelHead("应用系统")
public class SystemTransportDO {
	@ExcelHead("系统编号*")
	private String sysCode;
	@ExcelHead("系统名称*")
	private String sysName;
	@ExcelHead("英文全称")
	private String sysNameEn;
	@ExcelHead("英文简称")
	private String sysShortNameEn;
	@ExcelHead("系统状态")
	private String sysState;
	@ExcelHead("系统所属层*")
	private String syslayer;
	@ExcelHead("系统概述*")
	private String sysDesc;
	@ExcelHead("开发厂商信息")
	private String developer;
	@ExcelHead("系统上线日期")
	private String onlineDate;
	@ExcelHead("系统下线日期")
	private String offlineDate;
	@ExcelHead("技术部门")
	private String techDept;
	@ExcelHead("应用负责人*")
	private String managerName;
	@ExcelHead("应用配合人")
	private String appCoor;
	@ExcelHead("业务负责部门")
	private String busiDept;
	@ExcelHead("业务负责人")
	private String busiLeader;
	public String getBusiLeader() {
		return busiLeader;
	}

	public void setBusiLeader(String busiLeader) {
		this.busiLeader = busiLeader;
	}
	@ExcelHead("运维负责人")
	private String oprleader;
	@ExcelHead("灾备等级")
	private String disaLevel;
	@ExcelHead("灾备模式")
	private String disaMode;
	@ExcelHead("灾备恢复时间目标RTO（分钟）")
	private String disaRecRtoTime;
	@ExcelHead("灾备恢复点目标RPO（分钟）")
	private String disaRpoRtoTime;
	@ExcelHead("灾备系统情况说明")
	private String systemNote;
	@ExcelHead("安全保护等级")
	private String secLevel;
	@ExcelHead("是否建立应急预案")
	private String contPlan;
	@ExcelHead("是否已纳入监控")
	private String monitor;
	@ExcelHead("服务时间")
	private String serviceTime;
	@ExcelHead("使用用户")
	private String sysUser;
	@ExcelHead("服务范围")
	private String serviceArea;
	@ExcelHead("是否直接面向客户")
	private String faceToCust;
	@ExcelHead("系统类型")
	private String sysType;
	@ExcelHead("访问方式")
	private String accessMode;
	@ExcelHead("应用服务器高可用技术")
	private String highAvailableTech;
	@ExcelHead("应用服务器是否负载均衡")
	private String balance;
	@ExcelHead("操作系统")
	private String oprSystem;
	@ExcelHead("数据库")
	private String dataBase;
	@ExcelHead("中间件")
	private String middleware;
	@ExcelHead("是否管控内部接口")
	private String innerJkPass;
	@ExcelHead("系统用户列表")
	private String sysUserList;
	
	public SystemTransportDO() {
		super();
		// TODO 自动生成的构造函数存根
	}
	

	public SystemTransportDO(String sysCode, String sysName, String sysNameEn,
			String sysShortNameEn, String sysState, String syslayer,
			String sysDesc, String developer, String onlineDate,
			String offlineDate, String techDept, String managerName,
			String appCoor, String busiDept, String busiLeader,
			String oprleader, String disaLevel, String disaMode,
			String disaRecRtoTime, String disaRpoRtoTime, String systemNote,
			String secLevel, String contPlan, String monitor,
			String serviceTime, String sysUser, String serviceArea,
			String faceToCust, String sysType, String accessMode,
			String highAvailableTech, String balance, String oprSystem,
			String dataBase, String middleware, String innerJkPass,
			String sysUserList) {
		super();
		this.sysCode = sysCode;
		this.sysName = sysName;
		this.sysNameEn = sysNameEn;
		this.sysShortNameEn = sysShortNameEn;
		this.sysState = sysState;
		this.syslayer = syslayer;
		this.sysDesc = sysDesc;
		this.developer = developer;
		this.onlineDate = onlineDate;
		this.offlineDate = offlineDate;
		this.techDept = techDept;
		this.managerName = managerName;
		this.appCoor = appCoor;
		this.busiDept = busiDept;
		this.busiLeader = busiLeader;
		this.oprleader = oprleader;
		this.disaLevel = disaLevel;
		this.disaMode = disaMode;
		this.disaRecRtoTime = disaRecRtoTime;
		this.disaRpoRtoTime = disaRpoRtoTime;
		this.systemNote = systemNote;
		this.secLevel = secLevel;
		this.contPlan = contPlan;
		this.monitor = monitor;
		this.serviceTime = serviceTime;
		this.sysUser = sysUser;
		this.serviceArea = serviceArea;
		this.faceToCust = faceToCust;
		this.sysType = sysType;
		this.accessMode = accessMode;
		this.highAvailableTech = highAvailableTech;
		this.balance = balance;
		this.oprSystem = oprSystem;
		this.dataBase = dataBase;
		this.middleware = middleware;
		this.innerJkPass = innerJkPass;
		this.sysUserList = sysUserList;
	}

	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public String getSysNameEn() {
		return sysNameEn;
	}
	public void setSysNameEn(String sysNameEn) {
		this.sysNameEn = sysNameEn;
	}
	public String getSysShortNameEn() {
		return sysShortNameEn;
	}
	public void setSysShortNameEn(String sysShortNameEn) {
		this.sysShortNameEn = sysShortNameEn;
	}
	public String getSysState() {
		return sysState;
	}
	public void setSysState(String sysState) {
		this.sysState = sysState;
	}
	public String getSyslayer() {
		return syslayer;
	}
	public void setSyslayer(String syslayer) {
		this.syslayer = syslayer;
	}
	public String getSysDesc() {
		return sysDesc;
	}
	public void setSysDesc(String sysDesc) {
		this.sysDesc = sysDesc;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public String getOnlineDate() {
		return onlineDate;
	}
	public void setOnlineDate(String onlineDate) {
		this.onlineDate = onlineDate;
	}
	public String getOfflineDate() {
		return offlineDate;
	}
	public void setOfflineDate(String offlineDate) {
		this.offlineDate = offlineDate;
	}
	public String getTechDept() {
		return techDept;
	}
	public void setTechDept(String techDept) {
		this.techDept = techDept;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getAppCoor() {
		return appCoor;
	}
	public void setAppCoor(String appCoor) {
		this.appCoor = appCoor;
	}
	public String getBusiDept() {
		return busiDept;
	}
	public void setBusiDept(String busiDept) {
		this.busiDept = busiDept;
	}
	public String getOprleader() {
		return oprleader;
	}
	public void setOprleader(String oprleader) {
		this.oprleader = oprleader;
	}
	public String getDisaLevel() {
		return disaLevel;
	}
	public void setDisaLevel(String disaLevel) {
		this.disaLevel = disaLevel;
	}
	public String getDisaMode() {
		return disaMode;
	}
	public void setDisaMode(String disaMode) {
		this.disaMode = disaMode;
	}
	public String getDisaRecRtoTime() {
		return disaRecRtoTime;
	}
	public void setDisaRecRtoTime(String disaRecRtoTime) {
		this.disaRecRtoTime = disaRecRtoTime;
	}
	public String getDisaRpoRtoTime() {
		return disaRpoRtoTime;
	}
	public void setDisaRpoRtoTime(String disaRpoRtoTime) {
		this.disaRpoRtoTime = disaRpoRtoTime;
	}
	public String getSystemNote() {
		return systemNote;
	}
	public void setSystemNote(String systemNote) {
		this.systemNote = systemNote;
	}
	public String getSecLevel() {
		return secLevel;
	}
	public void setSecLevel(String secLevel) {
		this.secLevel = secLevel;
	}
	public String getContPlan() {
		return contPlan;
	}
	public void setContPlan(String contPlan) {
		this.contPlan = contPlan;
	}
	public String getMonitor() {
		return monitor;
	}
	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}
	public String getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}
	public String getSysUser() {
		return sysUser;
	}
	public void setSysUser(String sysUser) {
		this.sysUser = sysUser;
	}
	public String getServiceArea() {
		return serviceArea;
	}
	public void setServiceArea(String serviceArea) {
		this.serviceArea = serviceArea;
	}
	public String getFaceToCust() {
		return faceToCust;
	}
	public void setFaceToCust(String faceToCust) {
		this.faceToCust = faceToCust;
	}
	public String getSysType() {
		return sysType;
	}
	public void setSysType(String sysType) {
		this.sysType = sysType;
	}
	public String getAccessMode() {
		return accessMode;
	}
	public void setAccessMode(String accessMode) {
		this.accessMode = accessMode;
	}
	public String getHighAvailableTech() {
		return highAvailableTech;
	}
	public void setHighAvailableTech(String highAvailableTech) {
		this.highAvailableTech = highAvailableTech;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getOprSystem() {
		return oprSystem;
	}
	public void setOprSystem(String oprSystem) {
		this.oprSystem = oprSystem;
	}
	public String getDataBase() {
		return dataBase;
	}
	public void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}
	public String getMiddleware() {
		return middleware;
	}
	public void setMiddleware(String middleware) {
		this.middleware = middleware;
	}
	public String getInnerJkPass() {
		return innerJkPass;
	}
	public void setInnerJkPass(String innerJkPass) {
		this.innerJkPass = innerJkPass;
	}
	public String getSysUserList() {
		return sysUserList;
	}
	public void setSysUserList(String sysUserList) {
		this.sysUserList = sysUserList;
	}
	
	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "SystemTransportDO{" +
					   "sysCode='" + sysCode + '\'' +
					   ", sysName='" + sysName + '\'' +
					   ", sysNameEn='" + sysNameEn + '\'' +
					   ", sysShortNameEn='" + sysShortNameEn + '\'' +
					   ", sysState='" + sysState + '\'' +
					   ", syslayer='" + syslayer + '\'' +
					   ", sysDesc='" + sysDesc + '\'' +
					   ", developer='" + developer + '\'' +
					   ", onlineDate='" + onlineDate + '\'' +
					   ", offlineDate='" + offlineDate + '\'' +
					   ", techDept='" + techDept + '\'' +
					   ", managerName='" + managerName + '\'' +
					   ", appCoor='" + appCoor + '\'' +
					   ", busiDept='" + busiDept + '\'' +
					   ", busiLeader='" + busiLeader + '\'' +
					   ", oprleader='" + oprleader + '\'' +
					   ", disaLevel='" + disaLevel + '\'' +
					   ", disaMode='" + disaMode + '\'' +
					   ", disaRecRtoTime='" + disaRecRtoTime + '\'' +
					   ", disaRpoRtoTime='" + disaRpoRtoTime + '\'' +
					   ", systemNote='" + systemNote + '\'' +
					   ", secLevel='" + secLevel + '\'' +
					   ", contPlan='" + contPlan + '\'' +
					   ", monitor='" + monitor + '\'' +
					   ", serviceTime='" + serviceTime + '\'' +
					   ", sysUser='" + sysUser + '\'' +
					   ", serviceArea='" + serviceArea + '\'' +
					   ", faceToCust='" + faceToCust + '\'' +
					   ", sysType='" + sysType + '\'' +
					   ", accessMode='" + accessMode + '\'' +
					   ", highAvailableTech='" + highAvailableTech + '\'' +
					   ", balance='" + balance + '\'' +
					   ", oprSystem='" + oprSystem + '\'' +
					   ", dataBase='" + dataBase + '\'' +
					   ", middleware='" + middleware + '\'' +
					   ", innerJkPass='" + innerJkPass + '\'' +
					   ", sysUserList='" + sysUserList + '\'' +
					   '}';
	}
}
