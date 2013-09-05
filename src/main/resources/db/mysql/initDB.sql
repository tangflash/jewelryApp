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
	REFERENCES Product (Id) ON DELETE CASCADE ON UPDATE CASCADE
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
	REFERENCES material (Id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY Fk_materialInDetail_BillId (BillId) 
	REFERENCES materialIn (Id) ON DELETE CASCADE ON UPDATE CASCADE
) engine=InnoDB CHARSET=UTF8;

/*��������ͷ*/
create table if not exists materialOut (
	Id BigInt(12) not null auto_increment PRIMARY KEY,
	BillNumber VarChar(32) not null, /*��������*/
	BizDate DateTime not null,	/*��������*/
	ClientId  BigInt(12) not null default 0,	/*�ͻ�Id*/
	GoldPrice Decimal(18,6) not null default 0,	/*���*/
	GoldTypeId  BigInt(12) not null default 0,	/*���ɫId*/	
	CreateTime DateTime not null,
	BillStatus Int(4) Not null Default 0
) engine=InnoDB CHARSET=UTF8;

/*��������ϸ*/
create table if not exists materialOutDetail (
	Id BigInt(12) not null auto_increment PRIMARY KEY,
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
	FOREIGN KEY Fk_materialOutDetail_MaterId (MaterId) 
	REFERENCES material (Id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY Fk_materialOutDetail_BillId (BillId) 
	REFERENCES materialOut (Id) ON DELETE CASCADE ON UPDATE CASCADE
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
	constraint IX_Inventory_MaterId unique (MaterId)
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