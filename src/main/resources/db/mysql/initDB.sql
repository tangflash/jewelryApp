CREATE DATABASE IF NOT EXISTS jewelryDB;

USE jewelryDB;

/*ͨ�û�������   01-����״̬ */
create table if not exists baseData (
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	TypeNum VarChar(32) not null,
	Number VarChar(32) not null,	
	Name VarChar(64) not null,
	Remark VarChar(128) not null
) engine=InnoDB CHARSET=UTF8;

/*��ʯ��������*/
create table if not exists material (	
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	Number VarChar(32) not null,
	Name VarChar(64) not null,
    Sort int(4) not null default 0,
	Weight Decimal(6,4) not null default 0.0	
) engine=InnoDB CHARSET=UTF8;

/*���ɫ��������*/
create table if not exists GoldType (	
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	Number VarChar(32) not null,
	Name VarChar(128) not null,
    Remark VarChar(256) null	
) engine=InnoDB CHARSET=UTF8;

/*�ͻ���������*/
create table if not exists Client (	
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	Number VarChar(32) not null,
	Name VarChar(128) not null,
    Address VarChar(128) null,
	Tel VarChar(128) null,
	Remark VarChar(256) null
) engine=InnoDB CHARSET=UTF8;

/*��Ʒ����������*/
create table if not exists Product (	
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	Number VarChar(32) not null,
	Name VarChar(128) not null,
    Remark VarChar(256) null	
) engine=InnoDB CHARSET=UTF8;

/*��Ʒ��ʽ��������*/
create table if not exists ProductStyle (	
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	ProductId Bigint(12) not null,
	Number VarChar(32) not null,
	Name VarChar(128) not null,
    Remark VarChar(256) null,
    FOREIGN KEY Fk_ProductStyle_ProductId (ProductId) 
	REFERENCES Product (Id) ON UPDATE CASCADE
) engine=InnoDB CHARSET=UTF8;

/*��ʯ��ⵥ��ͷ*/
create table if not exists materialIn (
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	ClientId  BigInt(12) not null default 0,	/*�ͻ�Id*/
	BillNumber VarChar(32) not null,
	BizDate DateTime not null,	
	CreateTime DateTime not null,
	BillStatus Int(4) Not null Default 0
) engine=InnoDB CHARSET=UTF8;

/*��ʯ�����ϸ*/
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

/*��������ͷ*/
create table if not exists materialOut (
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	BillNumber VarChar(32) not null, /*��������*/
	BizDate DateTime not null,	/*��������*/
	OrderDate DateTime null,   /*�µ�����*/
	PlanFinishDate DateTime null, /*�ƻ��������*/
	ClientId  BigInt(12) not null default 0,	/*�ͻ�Id*/
	GoldPrice Decimal(18,6) not null default 0,	/*���*/
	GoldTypeId  BigInt(12) not null default 0,	/*���ɫId*/	
	CreateTime DateTime not null,
	BalanceBillId BigInt(12) not null default 0, /*�����Ľ��㵥Id*/
	BillStatus Int(4) Not null Default 0
) engine=InnoDB CHARSET=UTF8;

/*��������ϸ*/
create table if not exists materialOutDetail (
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	Number int not null default 0,   	/*���*/
	BillId BigInt(12) not null,	
	Sort int(4) not null default 0,
	ProductNameId BigInt(12) not null default 0, /*Ʒ��Id*/
	StyleNameId BigInt(12) not null default 0,	/*��ʽId*/
	HandSize Decimal(18,6) not null default 0,	/*�ִ�*/
	ProductAmount int not null default 0,	/*����*/
	ProductWeight Decimal(18,6) not null default 0,	/*����*/
	GoldWeight Decimal(18,6) not null default 0,	/*������*/
	ConsumeWeight Decimal(18,6) not null default 0,	/*������*/
	ProcessCost Decimal(18,6) not null default 0,	/*����*/
	AddProcessCost Decimal(18,6) not null default 0,	/*���ӹ���*/
	SuperSetCost Decimal(18,6) not null default 0,	/*���⹤��*/
	MaterId BigInt(12) not null,		/*��ʯId*/
	Amount int not null,				/*��ʯ����*/
	Weight Decimal(18,4) not null,		/*��ʯ����*/
	BackAmount int not null default 0,	       /*�˻���ʯ����*/
	BackWeight Decimal(18,4) not null default 0.0,		/*�˻���ʯ����*/
	FactoryAddMoney Decimal(18,6) not null default 0,	/*��������*/
	SecMaterId BigInt(12) not null,		/*��ʯId*/
	SecAmount int not null,				/*��ʯ����*/
	SecWeight Decimal(18,4) not null,	/*��ʯ����*/
	SecPrice Decimal(18,4) not null,	/*��ʯ����/Ԫ*/
	Loss Decimal(18,4) Not null Default 0, /*���*/	
	TemplateFree Decimal(18,4) Not null Default 0, /*���*/
	Remark	VarChar(1024) null,  /*��ע*/
	FOREIGN KEY Fk_materialOutDetail_BillId (BillId) 
	REFERENCES materialOut (Id) ON DELETE CASCADE ON UPDATE CASCADE
) engine=InnoDB CHARSET=UTF8;

/*�շ���*/
create table if not exists SendReceive(
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	BillId BigInt(12) not null,						/*������Id*/
	DepartmentId BigInt(12) not null default 0,		/*����  1.ִģ   2.��ʯ   3.ִ��   4.�׹� */
	SendDate DateTime null,							/*��������*/
	SendAmount int not null default 0,				/*��������*/
	SendWeight Decimal(18,4) not null default 0,	/*��������*/	
	SendSignInId BigInt(12) not null default 0,		/*����ǩ����Id*/
	ScrapAmount int not null default 0,				/*���ϼ���*/
	ScrapWeight Decimal(18,4) not null default 0,	/*��������*/	
	QCId	BigInt(12) not null default 0,			/*�ʼ���Id*/
	ReceiveDate DateTime null,						/*�ջ�����*/				
	ReceiveAmount int not null default 0,			/*�ջؼ���*/
	ReceiveWeight Decimal(18,4) not null default 0,	/*�ջ�����*/
	ReceiveSignId int not null default 0,			/*�ջ�ǩ��*/
	Remark VarChar(1024) null,						/*��ע*/				
	FOREIGN KEY Fk_SendReceive_BillId (BillId) 
	REFERENCES materialOut (Id) ON DELETE CASCADE ON UPDATE CASCADE
) engine=InnoDB CHARSET=UTF8;

/*��Ա��*/
create table if not exists Person (
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	Number VarChar(32) not null,	/*����*/
	Name VarChar(128) not null,		/*����*/
    Address VarChar(128) null,		/*��ַ*/
	Tel VarChar(128) null,			/*��ϵ�绰*/
	Remark VarChar(256) null		/*��ע*/	
) engine=InnoDB CHARSET=UTF8;

/*���㵥��ͷ*/
create table if not exists balanceBill (
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	BillNumber VarChar(32) not null, /*���㵥��*/
	BizDate DateTime not null,	/*��������*/
	ClientId  BigInt(12) not null default 0,	/*�ͻ�Id*/	
	CreateTime DateTime not null,
	BillStatus Int(4) Not null Default 0
) engine=InnoDB CHARSET=UTF8;

/*���㵥��ʯ��ϸ*/
create table if not exists balanceMainMaterDetail (
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	BillId BigInt(12) not null,		
	MaterId BigInt(12) not null,		/*��ʯId*/
	PriorAmount int not null,				/*�ϴ���ʯ����*/
	PriorWeight Decimal(18,4) not null,		/*�ϴ���ʯ����*/
	InAmount int not null,				/*��������ʯ����*/
	InWeight Decimal(18,4) not null,		/*��������ʯ����*/
	CurAmount int not null default 0,	       /*��ǰ��ʯ����*/
	CurWeight Decimal(18,4) not null default 0.0,		/*��ǰ��ʯ����*/	
	FOREIGN KEY Fk_balanceMainMaterDetail_MaterId (MaterId) 
	REFERENCES material (Id) ON UPDATE CASCADE,
	FOREIGN KEY Fk_balanceMainMaterDetail_BillId (BillId) 
	REFERENCES balanceBill (Id) ON DELETE CASCADE ON UPDATE CASCADE
) engine=InnoDB CHARSET=UTF8;

/*��ʯ���*/
create table if not exists materialInventory (
	Id BigInt(12) not null auto_increment PRIMARY KEY,	
	MaterId BigInt(12) not null,
	ClientId int not null default 0,
	InAmount int not null,	
	InWeight Decimal(18,4) not null,
	OutAmount int not null,	
	OutWeight Decimal(18,4) not null,
	Sort int(4) not null default 0,
	BalanceAmount int not null,	/*�������*/
	BalanceWeight Decimal(18,4) not null, /*�������*/
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

/*�����ֶ�  ���_loss*/
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

/*�����ֶ�  �������_BalanceAmount*/
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

/*�����ֶ�  �������_BalanceWeight*/
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

/*�����ֶ�  ���㵥Id_BalanceBillId*/
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

/*ɾ����� materialoutdetail_ibfk_1*/
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


/*�����ֶ�  Number_���*/
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

/*��������   IX_Inventory_MaterId*/
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

/*�����ֶ�  TemplateFree_���*/
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


/*�����ֶ�  OrderDate_�µ�����*/
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

/*�����ֶ�  PlanFinishDate_�ƻ��������*/
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

/*�����ֶ�  Remark_��ע*/
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