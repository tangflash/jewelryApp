CREATE DATABASE IF NOT EXISTS jewelryDB;

USE jewelryDB;

/*通用基础数据   01-单据状态 */
create table if not exists baseData (
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	TypeNum VarChar(32) not null,
	Number VarChar(32) not null,	
	Name VarChar(64) not null,
	Remark VarChar(128) not null
) engine=InnoDB CHARSET=UTF8;

/*钻石基础数据*/
create table if not exists material (	
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	Number VarChar(32) not null,
	Name VarChar(64) not null,
    Sort int(4) not null default 0,
	Weight Decimal(6,4) not null default 0.0	
) engine=InnoDB CHARSET=UTF8;

/*金成色基础数据*/
create table if not exists GoldType (	
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	Number VarChar(32) not null,
	Name VarChar(128) not null,
    Remark VarChar(256) null	
) engine=InnoDB CHARSET=UTF8;

/*客户基础数据*/
create table if not exists Client (	
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	Number VarChar(32) not null,
	Name VarChar(128) not null,
    Address VarChar(128) null,
	Tel VarChar(128) null,
	Remark VarChar(256) null
) engine=InnoDB CHARSET=UTF8;

/*产品名基础数据*/
create table if not exists Product (	
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	Number VarChar(32) not null,
	Name VarChar(128) not null,
    Remark VarChar(256) null	
) engine=InnoDB CHARSET=UTF8;

/*产品款式基础数据*/
create table if not exists ProductStyle (	
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	ProductId Bigint(12) not null,
	Number VarChar(32) not null,
	Name VarChar(128) not null,
    Remark VarChar(256) null,
    FOREIGN KEY Fk_ProductStyle_ProductId (ProductId) 
	REFERENCES Product (Id) ON UPDATE CASCADE
) engine=InnoDB CHARSET=UTF8;

/*钻石入库单据头*/
create table if not exists materialIn (
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	ClientId  BigInt(12) not null default 0,	/*客户Id*/
	BillNumber VarChar(32) not null,
	BizDate DateTime not null,	
	CreateTime DateTime not null,
	BillStatus Int(4) Not null Default 0
) engine=InnoDB CHARSET=UTF8;

/*钻石入库明细*/
create table if not exists materialInDetail (
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	BillId BigInt(12) not null,	
	MaterId BigInt(12) not null,
	Amount int not null,	
	Weight Decimal(18,4) not null,
	Sort int(4) not null default 0,
	FOREIGN KEY Fk_materialInDetail_MaterId (MaterId) 
	REFERENCES material (Id) ON UPDATE CASCADE,
	FOREIGN KEY Fk_materialInDetail_BillId (BillId) 
	REFERENCES materialIn (Id) ON DELETE CASCADE ON UPDATE CASCADE
) engine=InnoDB CHARSET=UTF8;

/*出货单据头*/
create table if not exists materialOut (
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	BillNumber VarChar(32) not null, /*出货单号*/
	BizDate DateTime not null,	/*出货日期*/
	OrderDate DateTime null,   /*下单日期*/
	PlanFinishDate DateTime null, /*计划完成日期*/
	ClientId  BigInt(12) not null default 0,	/*客户Id*/
	GoldPrice Decimal(18,6) not null default 0,	/*金价*/
	GoldTypeId  BigInt(12) not null default 0,	/*金成色Id*/	
	CreateTime DateTime not null,
	BalanceBillId BigInt(12) not null default 0, /*关联的结算单Id*/
	BillStatus Int(4) Not null Default 0
) engine=InnoDB CHARSET=UTF8;

/*出货单明细*/
create table if not exists materialOutDetail (
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	Number int not null default 0,   	/*序号*/
	BillId BigInt(12) not null,	
	Sort int(4) not null default 0,
	ProductNameId BigInt(12) not null default 0, /*品名Id*/
	StyleNameId BigInt(12) not null default 0,	/*款式Id*/
	HandSize Decimal(18,6) not null default 0,	/*手寸*/
	ProductAmount int not null default 0,	/*件数*/
	ProductWeight Decimal(18,6) not null default 0,	/*货重*/
	GoldWeight Decimal(18,6) not null default 0,	/*净金重*/
	ConsumeWeight Decimal(18,6) not null default 0,	/*含耗重*/
	ProcessCost Decimal(18,6) not null default 0,	/*工费*/
	AddProcessCost Decimal(18,6) not null default 0,	/*附加工费*/
	SuperSetCost Decimal(18,6) not null default 0,	/*超镶工费*/
	MaterId BigInt(12) not null,		/*主石Id*/
	Amount int not null,				/*主石粒数*/
	Weight Decimal(18,4) not null,		/*主石重量*/
	BackAmount int not null default 0,	       /*退回主石数量*/
	BackWeight Decimal(18,4) not null default 0.0,		/*退回主石重量*/
	FactoryAddMoney Decimal(18,6) not null default 0,	/*厂配主额*/
	SecMaterId BigInt(12) not null,		/*副石Id*/
	SecAmount int not null,				/*副石粒数*/
	SecWeight Decimal(18,4) not null,	/*副石重量*/
	SecPrice Decimal(18,4) not null,	/*副石单价/元*/
	Loss Decimal(18,4) Not null Default 0, /*损耗*/	
	TemplateFree Decimal(18,4) Not null Default 0, /*版费*/
	Remark	VarChar(1024) null,  /*备注*/
	FOREIGN KEY Fk_materialOutDetail_BillId (BillId) 
	REFERENCES materialOut (Id) ON DELETE CASCADE ON UPDATE CASCADE
) engine=InnoDB CHARSET=UTF8;

/*收发表*/
create table if not exists SendReceive(
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	BillId BigInt(12) not null,						/*出货单Id*/
	DepartmentId BigInt(12) not null default 0,		/*部门  1.执模   2.镶石   3.执边   4.抛光 */
	SendDate DateTime null,							/*发出日期*/
	SendAmount int not null default 0,				/*发货件数*/
	SendWeight Decimal(18,4) not null default 0,	/*发出重量*/	
	SendSignInId BigInt(12) not null default 0,		/*发出签收人Id*/
	ScrapAmount int not null default 0,				/*报废件数*/
	ScrapWeight Decimal(18,4) not null default 0,	/*报废重量*/	
	QCId	BigInt(12) not null default 0,			/*质检人Id*/
	ReceiveDate DateTime null,						/*收回日期*/				
	ReceiveAmount int not null default 0,			/*收回件数*/
	ReceiveWeight Decimal(18,4) not null default 0,	/*收回重量*/
	ReceiveSignId int not null default 0,			/*收回签收*/
	Remark VarChar(1024) null,						/*备注*/				
	FOREIGN KEY Fk_SendReceive_BillId (BillId) 
	REFERENCES materialOut (Id) ON DELETE CASCADE ON UPDATE CASCADE
) engine=InnoDB CHARSET=UTF8;

/*人员表*/
create table if not exists Person (
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	Number VarChar(32) not null,	/*工号*/
	Name VarChar(128) not null,		/*姓名*/
    Address VarChar(128) null,		/*地址*/
	Tel VarChar(128) null,			/*联系电话*/
	Remark VarChar(256) null		/*备注*/	
) engine=InnoDB CHARSET=UTF8;

/*结算单据头*/
create table if not exists balanceBill (
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	BillNumber VarChar(32) not null, /*结算单号*/
	BizDate DateTime not null,	/*结算日期*/
	ClientId  BigInt(12) not null default 0,	/*客户Id*/	
	CreateTime DateTime not null,
	BillStatus Int(4) Not null Default 0
) engine=InnoDB CHARSET=UTF8;

/*结算单主石明细*/
create table if not exists balanceMainMaterDetail (
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	BillId BigInt(12) not null,		
	MaterId BigInt(12) not null,		/*主石Id*/
	PriorAmount int not null,				/*上存主石粒数*/
	PriorWeight Decimal(18,4) not null,		/*上存主石重量*/
	InAmount int not null,				/*本期入主石粒数*/
	InWeight Decimal(18,4) not null,		/*本期入主石重量*/
	CurAmount int not null default 0,	       /*当前主石数量*/
	CurWeight Decimal(18,4) not null default 0.0,		/*当前主石重量*/	
	FOREIGN KEY Fk_balanceMainMaterDetail_MaterId (MaterId) 
	REFERENCES material (Id) ON UPDATE CASCADE,
	FOREIGN KEY Fk_balanceMainMaterDetail_BillId (BillId) 
	REFERENCES balanceBill (Id) ON DELETE CASCADE ON UPDATE CASCADE
) engine=InnoDB CHARSET=UTF8;

/*钻石库存*/
create table if not exists materialInventory (
	Id BigInt(12) not null auto_increment PRIMARY KEY,	
	MaterId BigInt(12) not null,
	ClientId int not null default 0,
	InAmount int not null,	
	InWeight Decimal(18,4) not null,
	OutAmount int not null,	
	OutWeight Decimal(18,4) not null,
	Sort int(4) not null default 0,
	BalanceAmount int not null,	/*结存数量*/
	BalanceWeight Decimal(18,4) not null, /*结存重量*/
	constraint IX_Inventory_MaterId unique (ClientId,MaterId)
) engine=InnoDB CHARSET=UTF8;

set @s = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'materialIn'
        AND table_schema = DATABASE()
        AND column_name = 'BillStatus'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE materialIn ADD BillStatus Int(4) Not null Default 0"
));

PREPARE stmt FROM @s;
EXECUTE stmt;

set @s = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'materialOut'
        AND table_schema = DATABASE()
        AND column_name = 'BillStatus'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE materialOut ADD BillStatus Int(4) Not null Default 0"
));

PREPARE stmt FROM @s;
EXECUTE stmt;

/*增加字段  损耗_loss*/
set @s = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'materialOutDetail'
        AND table_schema = DATABASE()
        AND column_name = 'Loss'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE materialOutDetail ADD Loss Decimal(18,4) Not null Default 0"
));

PREPARE stmt FROM @s;
EXECUTE stmt;

/*增加字段  结存数量_BalanceAmount*/
set @s = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'materialInventory'
        AND table_schema = DATABASE()
        AND column_name = 'BalanceAmount'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE materialInventory ADD BalanceAmount int Not null Default 0"
));

PREPARE stmt FROM @s;
EXECUTE stmt;

/*增加字段  结存重量_BalanceWeight*/
set @s = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'materialInventory'
        AND table_schema = DATABASE()
        AND column_name = 'BalanceWeight'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE materialInventory ADD BalanceWeight Decimal(18,4) Not null Default 0"
));

PREPARE stmt FROM @s;
EXECUTE stmt;

/*增加字段  结算单Id_BalanceBillId*/
set @s = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'materialOut'
        AND table_schema = DATABASE()
        AND column_name = 'BalanceBillId'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE materialOut ADD BalanceBillId BigInt(12) Not null Default 0"
));

PREPARE stmt FROM @s;
EXECUTE stmt;

/*删除外键 materialoutdetail_ibfk_1*/
set @s = (SELECT IF(
    (SELECT COUNT(1)
        FROM information_schema.REFERENTIAL_CONSTRAINTS 
        WHERE table_name = 'materialOutDetail'
        AND CONSTRAINT_SCHEMA = DATABASE()
        AND CONSTRAINT_NAME = 'materialoutdetail_ibfk_1') < 1,
    "SELECT 1",
    "ALTER TABLE materialOutDetail DROP FOREIGN KEY materialoutdetail_ibfk_1"
));
PREPARE stmt FROM @s;
EXECUTE stmt;


/*增加字段  Number_序号*/
set @s = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'materialOutDetail'
        AND table_schema = DATABASE()
        AND column_name = 'Number'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE materialOutDetail ADD Number int Not null Default 0"
));

PREPARE stmt FROM @s;
EXECUTE stmt;

/*增加索引   IX_Inventory_MaterId*/
set @s = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.statistics
        WHERE table_name = 'materialInventory'
        AND index_schema = DATABASE()
        AND index_name = 'IX_Inventory_MaterId'
    ) > 0,
    "SELECT 1",
    "CREATE UNIQUE INDEX IX_Inventory_MaterId ON materialInventory(clientId,materId)"
));

PREPARE stmt FROM @s;
EXECUTE stmt;

/*增加字段  TemplateFree_版费*/
set @s = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'materialOutDetail'
        AND table_schema = DATABASE()
        AND column_name = 'TemplateFree'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE materialOutDetail ADD TemplateFree Decimal(18,4) Not null Default 0"
));

PREPARE stmt FROM @s;
EXECUTE stmt;


/*增加字段  OrderDate_下单日期*/
set @s = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'materialOut'
        AND table_schema = DATABASE()
        AND column_name = 'OrderDate'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE materialOut ADD OrderDate DateTime null"
));
PREPARE stmt FROM @s;
EXECUTE stmt;

/*增加字段  PlanFinishDate_计划完成日期*/
set @s = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'materialOut'
        AND table_schema = DATABASE()
        AND column_name = 'PlanFinishDate'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE materialOut ADD PlanFinishDate DateTime null"
));
PREPARE stmt FROM @s;
EXECUTE stmt;

/*增加字段  Remark_备注*/
set @s = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'materialOutDetail'
        AND table_schema = DATABASE()
        AND column_name = 'Remark'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE materialOutDetail ADD Remark VarChar(1024) null"
));
PREPARE stmt FROM @s;
EXECUTE stmt;